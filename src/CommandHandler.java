package client;

import client.input_manager.AskInputManager;
import client.input_manager.Input;
import resources.exceptions.NoSuchCommandException;
import resources.utility.Request;
import resources.utility.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Даже не знаю, лучше передавать Scanner в run или в конструктор.
 * Если в run, то вообще этот метод можно сделать static.
 */
public class CommandHandler {

    public CommandHandler() {}

    /**
     * Method that handles input logic.
     * It handles the number of arguments command needs.
     */
    public byte[] run(Scanner sc) {
        Input im = new AskInputManager(sc);
        Request r = null;
        byte[] arr;

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
            case "remove_by_id" -> r = (new Request(command, Serializer.longSer(im.inpLong("id"))));
            case "update", "insert_at" ->
                    r = (new Request(command, Serializer.longRouteSer(im.inpLong("id"), im.inpRoute())));
            case "filter_greater_than_distance" ->
                    r = (new Request(command, Serializer.doubleSer(im.inpDouble("distance"))));
            case "execute_script" -> {
                ExecuteScript executeScript = new ExecuteScript(im);
                String requests = executeScript.makeReq();
                return requests.getBytes();
            }
            default -> {
                throw new NoSuchCommandException(command + " doesn't exist.");
            }
        }
        if (r != null) {
            arr = Serializer.objSer(r).getBytes(StandardCharsets.UTF_8);
        } else {
            arr = new byte[8192];
            System.err.println("arr is null");
        }
        //len = arr.length;
        return arr;
    }
}
