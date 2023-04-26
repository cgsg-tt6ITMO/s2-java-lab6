/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import server.CollectionManager;
import server.Deserializer;

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
    public void execute(String args) {
        System.out.println("ROUTES WITH DIST GREATER THAN INPUTTED:\n");
        Double distance = Deserializer.readDouble(args);
        for (var el : collectionManager.stack()) {
            if (el.getDistance() > distance) {
                System.out.println("ID: \t\t" + el.getId() + "\nName: \t\t" + el.getName()
                        + "\nDistance: \t" + el.getDistance() + "\n");
            }
        }
        System.out.print('\n');
    }
}
