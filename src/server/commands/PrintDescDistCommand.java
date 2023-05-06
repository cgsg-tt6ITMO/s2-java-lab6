/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.managers.CollectionManager;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Handle 'print_field_descending_distance' method.
 */
public class PrintDescDistCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'print_field_descending_distance' command.
     * @param collectionManager storage of the collection.
     */
    public PrintDescDistCommand(CollectionManager collectionManager) {
        super("print_field_descending_distance", "prints distances in descending order;");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints distances in descending order.
     */
    @Override
    public Response execute(String args) {
        StringBuilder sb = new StringBuilder("ALL DISTANCES IN DESCENDING ORDER:");
        ArrayList<Double> distances = new ArrayList<>();
        for (var el : collectionManager.stack()) {
            distances.add(el.getDistance());
        }
        distances.sort(Comparator.comparing(el -> el));
        for (int i = distances.size() - 1; i >= 0; i--) {
            sb.append(distances.get(i)).append("\n");
        }
        sb.append('\n');
        return new Response(new String(sb));
    }
}
