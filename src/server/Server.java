/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server;

/**
 * Server. Handles command logic.
 */
public class Server {
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
        ServerLogic serverLogic = new ServerLogic();
        serverLogic.run();
    }
}
