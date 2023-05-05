/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CollectionManager;
import resources.task.Route;

import java.util.Comparator;

/**
 * Sorts the collection.
 * (Is needed only after 'insert_at', so it doesn't have description and name)
 */
public class SortingCommand implements Command {
    private final CollectionManager storage;

    /**
     * @param collectionManager storage of the collection.
     */
    public SortingCommand(CollectionManager collectionManager) {
        storage = collectionManager;
    }

    /**
     * Sorts the Stack by id.
     */
    @Override
    public Response execute(String args) {
        Comparator<Route> routeComparator = Comparator.comparing(Route::getId);
        storage.stack().sort(routeComparator);
        return new Response();
    }
}
