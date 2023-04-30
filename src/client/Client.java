/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import resources.exceptions.NoSuchCommandException;
import resources.utility.Deserializer;
import resources.utility.Response;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * This class handles the commands that the client inputs in loop.
 */
public class Client {
    private static byte[] arr;
    private static int len;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        InetAddress host;
        int port = 6000;
        SocketAddress addr;
        ByteBuffer buf;
        client.CommandHandler commandHandler = new client.CommandHandler();

        try (SocketChannel sock = SocketChannel.open()) {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);
            sock.connect(addr);
            while (sc.hasNext()) {
                arr = commandHandler.run(sc);
                System.out.println(new String(arr));
                len = arr.length;
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
