package client.input_manager;

import client.validators.Validator;

import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberInput<T> {
    private Scanner sc;

    public NumberInput(Scanner scanner) {
        this.sc = scanner;
    }

    /**
     * T = Double/Float/Long
     */
    public T input(String name, Validator<T> validator) {
        T res = null;

        boolean loop = true;
        boolean wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("Incorrect data, input again:");
                }
                if (res.getClass() == Double.class) {
                    System.out.println(name + " (Double)");
                    //res = Double.parseDouble(sc.nextLine());
                }
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                wasErr = true;
                loop = true;
            }
        } while (loop);

        return res;
    }
}
