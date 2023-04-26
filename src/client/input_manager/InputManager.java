/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.input_manager;

import resources.task.Coordinates;
import resources.task.Location;
import resources.task.Route;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Inputs data from file and proves re-input in case of wrong client.input.
 */
public class InputManager implements Input {
    private Scanner sc;

    /**
     * Input manager initialization.
     * @param scanner - scanner from which to input (console, file, etc).
     */
    public InputManager(Scanner scanner) {
        setScanner(scanner);
    }

    /**
     * Changes the scanner from which we input.
     * @param scanner - new scanner.
     */
    public void setScanner(Scanner scanner) {
        this.sc = scanner;
    }

    /**
     * Inputs Route.
     * @return the Route inputted.
     */
    public Route inpRoute() {
        return new Route(inpString(""), inpCoordinates(""), inpLocation(""), inpLocation(""));
    }

    /**
     * Inputs coordinates with no console output.
     */
    public Coordinates inpCoordinates(String name) {
        return new Coordinates(inpDouble(""), inpFloat(""));
    }

    /**
     * Inputs Location with no console output.
     */
    public Location inpLocation(String name) {
        return new Location(inpFloat(""), inpFloat(""), inpLong(""), inpString(""));
    }

    /**
     * Safely inputs a variable of Double type
     * with no console output.
     */
    public Double inpDouble(String name) {
        Double res = null;
        boolean loop = true;
        do {
            try {
                res = Double.parseDouble(sc.nextLine());
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                loop = true;
            }
        } while (loop);
        return res;
    }

    /**
     * Safely inputs a variable of Float type
     * with no console output.
     */
    public Float inpFloat(String name) {
        Float res = null;
        boolean loop = true;
        do {
            try {
                res = Float.parseFloat(sc.nextLine());
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                loop = true;
            }
        } while (loop);
        return res;
    }

    /**
     * Safely inputs a variable of Long type
     * with no console output.
     */
    public Long inpLong(String name) {
        Long res = null;
        boolean loop = true;
        do {
            try {
                res = Long.parseLong(sc.nextLine());
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                loop = true;
            }
        } while (loop);
        return res;
    }

    /**
     * Inputs string with no console output.
     */
    public String inpString(String name) {
        return sc.nextLine();
    }

    /**
     * @return description for the InputManager.
     */
    @Override
    public String toString() {
        return "'silent' Input manager, which does not print anything (suits for client.input from file)";
    }

}
