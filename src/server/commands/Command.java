/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.utility.Response;

/**
 * Interface of all server.commands.
 */
public interface Command {
    /**
     * Run the command.
     */
    Response execute(String args);
}
