package server;

/**
 * Generates correct id.
 */
public class IdHandler {
    private static Long lastId = 0L;


    /**
     * @return id of last element in collection (so that the new id should be lastId + 1).
     */
    public static Long getLastId() {
        return lastId;
    }

    /**
     * Changes the id of last element.
     * @param Id id of new last element.
     */
    public static void setLastId(Long Id) {
        lastId = Id;
    }
}
