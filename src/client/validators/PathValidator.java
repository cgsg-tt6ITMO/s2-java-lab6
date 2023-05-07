package client.validators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PathValidator implements Validator<String> {
    @Override
    public String validate(String value) throws ValidateException {
        try {
            new Scanner(new File(value));
        } catch (FileNotFoundException e) {
            throw new ValidateException("Incorrect file path");
        }
        return value;
    }
}
