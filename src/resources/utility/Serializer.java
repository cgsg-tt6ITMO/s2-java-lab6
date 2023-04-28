package resources.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import resources.task.Route;

import java.io.IOException;

public class Serializer {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    /**
     * Default json manager constructor.
     */
    public Serializer() {}

    public static String routeSer(Route r) {
        String res = "";
        try {
            res = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(r);

        } catch (IOException e) {
            System.err.println("error while Route serialization:" + e.getMessage());
        }
        return res;
    }

    public static String requestSer(Request r) {
        String res = "";
        try {
            res = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(r);

        } catch (IOException e) {
            System.err.println("error while Request serialization:" + e.getMessage());
        }
        return res;
    }

    public static String responseSer(Response r) {
        String res = "";
        try {
            res = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(r);

        } catch (IOException e) {
            System.err.println("error while Response serialization:" + e.getMessage());
        }
        return res;
    }

    public static String longSer(Long l) {
        return String.valueOf(l);
    }

    public static String longRouteSer(long n, Route r) {
        return longSer(n) + "\n" + routeSer(r);
    }

    public static String doubleSer(Double d) {
        return String.valueOf(d);
    }
}
