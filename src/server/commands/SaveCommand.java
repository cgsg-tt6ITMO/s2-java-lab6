/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CollectionManager;
import resources.utility.JSONManager;

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
    public Response execute(String args) {
        jsonManager.write(collectionManager.stack(), path);
        return new Response("SAVED TO JSON SUCCESSFULLY\n");
    }
}
