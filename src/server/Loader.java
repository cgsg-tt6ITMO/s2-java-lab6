package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import resources.task.Route;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
            List<Route> books = Arrays.asList(mapper.readValue(Paths.get(path).toFile(), Route[].class));

            for (Route route : books) {
                st.add(route);
            }

        } catch (Exception ex) {
            System.err.println("default input from json file:" + ex.getMessage());
            ex.printStackTrace();
        }
        return  st;
    }

}
