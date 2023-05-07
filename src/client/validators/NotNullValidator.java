package client.validators;

public class NotNullValidator<T> implements Validator<T> {
    @Override
    public T validate(T value) throws ValidateException {
        if (value != null) {
            return value;
        }
        throw new ValidateException("Value is null");
    }
}
