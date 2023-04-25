/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.JSONManager;

/**
 * Handle 'save' method.
 */
public class SaveCommand extends AbstractCommand implements Command {
    private final String path;
    private final CollectionManager collectionManager;
    private final JSONManager jsonManager = new JSONManager();

    /**
     * Set name and description for 'save' command.
     * @param path file where to save.
     * @param collectionManager storage of the collection.
     */
    public SaveCommand(String path, CollectionManager collectionManager) {
        super("save", "saves collection to json file.;");
        this.path = path;
        this.collectionManager = collectionManager;
    }

    /**
     * Save collection to json file.
     */
    @Override
    public void execute() {
        jsonManager.write(collectionManager.stack(), path);
        System.out.println("SAVED TO JSON SUCCESSFULLY");
    }
}
