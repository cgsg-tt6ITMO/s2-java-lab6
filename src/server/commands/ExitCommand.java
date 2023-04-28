/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;

import static java.lang.System.exit;

/**
 * Handle 'exit' command.
 */
public class ExitCommand extends AbstractCommand implements Command {
    /**
     * Sets name and description of the command.
     */
    public ExitCommand() {
        super("exit", "interrupts the program without saving;");
    }

    /**
     * Aborts the program.
     */
    @Override
    public Response execute(String args) {
        exit(0);
        return new Response("EXIT...\n");
    }
}
