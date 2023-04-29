/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;
import server.CommandManager;
import server.Saver;

import static java.lang.System.exit;

/**
 * Handle 'exit' command.
 */
public class ExitCommand extends AbstractCommand implements Command {
    private final Saver saver;

    /**
     * Sets name and description of the command.
     */
    public ExitCommand(Saver saver) {
        super("exit", "interrupts the program without saving;");
        this.saver = saver;
    }

    /**
     * Aborts the program. And saves data before aborting.
     */
    @Override
    public Response execute(String args) {
        saver.save();
        exit(0);
        return new Response("EXIT...\n");
    }
}
