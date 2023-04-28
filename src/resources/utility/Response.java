package resources.utility;

public class Response {
    private final String message;

    public Response() {
        this.message = "";
    }

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
