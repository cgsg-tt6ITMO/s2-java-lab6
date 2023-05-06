/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.handlers;

import resources.task.Route;
import resources.utility.Response;
import server.commands.Command;

import java.util.Stack;

public class GetDefaultCollectionSize implements Command {
    private final Stack<Route> routeStack;
    public GetDefaultCollectionSize(Stack<Route> collection) {
        this.routeStack = collection;
    }

    @Override
    public Response execute(String args) {
        return new Response(String.valueOf(routeStack.size()));
    }
}
