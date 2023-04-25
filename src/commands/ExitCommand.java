/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

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
    public void execute() {
        exit(0);
        System.out.println("EXIT...\n");
    }
}
