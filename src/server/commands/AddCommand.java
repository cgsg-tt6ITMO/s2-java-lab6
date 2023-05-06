/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.task.Route;
import resources.utility.Response;
import server.managers.CollectionManager;
import resources.utility.Deserializer;

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
    public Response execute(String args) {
        Route r = Deserializer.readRoute(args);
        collectionManager.stack().add(r);
        return new Response("ADD ELEMENT:\nNEW ELEMENT ADDED SUCCESSFULLY\n");
    }
}
