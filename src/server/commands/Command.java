/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

/**
 * Interface of all server.commands.
 */
public interface Command {
    /**
     * Run the command.
     */
    void execute(String args);
}
