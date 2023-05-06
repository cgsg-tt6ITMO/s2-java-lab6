/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.managers.CollectionManager;
import resources.task.Route;
import resources.utility.Deserializer;

/**
 * Handle 'add_if_max' method.
 */
public class AddIfMaxCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'add_if_max' command.
     * @param collectionManager storage of the collection.
     */
    public AddIfMaxCommand(CollectionManager collectionManager) {
        super("add_if_max", "adds the element if it is larger than every element in collection;");
        this.collectionManager = collectionManager;
    }

    /**
     * Adds the element if it is larger than every element stored in collection.
     */
    @Override
    public Response execute(String args) {
        Route route = Deserializer.readRoute(args);

        boolean flag = true;
        for (var el : collectionManager.stack()) {
            if (route.compareTo(el) != 1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            collectionManager.stack().add(route);
            return new Response("ADD IF MAX:\nNEW ELEMENT ADDED SUCCESSFULLY\n");
        }
        return new Response("ADD IF MAX:\nThe element is not max, so it was not added.\n");

    }
}
