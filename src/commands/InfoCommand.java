/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;

/**
 * Handle 'info' method.
 */
public class InfoCommand extends AbstractCommand implements Command{
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'info' command.
     * @param collectionManager storage of the collection.
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "prints info about the collection;");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints to screen all the attributes of the collection.
     */
    @Override
    public void execute() {
        var date = collectionManager.getCreationDate();
        System.out.println("COLLECTION INFO:\n"
                + "creation time: " + date.getHour() + ":" + (date.getMinute() > 9 ? date.getMinute() : "0" + date.getMinute())
                + "\ntype of storage: " + collectionManager.getType() + "\nnumber of elements: " + collectionManager.stack().size() + "\n");
    }
}
