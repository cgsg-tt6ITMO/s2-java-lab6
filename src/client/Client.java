/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import client.managers.CommandHandler;
import client.managers.DisplayResponse;
import resources.exceptions.NoSuchCommandException;
import resources.utility.Deserializer;
import resources.utility.Request;
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
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Sends requests and gets responses from the server.
     * Displays server Responses.
     * @param args cmd arguments.
     */
    public static void main(String[] args) {
        InetAddress host;
        // need to change the number after client disconnection
        int port = 6000;
        SocketAddress addr;
        byte[] arr = new byte[8192];
        ByteBuffer buf = ByteBuffer.wrap(arr);
        CommandHandler commandHandler = new CommandHandler(sc, 1);
        boolean start = false;

        try (SocketChannel sock = SocketChannel.open()) {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);
            sock.connect(addr);

            System.out.println("Type 'start' to begin...");

            if (!start && sc.nextLine().equals("start")) {
                byte[] array;
                ByteBuffer buffer;
                Request r = new Request("start", "");
                array = Serializer.objSer(r).getBytes(StandardCharsets.UTF_8);
                buffer = ByteBuffer.wrap(array);
                sock.write(buffer);

                buffer.clear();
                buffer = ByteBuffer.allocate(8192);
                sock.read(buffer);

                Response resp = Deserializer.readResp(new String(buffer.array()));

                commandHandler = new CommandHandler(sc, Integer.parseInt(resp.getMessage()));
                System.out.println("Start successful");

                buffer.clear();
                start = true;
            }



            while (sc.hasNext()) {
                try {
                    arr = commandHandler.run();
                    buf = ByteBuffer.wrap(arr);
                    System.out.println("we send " + new String(arr));
                    sock.write(buf);

                    buf.clear();
                    buf = ByteBuffer.allocate(8192);
                    sock.read(buf);
                    DisplayResponse.display(buf.array());
                    buf.clear();
                } catch (UnknownHostException e) {
                    System.err.println(e.getMessage());
                } catch (ConnectException e) {
                    sock.close();
                    throw new ConnectException("Client activity was terminated...");
                } catch (NoSuchCommandException e) {
                    System.err.println(e.getMessage() + " (try again)");
                }
            }
        } catch (UnknownHostException | SocketException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
