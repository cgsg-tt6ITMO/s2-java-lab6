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
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Sends requests and gets responses from the server.
     * Displays server Responses.
     * @param args cmd arguments.
     */
    public static void main(String[] args) {
        InetAddress host;
        // need to change the number after client disconnection
        int port = 6001;
        SocketAddress addr;
        ByteBuffer buf;
        client.CommandHandler commandHandler = new client.CommandHandler();

        try (SocketChannel sock = SocketChannel.open()) {
            host = InetAddress.getLocalHost();
            addr = new InetSocketAddress(host, port);
            sock.connect(addr);
            while (sc.hasNext()) {
                try {
                    arr = commandHandler.run(sc);
                    // send data
                    buf = ByteBuffer.wrap(arr);
                    sock.write(buf);
                    // get data
                    buf.clear();
                    arr = new byte[8192];
                    buf = ByteBuffer.allocate(8192);
                    sock.read(buf);
                    // display response data
                    String answ = new String(buf.array()).trim();
                    if (answ.charAt(0) == '[') {
                        for (var i : Deserializer.responses(answ)) {
                            System.out.println(i.getMessage());
                            if (i.getMessage().equals("EXIT...\n")) {
                                sock.close();
                                throw new ConnectException("exit");
                            }
                        }
                    } else {
                        Response response = Deserializer.readResp(answ);
                        System.out.println(response.getMessage());
                        if (response.getMessage().equals("EXIT...\n")) {
                            sock.close();
                            throw new ConnectException("exit");
                        }
                    }
                    buf.clear();
                } catch (UnknownHostException e) {
                    System.err.println(e.getMessage());
                } catch (ConnectException e) {
                    sock.close();
                    throw new ConnectException("Client activity was terminated...");
                } catch (NoSuchCommandException e) {
                    System.err.println(e.getMessage() + " (re-input команды пока что не поддерживается)");
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (ConnectException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchCommandException e) {
            System.err.println(e.getMessage() + " (re-input команды пока что не поддерживается)");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
