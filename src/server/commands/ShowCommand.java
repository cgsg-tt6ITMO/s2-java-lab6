/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import server.CollectionManager;

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
    public void execute(String args) {
        System.out.println("SHOW COLLECTION:");
        if (collectionManager.stack().size() == 0) {
            System.out.println("The collection is empty.");
            return;
        }
        for (var el : collectionManager.stack()) {
            System.out.println("ID: \t\t" + el.getId() + "\nName: \t\t" + el.getName()
                    + "\nDistance: \t" + el.getDistance() + "\n");
        }
    }
}
