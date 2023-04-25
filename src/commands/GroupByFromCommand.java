/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;

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
    public void execute() {
        System.out.println("GROUP COUNTING BY 'FROM':");
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
            System.out.println("Location from: " + el + "\nNumber of elements with this from: " + grouped.get(el));
        }
    }
}
