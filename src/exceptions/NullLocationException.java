/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package exceptions;

/**
 * Null-Location set in Route.
 */
public class NullLocationException extends RuntimeException {
    /**
     * @param message place (method / class / file) where the exception occurs.
     */
    public NullLocationException(String message) {
        super(message + ": the Location equals null.");
    }
}
