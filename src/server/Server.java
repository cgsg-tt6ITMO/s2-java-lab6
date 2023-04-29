package server;

import client.input_manager.AskInputManager;
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
import java.util.Locale;
import java.util.Scanner;

/**
 * Class to handle server.
 */
public class Server {
    private static byte[] arr = new byte[8192];
    private static final int len = arr.length;
    // maximum number of commands
    private static final int maxCom = 100;

    public Server() {}

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

                // прогнать через десер, десер прогнать через req handler
                // зачем req handler, если мы в итоге req -> serial -> req -> req handler

                Request r = Deserializer.readReq(new String(arr));
                Response response = commandManager.getCommands().get(r.name()).execute(r.args());

                // обратно
                arr = Serializer.objSer(response).getBytes(StandardCharsets.UTF_8);
                buf = ByteBuffer.wrap(arr);

                // передаём обратно на клиент
                //buf.flip();
                sock.write(buf);
                arr = new byte[8192];
                q--;
            }

            sock.close();
        } // host = InetAddress.getLocalHost();
        catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } // sock = SocketChannel.open();
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
