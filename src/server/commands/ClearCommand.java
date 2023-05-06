/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.managers.CollectionManager;

/**
 * Handle 'clear' method.
 */
public class ClearCommand extends AbstractCommand implements Command{
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'clear' command.
     * @param collectionManager storage of the collection.
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "deletes all the elements of the collection;");
        this.collectionManager = collectionManager;
    }

    /**
     * Deletes all the elements in collection.
     */
    @Override
    public Response execute(String args) {
        collectionManager.stack().clear();
        return new Response("CLEAR:\nNow the collection is empty.\n");
    }
}
