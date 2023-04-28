/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CollectionManager;
import server.CommandManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handle 'insert_at' method.
 */
public class InsertAtCommand extends AbstractCommand implements Command {
    private final CollectionManager storage;
    private final CommandManager cm;

    /**
     * Set name and description for 'insert_at' command.
     * @param collectionManager storage of the collection.
     */
    public InsertAtCommand(CollectionManager collectionManager, CommandManager commandManager) {
        super("insert_at", "insert an element into place of inputted id;");
        this.storage = collectionManager;
        this.cm = commandManager;
    }

    /**
     * Inserts an element into a place of index you client.input.
     */
    @Override
    public Response execute(String args) {
        Scanner scanner = new Scanner(args);
        String idStr = scanner.nextLine();
        scanner.close();
        long id = Long.parseLong(idStr);
        StringBuilder json = new StringBuilder();

        for (int i = idStr.length(); i < args.length(); i++) {
            json.append(args.charAt(i));
        }

        int n = storage.stack().size();

        boolean loop = true;
        do {
            try {
                if (id > CollectionManager.getLastId()) {
                    CollectionManager.setLastId(id - 1);
                    cm.getCommands().get("add").execute(json.toString());
                }
                else if (id < 1) {
                    System.err.println("insert_at: TODO надо кинуть Response ошибка Incorrect id: less than 1\n");
                    throw new InputMismatchException();
                } else {
                    boolean exist = false;
                    // check if this index exists
                    for (var el : storage.stack()) {
                        if (el.getId().equals(id)) {
                            exist = true;
                        }
                        if (exist) {
                            el.setId(el.getId() + 1);
                        }
                    }
                    cm.getCommands().get("add").execute(json.toString());
                    storage.stack().peek().setId(id);
                }
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception){
                loop = true;
            }
        } while (loop);

        if (storage.stack().size() > n) {
            cm.getCommands().get("sort").execute("");
            return new Response("INSERT AT:\nInserted successfully.\n");
        }
        cm.getCommands().get("sort").execute("");
        return new Response("INSERT AT:\n");
    }
}

