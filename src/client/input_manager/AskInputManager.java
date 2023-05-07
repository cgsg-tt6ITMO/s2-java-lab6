/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.input_manager;

import client.validators.*;
import resources.task.Coordinates;
import resources.task.Location;
import resources.task.Route;
import resources.utility.IdHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Inputs data, gently reminding the client about what variable they are supposed to input,
 *   and it's type and providing re-input in case of wrong client.input.
 */
public class AskInputManager implements Input {
    private Scanner sc;
    private final IdHandler idHandler;

    /**
     * Input manager initialization.
     * @param scanner - scanner from which to input (console, file, etc).
     */
    public AskInputManager(Scanner scanner, IdHandler idHandler) {
        setScanner(scanner);
        this.idHandler = idHandler;
    }

    /**
     * Changes the scanner from which we input.
     * @param scanner - new scanner.
     */
    @Override
    public void setScanner(Scanner scanner) {
        this.sc = scanner;
    }

    /**
     * Inputs Route.
     * @return the Route inputted.
     */
    @Override
    public Route inpRoute() {
        System.out.println("Input route data");
        idHandler.setLastId(idHandler.getLastId() + 1);
        try {
            return new Route()
                    .setId(idHandler.getLastId())
                    .setName(inpString("Name", new StringValidator()))
                    .setCoordinates(inpCoordinates("Coordinates (Double X, Float Y)"))
                    .setFrom(inpLocation("Location from (Float X, Float Y, Long Z, String name)"))
                    .setTo(inpLocation("Location to (Float X, Float Y, Long Z, String name)"));
        } catch (ValidateException validateException) {
            return inpRoute();
        }
    }

    /**
     * @param variableName name of variable.
     * @return inputted Coordinates.
     */
    @Override
    public Coordinates inpCoordinates(String variableName) {
        System.out.println(variableName);
        try {
            // через билдер быстрее ошибку найдём
            return new Coordinates()
                    .setX(inpDouble("X", new NotNullValidator<Double>()))
                    .setY(inpFloat("Y", new NoValidator<Float>()));
        } catch (ValidateException validateException) {
            return inpCoordinates("input again " + variableName);
        }
    }

    /**
     * @param variableName name of variable.
     * @return inputted Location.
     */
    @Override
    public Location inpLocation(String variableName) {
        System.out.println(variableName);
        try {
            return new Location()
                    .setX(inpFloat("X", new NoValidator<>()))
                    .setY(inpFloat("Y", new NotNullValidator<Float>()))
                    .setZ(inpLong("Z", new NoValidator<Long>()))
                    .setName(inpString("name", new StringValidator()));
        } // придётся заново все вводить
        catch (ValidateException | NumberFormatException | InputMismatchException e) {
            return inpLocation("input again " + variableName);
        }
    }

    /**
     * Safely inputs a variable of Double type.
     * @param variableName - name of the variable.
     * @return result (Double).
     */
    @Override
    public Double inpDouble(String variableName, Validator<Double> validator) {
        System.out.println(variableName + " (Float)");
        try {
            return validator.validate(Double.parseDouble(sc.nextLine()));
        } catch (ValidateException | NumberFormatException | InputMismatchException e) {
            return inpDouble("input again " + variableName, validator);
        }
    }

    /**
     * Safely inputs a variable of Float type.
     * @param variableName - name of the variable.
     * @return result (Float).
     */
    @Override
    public Float inpFloat(String variableName, Validator<Float> validator) {
        System.out.println(variableName + " (Float)");
        try {
            return validator.validate(Float.parseFloat(sc.nextLine()));
        } catch (ValidateException | NumberFormatException | InputMismatchException e) {
            return inpFloat("input again" + variableName, validator);
        }
    }

    /**
     * Safely inputs a variable of Long type.
     * @param variableName - name of the variable.
     * @return result (Long).
     */
    @Override
    public Long inpLong(String variableName, Validator<Long> validator) {
        System.out.println(variableName + " (Long)");
        try {
            return validator.validate(Long.parseLong(sc.nextLine()));
        } catch (ValidateException | NumberFormatException | InputMismatchException e) {
            return inpLong("input again" + variableName, validator);
        }
    }

    /**
     * @param variableName name of the variable we input.
     * @return inputted String
     */
    @Override
    public String inpString(String variableName, Validator<String> validator) {
        System.out.println(variableName + " (String)");
        try {
            return validator.validate(sc.nextLine());
        } catch (ValidateException validateException) {
            return inpString("input again" + variableName, new StringValidator());
        }
    }

    /**
     * @return description for the AskInputManager.
     */
    @Override
    public String toString() {
        return "Input manager which prints tips for inputting data";
    }
}
