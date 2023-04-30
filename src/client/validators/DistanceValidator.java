/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.validators;

/**
 * Check if Distance of Route is more than 1 (and non-negative).
 */
public class DistanceValidator implements Validator<Double> {
    @Override
    public boolean validate(Double value) {
        return value > 1;
    }
}
