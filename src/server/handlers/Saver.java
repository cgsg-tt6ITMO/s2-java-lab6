/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import resources.task.Route;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 * Save collection to json file.
 */
public class Saver {
    private final Stack<Route> storage;
    private final String path;
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    /**
     * Constructor
     * @param storage collection to be saved.
     * @param path file to save in.
     */
    public Saver(Stack<Route> storage, String path) {
        this.storage = storage;
        this.path = path;
    }

    /**
     * Is executed before exit.
     */
    public void save() {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path));) {
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(storage);
            output.write(json);
        } catch (IOException e) {
            System.err.println("json, write to file:" + e.getMessage());
        }
    }
}
