/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import server.CollectionManager;
import client.input_manager.Input;
import resources.task.Route;
import server.Deserializer;

/**
 * Handle 'remove_lower' method.
 */
public class RemoveLowerCommand extends AbstractCommand implements Command {
    private final CollectionManager cm;

    /**
     * Set name and description for 'remove_lower' command.
     * @param collectionManager storage of the collection.
     */
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "removes all elements lower than inputted;");
        this.cm = collectionManager;
    }

    /**
     * Removes all elements lower than inputted.
     */
    @Override
    public void execute(String args) {
        Route route = Deserializer.readRoute(args);
        int n = cm.stack().size();
        System.out.println("REMOVE LOWER:");
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
