/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import resources.utility.Request;
import client.input_manager.AskInputManager;
import client.input_manager.Input;
import server.RequestHandler;

import java.util.Scanner;

/**
 * This class handles the server.commands that the client inputs in loop.
 */
public class Client {

    /**
     * This method is for inputting client's server.commands and handling them.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Input im = new AskInputManager(sc);
        RequestHandler requestHandler = new RequestHandler();
        Serializer ser = new Serializer();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            switch (command) {
                // save должно совершаться на сервере
                case "group_counting_by_from",
                    "help", "info", "show",
                    "print_field_descending_distance",
                    "clear", "exit" -> {
                    requestHandler.run(new Request(command, null));
                }
                case "add", "remove_lower", "add_if_max" -> {
                    // нужно чекнуть, что правильный сканнер для execute_script
                    requestHandler.run(new Request(command, ser.routeSer(im.inpRoute())));
                }
                case "remove_by_id" ->
                    requestHandler.run(new Request(command, ser.longSer(im.inpLong("id"))));
                case "update", "insert_at" ->
                    requestHandler.run(new Request(command, ser.longRouteSer(im.inpLong("id"), im.inpRoute())));
                case "filter_greater_than_distance" ->
                    requestHandler.run(new Request(command, ser.doubleSer(im.inpDouble("distance"))));
                case "execute_script" -> {
                    //requestHandler.run(new Request(command, im.inpString("file name")));
                }
                default -> {
                    System.err.println(command + " doesn't exist.");
                }
            }
        }
    }
}
