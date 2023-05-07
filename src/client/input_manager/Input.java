/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.input_manager;

import client.validators.Validator;
import resources.task.Coordinates;
import resources.task.Location;
import resources.task.Route;

import java.util.Scanner;

/**
 * Purpose: make similar Input managers, one of which asks the client to client.input and the another one doesn't.
 */
public interface Input {
    /**
     * Sets either console scanner or file scanner.
     * @param sc Scanner to set.
     */
    void setScanner(Scanner sc);

    /**
     * Input Route with Scanner.
     * @return the Route inputted.
     */
    Route inpRoute();

    Coordinates inpCoordinates(String name);
    Location inpLocation(String name);

    Double inpDouble(String name, Validator<Double> validator);
    Float inpFloat(String name, Validator<Float> validator);
    Long inpLong(String name, Validator<Long> validator);
    String inpString(String name, Validator<String> validator);

}
