/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server;

import server.commands.*;

import java.util.HashMap;

/**
 * Storage for all commands.
 */
public class CommandManager {
    private static final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Adds instances of all commands to list.
     */
    public CommandManager() {
        String envVar = "JAVA_LABA_6";
        Loader loader = new Loader(System.getenv().get(envVar));
        CollectionManager collectionManager = new CollectionManager(loader);
        Saver saver = new Saver(collectionManager.stack(), System.getenv().get(envVar));
        SortingCommand sort = new SortingCommand(collectionManager);

        HelpCommand help = new HelpCommand(this);
        InfoCommand info = new InfoCommand(collectionManager);
        ShowCommand show = new ShowCommand(collectionManager);
        ClearCommand clear = new ClearCommand(collectionManager);
        AddCommand add = new AddCommand(collectionManager);
        ExitCommand exit = new ExitCommand(saver);
        PrintDescDistCommand descDist = new PrintDescDistCommand(collectionManager);
        FilterGreaterDistCommand filterGreaterDist = new FilterGreaterDistCommand(collectionManager);
        AddIfMaxCommand addIfMax = new AddIfMaxCommand(collectionManager);
        GroupByFromCommand groupByFrom = new GroupByFromCommand(collectionManager);
        RemoveByIdCommand deleteById = new RemoveByIdCommand(collectionManager);
        RemoveLowerCommand removeLower = new RemoveLowerCommand(collectionManager);
        InsertAtCommand insertAt = new InsertAtCommand(collectionManager, this);
        UpdateCommand update = new UpdateCommand(collectionManager);

        commands.put("sort", sort);
        commands.put(help.getName(), help);
        commands.put(info.getName(), info);
        commands.put(show.getName(), show);
        commands.put(clear.getName(), clear);
        commands.put(add.getName(), add);
        commands.put(exit.getName(), exit);
        commands.put(descDist.getName(), descDist);
        commands.put(filterGreaterDist.getName(), filterGreaterDist);
        commands.put(addIfMax.getName(), addIfMax);
        commands.put(groupByFrom.getName(), groupByFrom);
        commands.put(deleteById.getName(), deleteById);
        commands.put(removeLower.getName(), removeLower);
        commands.put(insertAt.getName(), insertAt);
        commands.put(update.getName(), update);

    }

    /**
     * @return ArrayList of all server.commands.
     */
    public HashMap<String, Command> getCommands() {
        return commands;
    }

}
