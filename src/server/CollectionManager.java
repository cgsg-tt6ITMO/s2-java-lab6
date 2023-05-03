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
