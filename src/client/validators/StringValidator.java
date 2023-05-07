package client.validators;

public class StringValidator implements Validator<String> {
    @Override
    public String validate(String value) {
        if (!(value == null || value.equals(""))) {
            return value;
        }
        throw new ValidateException("String is null or empty");
    }
}
