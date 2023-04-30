/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server;

import resources.task.Route;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Stores the collection and info about it.
 */
public class CollectionManager {
    private static Stack<Route> stack = new Stack<>();
    private final String type;
    private final ZonedDateTime creationDate;
    private static Long lastId = 0L;

    // for 'add'

    /**
     * @return id of last element in collection (so that the new id should be lastId + 1).
     */
    public static Long getLastId() {
        return lastId;
    }

    /**
     * Changes the id of last element.
     * @param Id id of new last element.
     */
    public static void setLastId(Long Id) {
        lastId = Id;
    }

    // for 'info'

    /**
     * @return creationTime of the collection.
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return type of the collection for 'info' (Stack);
     */
    public String getType() {
        return type;
    }

    /**
     * @return instance of Stack with all elements.
     */
    public Stack<Route> stack() {
        return stack;
    }

    /**
     * Collection initialization.
     */
    public CollectionManager(Loader loader) {
        type = "Stack";
        creationDate = ZonedDateTime.now();
        stack = loader.load();
    }
}
