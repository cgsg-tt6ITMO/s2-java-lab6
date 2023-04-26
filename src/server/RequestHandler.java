package server;

import resources.utility.Request;
import client.input_manager.AskInputManager;

import java.util.Locale;
import java.util.Scanner;

public class RequestHandler {
    String envVar = "JAVA_LABA_6";
    CollectionManager storage = new CollectionManager(System.getenv().get(envVar));
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    AskInputManager askInputManager = new AskInputManager(scanner);
    CommandManager commandManager = new CommandManager(storage, askInputManager);

    // потом сделать enum с ERROR, SUCCESS, ...
    public boolean run(Request r) {
        commandManager.getCommands().get(r.name()).execute(r.args());
        return true;
    }
}
