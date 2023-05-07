/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.validators;

import java.io.FileNotFoundException;

/**
 * Checks if values are correct.
 * @param <T> type of value.
 */
public interface Validator<T> {
    /**
     * Checks if values are correct.
     * Example: negative age = false.
     * @param value value to be checked.
     * @return true if the value is suitable.
     */
    T validate(T value);
}
