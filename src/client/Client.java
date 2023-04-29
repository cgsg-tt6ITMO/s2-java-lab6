/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import resources.exceptions.NoSuchCommandException;
import resources.utility.Deserializer;
import resources.utility.Request;
import client.input_manager.AskInputManager;
import client.input_manager.Input;
import resources.utility.Response;
import resources.utility.Serializer;

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
            case "group_counting_by_from",
                    "help", "info", "show",
                    "print_field_descending_distance",
                    "clear", "exit" -> {
                r = (new Request(command, ""));
            }
            case "add", "remove_lower", "add_if_max" -> {
                // нужно чекнуть, что правильный сканнер для execute_script
                r = (new Request(command, Serializer.objSer(im.inpRoute())));
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
            arr = Serializer.objSer(r).getBytes(StandardCharsets.UTF_8);
        }
        else {
            arr = new byte[8192];
            System.err.println("arr is null");
        }
        len = arr.length;
    }

    public static void main(String[] args) {
        InetAddress host;
        int port = 6000;
        SocketAddress addr;
        ByteBuffer buf;

        try (SocketChannel sock = SocketChannel.open()) {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);
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
                Response response = Deserializer.readResp(new String(buf.array()).trim());
                System.out.println(response.getMessage());
                buf.clear();
            }
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
