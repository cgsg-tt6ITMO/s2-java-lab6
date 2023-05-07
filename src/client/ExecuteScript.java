/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client;

import client.input_manager.Input;
import client.validators.PathValidator;
import client.validators.ValidateException;
import resources.exceptions.InfiniteLoopException;
import resources.utility.Deserializer;
import resources.utility.Request;
import resources.utility.Serializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This command needs its own class because it just calls other commands.
 * It does not send its own requests, only commands written inside do.
 */
public class ExecuteScript {
    private static Input inputManager;
    private final ExecuteScriptHandler handler = new ExecuteScriptHandler();

    public ExecuteScript(Input inpMan) {
        inputManager = inpMan;
    }

    /**
     * Может, оно будет возвращать массив уже сериализованных строк?
     * а не ArrayList<Request>
     */
    public String makeReq(int stackSize) {
        int numOfCommands = 100;
        Request[] reqs = new Request[numOfCommands];

        boolean loop = true, wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("execute script: input filename again:");
                }
                String path = inputManager.inpString("script file name", new PathValidator());

                // check infinite loop
                if (handler.getFiles().isEmpty()) {
                    handler.fadd(path);
                } else {
                    if (!handler.getFiles().contains(path)) {
                        handler.fadd(path);
                    } else {
                        // if path in files, exception
                        throw new InfiniteLoopException("execute_script");
                    }
                }

                File file = new File(path);
                Scanner fileScanner = new Scanner(file);
                for (int i = 0; i < numOfCommands; i++) {
                    if (fileScanner.hasNext()) {
                        stackSize += 1;
                        Request st = Deserializer.readReq(new String(
                                new client.CommandHandler(fileScanner, stackSize).run()));
                        reqs[i] = st;
                    }
                }

                loop = false;
            } catch (ValidateException | FileNotFoundException e) {
                loop = true;
                wasErr = true;
            }
            catch (InfiniteLoopException e) {
                System.err.println(e.getMessage());
                loop = false;
            }
        } while (loop);

        return Serializer.objSer(reqs);
    }
}
