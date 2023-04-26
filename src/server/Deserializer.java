package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import resources.task.Route;

public class Deserializer {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public static Route readRoute(String json) {
        Route res = null;
        try {
            res = mapper.readValue(json, Route.class);

        } catch (Exception ex) {
            System.err.println("default client.input from json file:" + ex.getMessage());
            ex.printStackTrace();
        }
        return res;
    }

    public static Long readLong(String ser) {
        return Long.parseLong(ser);
    }

    public static Double readDouble(String d) {
        return Double.parseDouble(d);
    }
}
