/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.Input;

import java.util.Objects;

/**
 * Handle 'remove_by_id' method.
 */
public class RemoveByIdCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;
    private final Input inputManager;

    /**
     * Set name and description for 'remove_by_id' command.
     * @param collectionManager storage of the collection.
     */
    public RemoveByIdCommand(CollectionManager collectionManager, Input inputManager) {
        super("remove_by_id", "deletes the element with inputted id;");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Deletes the element with inputted id.
     */
    @Override
    public void execute() {
        System.out.println("REMOVE BY ID:");
        Long id = inputManager.inpLong("index");
        int begin = collectionManager.stack().size();
        if (begin != 0) {
            collectionManager.stack().removeIf(el -> Objects.equals(el.getId(), id));
            if (Objects.equals(collectionManager.stack().size(), begin)) {
                System.err.println("remove_by_id: there is no element with this id: " + id + ".");
                System.out.println("If you wish to try again, type 'remove_by_id' one more time.\n");
            } else {
                System.out.println("SUCCESS\n");
            }
        } else {
            System.err.println("Collection doesn't have any elements\n");
        }
    }
}
