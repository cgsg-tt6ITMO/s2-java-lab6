/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.validators;

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
    boolean validate(T value);
}
