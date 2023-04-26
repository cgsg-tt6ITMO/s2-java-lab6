/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

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
    public void execute(String args) {
        Scanner scanner = new Scanner(args);
        String idStr = scanner.nextLine();
        scanner.close();
        long id = Long.parseLong(idStr);
        String json = "";

        for (int i = idStr.length(); i < args.length(); i++) {
            json += args.charAt(i);
        }

        System.out.println("INSERT AT:");
        int n = storage.stack().size();

        boolean loop = true;
        do {
            try {
                if (id > CollectionManager.getLastId()) {
                    CollectionManager.setLastId(id - 1);
                    cm.getCommands().get("add").execute(json);
                }
                else if (id < 1) {
                    System.err.println("insert_at: Incorrect id: less than 1\n");
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
                    cm.getCommands().get("add").execute(json);
                    storage.stack().peek().setId(id);
                }
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception){
                loop = true;
            }
        } while (loop);

        if (storage.stack().size() > n) {
            System.out.println("Inserted successfully.\n");
        }
        cm.getCommands().get("sort").execute("");
    }
}

