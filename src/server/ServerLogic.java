/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server;

import resources.utility.Deserializer;
import resources.utility.Request;
import resources.utility.Response;
import resources.utility.Serializer;
import server.managers.CommandManager;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.rmi.ConnectIOException;
import java.util.ArrayList;
import java.util.Iterator;

import static java.nio.channels.SelectionKey.*;

/**
 * Server. Handles command logic.
 */
public class ServerLogic {
    private static byte[] arr = new byte[8192];
    private static final int MAX_NUM_COMMANDS = 10;

    private CommandManager commandManager = new CommandManager();

    public void makeResponse() {
        //System.out.println("makeResponse " + new String(arr));

        // data deserialization & command execution

        // TODO: сделать так, чтоб без start не начинали
        if (arr[0] == '[') {
            Request[] reqs = Deserializer.readArr(new String(arr));
            ArrayList<Response> response = new ArrayList<>();
            for (Request r : reqs) {
                if (r != null) {
                    Response res = commandManager.runCommand(r);
                    response.add(res);
                }
            }
            arr = new byte[8192];
            arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
        } else {
            Request r = Deserializer.readReq(new String(arr));
            Response response = commandManager.runCommand(r.name(), r.args());
            arr = new byte[8192];
            arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
        }
        //System.out.println("makeResponse: " + new String(arr));
    }

    /**
     * Default constructor.
     */
    public ServerLogic() {}

    public void run() {
        int q = MAX_NUM_COMMANDS;
        int port = 6000;
        ServerSocketChannel serv;
        Selector sel;
        ByteBuffer buf;

        try {
            sel = Selector.open();
            serv = ServerSocketChannel.open();
            InetAddress host = InetAddress.getLocalHost();
            SocketAddress addr = new InetSocketAddress(host, port);
            serv.bind(addr);
            serv.configureBlocking(false);
            serv.register(sel, OP_ACCEPT);
            boolean loop = true;

            while (true) {
                sel.select();
                Iterator<SelectionKey> iter = sel.selectedKeys().iterator();
                if (iter.hasNext()) {
                    SelectionKey k = iter.next();

                    if (k.isAcceptable()) {
                        System.out.println("New channel...");
                        SocketChannel sock = serv.accept();
                        sock.configureBlocking(false);
                        sock.register(sel, SelectionKey.OP_READ);
                    }

                    else if (k.isReadable()) {
                        System.out.println("readable");
                        SocketChannel client = (SocketChannel) k.channel();
                        client.configureBlocking(false);
                        arr = new byte[8192];
                        buf = ByteBuffer.wrap(arr);
                        buf.clear();
                        client.read(buf);
                        System.out.println("one " + new String(arr));

                        buf.clear();
                        makeResponse();

                        /*
                        Request r = Deserializer.readReq(new String(arr));
                        Response response = new CommandManager().runCommand(r.name(), r.args());
                        buf.clear();
                        arr = new byte[8192];
                        arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
                        //makeResponse();

                         */


                        buf.flip();
                        buf = ByteBuffer.wrap(arr);
                        client.write(buf);
                        buf.clear();
                        //System.out.println(new String(arr));
                    }
                    iter.remove();
                }
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (ConnectIOException e) {
            System.err.println("ConnectIOException");
        } catch (BindException e) {
            port += 1;
        } catch (IOException e) {
            System.out.println("One client was disconnected...");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("ERROR...");
            e.printStackTrace();
        }

    }
}
