/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.managers.CollectionManager;

import java.util.HashMap;

/**
 * Handle 'group_counting_by_from' method.
 */
public class GroupByFromCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Set name and description for 'group_counting_by_from' command.
     * @param collectionManager storage of the collection.
     */
    public GroupByFromCommand(CollectionManager collectionManager) {
        super("group_counting_by_from", "outputs numbers of elements with the same from;");
        this.collectionManager = collectionManager;
    }

    /**
     * Outputs numbers of elements with the same from.
     */
    @Override
    public Response execute(String args) {
        StringBuilder sb = new StringBuilder("GROUP COUNTING BY 'FROM':");
        HashMap<String, Integer> grouped = new HashMap<>();

        for (var el : collectionManager.stack()) {
            String from = el.getFrom().toString();
            if (!grouped.containsKey(from)) {
                grouped.put(from, 1);
            }
            else {
                grouped.put(from, grouped.get(from) + 1);
            }
        }
        for (var el : grouped.keySet()) {
            sb.append("Location from: ").append(el).append("\nNumber of elements with this from: ").append(grouped.get(el)).append("\n");
        }
        return new Response(new String(sb));
    }
}
