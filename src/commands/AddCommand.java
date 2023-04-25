/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.Input;

/**
 * Handle 'add' method.
 */
public class AddCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;
    private final Input inputManager;

    /**
     * Set name and description for 'add' command.
     * @param collectionManager storage of the collection.
     */
    public AddCommand(CollectionManager collectionManager, Input inputManager) {
        super("add", "adds your element to the collection;");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Adds one element from console to the collection.
     */
    @Override
    public void execute() {
        System.out.println("ADD ELEMENT:");
        collectionManager.stack().add(inputManager.inpRoute());
        System.out.println("NEW ELEMENT ADDED SUCCESSFULLY\n");
    }
}
