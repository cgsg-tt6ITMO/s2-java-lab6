/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CommandManager;

import static java.lang.System.exit;

/**
 * Handle 'exit' command.
 */
public class ExitCommand extends AbstractCommand implements Command {
    private final CommandManager commandManager;

    /**
     * Sets name and description of the command.
     */
    public ExitCommand(CommandManager cm) {
        super("exit", "interrupts the program without saving;");
        this.commandManager = cm;
    }

    /**
     * Aborts the program. And saves data before aborting.
     */
    @Override
    public Response execute(String args) {
        commandManager.getCommands().get("save").execute("");
        exit(0);
        return new Response("EXIT...\n");
    }
}
