/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server;

import resources.utility.Deserializer;
import resources.utility.Request;
import resources.utility.Response;
import resources.utility.Serializer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Server. Handles command logic.
 */
public class Server {
    private static byte[] arr = new byte[8192];
    private static final int len = arr.length;
    private static final int maxCom = 100;

    /**
     * Default constructor.
     */
    public Server() {}

    /**
     * Gets requests, handles them and sends responses.
     * Does all the manipulation with the collection.
     * @param args cmd arguments.
     */
    public static void main(String[] args) {
        int q = maxCom;
        InetAddress host;
        int port = 6000;
        SocketAddress addr;
        SocketChannel sock;
        ServerSocketChannel serv;
        ByteBuffer buf;
        CommandManager commandManager = new CommandManager();

        try {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);

            serv = ServerSocketChannel.open();
            serv.bind(addr);
            sock = serv.accept();

            while (q > 0) {
                buf = ByteBuffer.wrap(arr);
                sock.read(buf);

                // data deserialization & command execution

                if (arr[0] == '[') {
                    System.out.println("array form execute_script");
                    Request[] reqs = Deserializer.readArr(new String(arr));
                    ArrayList<Response> response = new ArrayList<>();
                    for (Request r : reqs) {
                        if (r != null) {
                            Response res = commandManager.getCommands().get(r.name()).execute(r.args());
                            response.add(res);
                        }
                    }
                    arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
                    System.out.println(new String(arr));
                } else {
                    Request r = Deserializer.readReq(new String(arr));
                    Response response = commandManager.getCommands().get(r.name()).execute(r.args());
                    arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
                }

                // send response to the client
                buf = ByteBuffer.wrap(arr);
                //buf.flip();
                sock.write(buf);
                arr = new byte[8192];
                q--;
            }
            sock.close();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
