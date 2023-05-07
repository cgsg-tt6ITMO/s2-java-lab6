package client.validators;

public class NoValidator<T> implements Validator<T> {
    @Override
    public T validate(T value) {
        return value;
    }
}
