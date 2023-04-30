package client;

import client.input_manager.Input;
import resources.exceptions.InfiniteLoopException;
import resources.utility.Deserializer;
import resources.utility.Request;
import resources.utility.Serializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Я создала для этого отдельный класс, потому что он просто вызывает другие функции, то есть
 * request-ы будут посылаться только функциями, которые в нём написаны, а не им самим
 * иначе было бы: execute_script посылает request, а ему что в ответ?
 *
 * Кстати, возможно можно создать коллекцию команд, которые возвращает execute_script
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
    public String makeReq() {
        //ArrayList<String> reqs = new ArrayList<>();
        int numOfCommands = 100;
        Request[] reqs = new Request[numOfCommands];
        /* надо сделать так, чтобы Client.run() возвращал Request
         * либо придётся копировать кусок кода сюда
         */

        // для того, чтобы избежать зацикливания скриптов, в клиенте надо создать массив использованных скритов

        boolean loop = true, wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("execute script: client.input filename again:");
                }
                String path = inputManager.inpString("script file name");

                // проверка отсутствия зацикливания в скриптах
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
                        Request st = Deserializer.readReq(new String(new client.CommandHandler().run(fileScanner)));
                        System.out.println(st);
                        reqs[i] = st;
                    }
                }

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

        return Serializer.objSer(reqs);
    }

}
