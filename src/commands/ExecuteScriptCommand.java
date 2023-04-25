/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import exceptions.InfiniteLoopException;
import management.*;

import java.io.*;
import java.util.Scanner;

/**
 * Handle 'execute_script' method.
 */
public class ExecuteScriptCommand extends AbstractCommand implements Command {
    private final CollectionManager collectionManager;
    private final Input inputManager;

    /**
     * Set name and description for 'execute_script' command.
     * @param collectionManager storage of the collection.
     */
    public ExecuteScriptCommand(CollectionManager collectionManager, Input inputManager) {
        super("execute_script", "inputs elements from file;");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Input elements from file.
     */
    @Override
    public void execute() {
        boolean loop = true, wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("execute script: input filename again:");
                }
                String path = inputManager.inpString("path");
                if (collectionManager.getFiles().isEmpty()) {
                    collectionManager.fadd(path);
                } else {
                    if (!collectionManager.getFiles().contains(path)) {
                        collectionManager.fadd(path);
                    } else {
                        // if path in files, exception
                        throw new InfiniteLoopException("execute_script");
                    }
                }

                File file = new File(path);
                Scanner fileScanner = new Scanner(file);
                InputManager im = new InputManager(fileScanner);
                CommandManager commandManager = new CommandManager(collectionManager, im);

                while (fileScanner.hasNext()) {
                    String command = fileScanner.nextLine();
                    if (commandManager.getCommands().containsKey(command)) {
                        commandManager.getCommands().get(command).execute();
                    } else {
                        System.out.println(command + ": this command doesn't exist yet.");
                    }
                }
                fileScanner.close();
                loop = false;
            } catch (FileNotFoundException e) {
                loop = true;
                wasErr = true;
            }
            catch (InfiniteLoopException e) {
                System.err.println(e.getMessage());
                loop = false;
            }
        } while (loop);
    }
}

