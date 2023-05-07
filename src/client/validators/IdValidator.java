package client.validators;

public class IdValidator implements Validator<Long> {
    @Override
    public Long validate(Long value) throws ValidateException {
        if (value > 0) {
            return value;
        }
        throw new ValidateException("Id less than zero");
    }
}
