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

import static java.nio.channels.SelectionKey.*;

/**
 * Server. Handles command logic.
 */
public class ServerLogic {
    private static byte[] arr = new byte[8192];
    private static final int MAX_NUM_COMMANDS = 10;
    private int port = 6000;
    private ServerSocketChannel serv;
    private Selector sel;
    private SelectionKey key;
    private SocketChannel sock;
    private SelectableChannel selectableChannel;
    private ByteBuffer buf;

    public void init() {
        try {
            //sel = Selector.open();
            //System.out.println(sel.keys().isEmpty());
            InetAddress host = InetAddress.getLocalHost();
            SocketAddress addr = new InetSocketAddress(host, port);
            serv = ServerSocketChannel.open();
            serv.bind(addr);
            // non-blocking
            //serv.configureBlocking(false);
            //key = serv.register(/*key.selector()*/ sel, OP_ACCEPT);
        } catch (ClosedChannelException | UnknownHostException e) {
            System.err.println("init trouble");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("INIT ERROR");
            e.printStackTrace();
        }
    }

    public void accept() throws IOException {
        //serv = (ServerSocketChannel)key.channel();
        sock = serv.accept();
        //serv.configureBlocking(false);
        //key = serv.register(key.selector(), OP_ACCEPT);
        //key.attach(key.attachment());
    }

    public void read() throws IOException {
        //selectableChannel = key.channel();

        // get data from client
        buf = ByteBuffer.wrap(arr);
        sock.read(buf);

        //serv.configureBlocking(false);
        //key = serv.register(key.selector(), OP_ACCEPT);
    }

    public void write() throws IOException {
        //sock = (SocketChannel) key.channel();
        // send response to the client
        buf = ByteBuffer.wrap(arr);
        sock.write(buf);
        arr = new byte[8192];
        //sock.configureBlocking(false);
        //sock.register(key.selector(), OP_ACCEPT);
    }

    /**
     * Default constructor.
     */
    public ServerLogic() {}

    public void run() {
        int q = MAX_NUM_COMMANDS;
        CommandManager commandManager = new CommandManager();

        init();
        boolean loop = true;
        do {
            try {
                //init();
                accept();
                while (q > 0) {
                    read();
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
                        arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
                    } else {
                        Request r = Deserializer.readReq(new String(arr));
                        Response response = commandManager.runCommand(r.name(), r.args());
                        arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
                    }

                    write();

                    q--;
                }

                sock.close();
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
        } while (loop);
    }
}
