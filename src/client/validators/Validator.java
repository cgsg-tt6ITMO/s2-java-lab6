package client.validators;

public interface Validator<T> {
    boolean validate(T value);
}
