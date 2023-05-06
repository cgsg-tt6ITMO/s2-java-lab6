/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.managers.CollectionManager;

/**
 * Handle 'show' method.
 */
public class ShowCommand extends AbstractCommand implements Command{
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'show' command.
     * @param storage storage of the collection.
     */
    public ShowCommand(CollectionManager storage) {
        super("show", "prints the collection to screen;");
        this.collectionManager = storage;
    }

    /**
     * Prints the collection to screen.
     */
    @Override
    public Response execute(String args) {
        StringBuilder sb = new StringBuilder("SHOW COLLECTION:\n");
        if (collectionManager.stack().size() == 0) {
            sb.append("The collection is empty.");
            return new Response(new String(sb));
        }
        for (var el : collectionManager.stack()) {
            sb.append("ID: \t\t").append(el.getId()).append("\nName: \t\t").append(el.getName()).append("\nDistance: \t").append(el.getDistance()).append("\n\n");
        }
        return new Response(new String(sb));
    }
}
