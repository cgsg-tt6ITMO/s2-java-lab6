/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.managers.CollectionManager;
import resources.utility.Deserializer;

import java.util.Objects;

/**
 * Handle 'remove_by_id' method.
 */
public class RemoveByIdCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'remove_by_id' command.
     * @param collectionManager storage of the collection.
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "deletes the element with inputted id;");
        this.collectionManager = collectionManager;
    }

    /**
     * Deletes the element with inputted id.
     */
    @Override
    public Response execute(String args) {
        StringBuilder sb = new StringBuilder("REMOVE BY ID:\n");
        Long id = Deserializer.readLong(args);
        int begin = collectionManager.stack().size();
        if (begin != 0) {
            collectionManager.stack().removeIf(el -> Objects.equals(el.getId(), id));
            if (Objects.equals(collectionManager.stack().size(), begin)) {
                System.err.println("remove_by_id: there is no element with this id: " + id + ".\n");
                sb.append("If you wish to try again, type 'remove_by_id' one more time.\n\n");
            } else {
                sb.append("SUCCESS\n\n");
            }
        } else {
            sb.append("Collection doesn't have any elements\n\n");
        }
        return new Response(new String(sb));
    }
}
