/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import task.Route;

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
        this.storage = collectionManager;
    }

    /**
     * Swaps two elements while sorting.
     * @param a index of the first element to be swapped with the second;
     * @param b index of the second element.
     */
    private void swap(int a, int b) {
        Route tmp = storage.stack().get(a);
        storage.stack().set(a, storage.stack().get(b));
        storage.stack().set(b, tmp);
    }

    /**
     * Sorts the Stack by id.
     */
    @Override
    public void execute() {
        for (int j = 0; j < storage.stack().size() - 1; j++) {
            for (int i = j + 1; i < storage.stack().size(); i++) {
                if (storage.stack().get(i).getId() < storage.stack().get(i - 1).getId()) {
                    swap(i, j);
                }
            }
        }
    }
}
