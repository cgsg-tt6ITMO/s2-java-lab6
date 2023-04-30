package client;

import java.util.ArrayList;

/**
 * Нужен для проверки отсутсвия зацикливания в скриптах.
 */
public class ExecuteScriptHandler {

    public ExecuteScriptHandler() {
        System.out.println("ExecuteScriptHandler");
    }

    // for 'execute_script' -- array of all used scripts.
    private final ArrayList<String> files = new ArrayList<>();

    /**
     * @return array of paths with scripts we met on our way.
     */
    public ArrayList<String> getFiles() {
        return files;
    }

    /**
     * Adds a path to array of scripts we used for our work.
     * @param path a path to the script and it's name.
     */
    public void fadd(String path) {
        this.files.add(path);
    }

    /**
     * Makes the array of scripts we met empty.
     * Purpose: if we call one script second time while running the program,
     *     it would throw InfiniteLoopException though it should not.
     */
    public void fclear() {
        this.files.clear();
    }


}
