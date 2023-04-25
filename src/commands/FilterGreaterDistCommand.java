/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.Input;

/**
 * Handle 'filter_greater_than_distance' method.
 */
public class FilterGreaterDistCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;
    private final Input inputManager;

    /**
     * Set name and description for 'filter_greater_than_distance' command.
     * @param collectionManager storage of the collection.
     */
    public FilterGreaterDistCommand(CollectionManager collectionManager, Input inputManager) {
        super("filter_greater_than_distance", "prints elements with distance greater than the inputted one;");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Shows elements with distance greater than the inputted one.
     */
    @Override
    public void execute() {
        System.out.println("ROUTES WITH DIST GREATER THAN INPUTTED:\nInput distance (Double), and I will output all routes with greater one:");
        Double distance = inputManager.inpDouble("distance");
        for (var el : collectionManager.stack()) {
            if (el.getDistance() > distance) {
                System.out.println("ID: \t\t" + el.getId() + "\nName: \t\t" + el.getName()
                        + "\nDistance: \t" + el.getDistance() + "\n");
            }
        }
        System.out.print('\n');
    }
}
