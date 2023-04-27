/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import resources.utility.Request;
import client.input_manager.AskInputManager;
import client.input_manager.Input;
import server.RequestHandler;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This class handles the commands that the client inputs in loop.
 */
public class Client {
    private static byte[] arr;
    private static int len;

    public static void run() {
        Scanner sc = new Scanner(System.in);
        Input im = new AskInputManager(sc);
        RequestHandler requestHandler = new RequestHandler();
        Serializer ser = new Serializer();
        Request r = null;

        //while (sc.hasNext()) {
            String command = sc.nextLine();
            switch (command) {
                // save должно совершаться на сервере
                case "group_counting_by_from",
                        "help", "info", "show",
                        "print_field_descending_distance",
                        "clear", "exit" -> {
                    r = (new Request(command, null));
                }
                case "add", "remove_lower", "add_if_max" -> {
                    // нужно чекнуть, что правильный сканнер для execute_script
                    r = (new Request(command, ser.routeSer(im.inpRoute())));
                }
                case "remove_by_id" ->
                        r = (new Request(command, ser.longSer(im.inpLong("id"))));
                case "update", "insert_at" ->
                        r = (new Request(command, ser.longRouteSer(im.inpLong("id"), im.inpRoute())));
                case "filter_greater_than_distance" ->
                        r = (new Request(command, ser.doubleSer(im.inpDouble("distance"))));
                case "execute_script" -> {
                    //requestHandler.run(new Request(command, im.inpString("file name")));
                }
                default -> {
                    System.err.println(command + " doesn't exist.");
                }
            //}
        }
        if (r != null) {
            arr = ser.requestSer(r).getBytes(StandardCharsets.UTF_8);
            len = arr.length;
        }
        else {
            arr = new byte[]{};
            len = arr.length;
            System.err.println("arr is null");
        }
    }

    public static void main(String[] args) {
        run();
        InetAddress host;
        int port = 6000;
        SocketAddress addr;
        SocketChannel sock;
        // буффер массива, который мы передаём на сервер
        ByteBuffer buf;
        try {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);
            // он хочет try с ресурсами для автозакрытия
            sock = SocketChannel.open();
            sock.connect(addr);

            //System.out.println(new String(arr));

            // оборачиваем массив в буффер и передаём на сервер (?)
            buf = ByteBuffer.wrap(arr);
            sock.write(buf);

            buf.clear();
            sock.read(buf);

            for (int i = 0; i < len; i++) {
                System.out.println(arr[i]);
            }
            sock.close();
        } // host = InetAddress.getLocalHost();
        catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
        // sock = SocketChannel.open();
        catch (ConnectException e) {
            System.err.println(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
