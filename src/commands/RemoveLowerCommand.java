/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.Input;
import task.Route;

/**
 * Handle 'remove_lower' method.
 */
public class RemoveLowerCommand extends AbstractCommand implements Command {
    private final CollectionManager cm;
    private final Input im;

    /**
     * Set name and description for 'remove_lower' command.
     * @param collectionManager storage of the collection.
     */
    public RemoveLowerCommand(CollectionManager collectionManager, Input inputManager) {
        super("remove_lower", "removes all elements lower than inputted;");
        this.im = inputManager;
        this.cm = collectionManager;
    }

    /**
     * Removes all elements lower than inputted.
     */
    @Override
    public void execute() {
        int n = cm.stack().size();
        System.out.println("REMOVE LOWER:");
        Route route = im.inpRoute();
        for (var el : cm.stack()) {
            if (route.compareTo(el) > 0) {
                cm.stack().remove(el);
            }
        }
        // analyse results
        if (n > cm.stack().size()) {
            System.out.println("SUCCESSFUL REMOVE\n");
        } else {
            System.out.println("No elements were less than inputted.\n");
        }
    }
}
