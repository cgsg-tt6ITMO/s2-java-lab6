/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import resources.exceptions.NoSuchCommandException;
import resources.utility.Deserializer;
import resources.utility.Request;
import client.input_manager.AskInputManager;
import client.input_manager.Input;
import resources.utility.Serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Method that handles input logic.
     * It handles the number of arguments command needs.
     */
    public static void run() {
        Input im = new AskInputManager(sc);
        Request r = null;

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
                r = (new Request(command, Serializer.routeSer(im.inpRoute())));
            }
            case "remove_by_id" ->
                    r = (new Request(command, Serializer.longSer(im.inpLong("id"))));
            case "update", "insert_at" ->
                    r = (new Request(command, Serializer.longRouteSer(im.inpLong("id"), im.inpRoute())));
            case "filter_greater_than_distance" ->
                    r = (new Request(command, Serializer.doubleSer(im.inpDouble("distance"))));
            case "execute_script" -> {
                //requestHandler.run(new Request(command, im.inpString("file name")));
            }
            default -> {
                throw new NoSuchCommandException(command + " doesn't exist.");
            }
        }
        if (r != null) {
            arr = Serializer.requestSer(r).getBytes(StandardCharsets.UTF_8);
            len = arr.length;
        }
        else {
            arr = new byte[1000];
            len = arr.length;
            System.err.println("arr is null");
        }
    }

    public static void main(String[] args) {
        InetAddress host;
        int port = 6000;
        SocketAddress addr;
        SocketChannel sock;
        // буффер массива, который мы передаём на сервер
        ByteBuffer buf; // = ByteBuffer.allocate(8192);

        try {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);
            // он хочет try с ресурсами для автозакрытия
            sock = SocketChannel.open();
            sock.connect(addr);
            while (sc.hasNext()) {
                run();
                // несколько раз посылаем
                buf = ByteBuffer.wrap(arr);
                sock.write(buf);
                // получаем данные
                buf.clear();
                arr = new byte[8192];
                buf = ByteBuffer.allocate(8192);
                sock.read(buf);

                // выводим данные response
                String s =Deserializer.readResp(new String(buf.array()).trim()).getMessage();
                System.out.println(s);
                buf.clear();
                /*
                for (int i = 0; i < len; i++) {
                    System.out.println(arr[i]);
                }

                 */
            }
            sock.close();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchCommandException e) {
            System.err.println(e.getMessage() + " (re-input команды пока что не поддерживается)");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
