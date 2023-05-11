/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import resources.task.Route;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Stack;

/**
 * Loads default collection.
 */
public class Loader {
    private final String path;
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public Loader(String file) {
        this.path = file;
    }

    /**
     * Read values from JSON file.
     * @return collection of elements we read from the file.
     */
    public Stack<Route> load() {
        Stack<Route> st = new Stack<>();
        try {
            Route[] books = mapper.readValue(Paths.get(path).toFile(), Route[].class);
            Collections.addAll(st, books);
        } catch (Exception ex) {
            System.err.println("default input from json file:" + ex.getMessage());
            ex.printStackTrace();
        }
        return  st;
    }

}