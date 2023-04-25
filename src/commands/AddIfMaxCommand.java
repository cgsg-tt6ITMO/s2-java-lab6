/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.Input;
import task.Route;

/**
 * Handle 'add_if_max' method.
 */
public class AddIfMaxCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;
    private final Input inputManager;

    /**
     * Set name and description for 'add_if_max' command.
     * @param collectionManager storage of the collection.
     */
    public AddIfMaxCommand(CollectionManager collectionManager, Input input) {
        super("add_if_max", "adds the element if it is larger than every element in collection;");
        this.collectionManager = collectionManager;
        this.inputManager = input;
    }

    /**
     * Adds the element if it is larger than every element stored in collection.
     */
    @Override
    public void execute() {
        System.out.println("ADD IF MAX:");
        Route route = inputManager.inpRoute();

        boolean flag = true;
        for (var el : collectionManager.stack()) {
            if (route.compareTo(el) != 1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            collectionManager.stack().add(route);
            System.out.println("NEW ELEMENT ADDED SUCCESSFULLY\n");
        }
        else {
            System.out.println("The element is not max, so it was not added.\n");
        }
    }
}
