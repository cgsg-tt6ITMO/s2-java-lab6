/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CollectionManager;
import resources.task.Route;
import resources.utility.Deserializer;

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
    public Response execute(String args) {
        Route route = Deserializer.readRoute(args);
        int n = cm.stack().size();
        StringBuilder sb = new StringBuilder("REMOVE LOWER:\n");
        for (var el : cm.stack()) {
            if (route.compareTo(el) > 0) {
                cm.stack().remove(el);
            }
        }
        // analyse results
        if (n > cm.stack().size()) {
            sb.append("SUCCESSFUL REMOVE\n\n");
        } else {
            sb.append("No elements were less than inputted.\n\n");
        }
        return new Response(new String(sb));
    }
}
