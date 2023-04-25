/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.CommandManager;
import management.Input;

import java.util.InputMismatchException;

/**
 * Handle 'insert_at' method.
 */
public class InsertAtCommand extends AbstractCommand implements Command {
    private final Input im;
    private final CollectionManager storage;
    private final CommandManager cm;

    /**
     * Set name and description for 'insert_at' command.
     * @param collectionManager storage of the collection.
     */
    public InsertAtCommand(CollectionManager collectionManager, Input inputManager,
                           CommandManager commandManager) {
        super("insert_at", "insert an element into place of inputted id;");
        this.storage = collectionManager;
        this.cm = commandManager;
        this.im = inputManager;
    }

    /**
     * Inserts an element into a place of index you input.
     */
    @Override
    public void execute() {
        System.out.println("INSERT AT:");
        int n = storage.stack().size();

        boolean loop = true;
        do {
            System.out.println("Input index");
            Long id = im.inpLong("index");
            try {
                // if id > lasID, just input normally
                if (id > CollectionManager.getLastId()) {
                    CollectionManager.setLastId(id - 1);
                    cm.getCommands().get("add").execute();
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
                    cm.getCommands().get("add").execute();
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
        cm.getCommands().get("sort").execute();
    }
}

