package client.validators;

public class DistanceValidator implements Validator<Double> {
    @Override
    public boolean validate(Double value) {
        return value > 1;
    }
}
