/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package management;

import task.Coordinates;
import task.Location;
import task.Route;

import java.util.Scanner;

/**
 * Purpose: make similar Input managers, one of which asks the client to input and the another one doesn't.
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

    Double inpDouble(String name);
    Float inpFloat(String name);
    Long inpLong(String name);
    String inpString(String name);

}
