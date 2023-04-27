package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    private static final byte[] arr = new byte[1000];
    private static final int len = arr.length;

    public Server() {

    }

    public static void main(String[] args) {
        InetAddress host;
        int port = 6000;
        SocketAddress addr;
        SocketChannel sock;
        // фабрика сокетов
        ServerSocketChannel serv;
        // буффер массива, который мы передаём на сервер
        ByteBuffer buf;

        try {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);

            serv = ServerSocketChannel.open();
            serv.bind(addr);
            sock = serv.accept();

            // оборачиваем массив в буффер и передаём на сервер (?)
            buf = ByteBuffer.wrap(arr);
            sock.read(buf);

            String req = new String(arr);
            //System.out.println(req);
            // прогнать через десер
            // десер прогнать через req handler
            RequestHandler rh = new RequestHandler();
            rh.run(Deserializer.readReq(req));

            // передаём обратно на клиент
            buf.flip();
            sock.write(buf);
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
