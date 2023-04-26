/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.task.Route;
import server.CollectionManager;
import client.input_manager.Input;
import server.Deserializer;

/**
 * Handle 'add' method.
 */
public class AddCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'add' command.
     * @param collectionManager storage of the collection.
     */
    public AddCommand(CollectionManager collectionManager) {
        super("add", "adds your element to the collection;");
        this.collectionManager = collectionManager;
    }

    /**
     * Adds one element from console to the collection.
     */
    @Override
    public void execute(String args) {
        Route r = Deserializer.readRoute(args);
        System.out.println("ADD ELEMENT:");
        collectionManager.stack().add(r);
        System.out.println("NEW ELEMENT ADDED SUCCESSFULLY\n");
    }
}
