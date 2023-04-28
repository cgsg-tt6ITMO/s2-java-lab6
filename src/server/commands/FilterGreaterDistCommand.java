/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CollectionManager;
import resources.utility.Deserializer;

/**
 * Handle 'filter_greater_than_distance' method.
 */
public class FilterGreaterDistCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'filter_greater_than_distance' command.
     * @param collectionManager storage of the collection.
     */
    public FilterGreaterDistCommand(CollectionManager collectionManager) {
        super("filter_greater_than_distance", "prints elements with distance greater than the inputted one;");
        this.collectionManager = collectionManager;
    }

    /**
     * Shows elements with distance greater than the inputted one.
     */
    @Override
    public Response execute(String args) {
        StringBuilder sb = new StringBuilder("ROUTES WITH DIST GREATER THAN INPUTTED:\n");
        Double distance = Deserializer.readDouble(args);
        for (var el : collectionManager.stack()) {
            if (el.getDistance() > distance) {
                sb.append("ID: \t\t").append(el.getId()).append("\nName: \t\t").append(el.getName()).append("\nDistance: \t").append(el.getDistance()).append("\n\n");
            }
        }
        sb.append('\n');
        return new Response(new String(sb));
    }
}
