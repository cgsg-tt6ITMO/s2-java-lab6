/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CommandManager;

/**
 * Handle 'help' method.
 */
public class HelpCommand extends AbstractCommand implements Command {
    private final CommandManager commandManager;

    /**
     * Set name and description for 'help' command.
     * @param cm - CommandManager.
     */
    public HelpCommand(CommandManager cm) {
        super("help", "prints list of all commands and their descriptions;");
        this.commandManager = cm;
    }

    /**
     * Prints all commands and their descriptions.
     */
    @Override
    public void execute() {
        System.out.println("COMMANDS AVAILABLE:");
        for (var key : commandManager.getCommands().keySet()) {
            if (commandManager.getCommands().get(key).toString().endsWith(";")) {
                System.out.println(commandManager.getCommands().get(key));
            }
        }
        System.out.print('\n');
    }
}
