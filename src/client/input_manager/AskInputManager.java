/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package client.input_manager;

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
    public void setScanner(Scanner scanner) {
        this.sc = scanner;
    }

    /**
     * Inputs Route.
     * @return the Route inputted.
     */
    public Route inpRoute() {
        System.out.println("Input route data");
        idHandler.setLastId(idHandler.getLastId() + 1);
        return new Route(idHandler.getLastId(),
                inpString("Name"),
                inpCoordinates("Coordinates (Double X, Float Y)"),
                inpLocation("Location from (Float X, Float Y, Long Z, String name)"),
                inpLocation("Location to (Float X, Float Y, Long Z, String name)"));
    }

    /**
     * @param name name of variable.
     * @return inputted Coordinates.
     */
    public Coordinates inpCoordinates(String name) {
        System.out.println(name);
        return new Coordinates(inpDouble("X"), inpFloat("Y"));
    }

    /**
     * @param name name of variable.
     * @return inputted Location.
     */
    public Location inpLocation(String name) {
        System.out.println(name);
        return new Location(inpFloat("X"), inpFloat("Y"), inpLong("Z"), inpString("name"));
    }

    /**
     * Safely inputs a variable of Double type.
     * @param name - name of the variable.
     * @return result (Double).
     */
    public Double inpDouble(String name) {
        Double res = null;

        boolean loop = true;
        boolean wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("Incorrect data, client.input again:");
                }
                System.out.println(name + " (Double)");
                res = Double.parseDouble(sc.nextLine());
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                wasErr = true;
                loop = true;
            }
        } while (loop);

        return res;
    }

    /**
     * Safely inputs a variable of Float type.
     * @param name - name of the variable.
     * @return result (Float).
     */
    public Float inpFloat(String name) {
        Float res = null;

        boolean loop = true;
        boolean wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("Incorrect data, client.input again:");
                }
                System.out.println(name + " (Float)");
                res = Float.parseFloat(sc.nextLine());
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                wasErr = true;
                loop = true;
            }
        } while (loop);

        return res;
    }

    /**
     * Safely inputs a variable of Long type.
     * @param name - name of the variable.
     * @return result (Long).
     */
    public Long inpLong(String name) {
        Long res = null;

        boolean loop = true;
        boolean wasErr = false;
        do {
            try {
                if (wasErr) {
                    System.err.println("Incorrect data, client.input again:");
                }
                System.out.println(name + " (Long)");
                res = Long.parseLong(sc.nextLine());
                loop = false;
            } catch (NumberFormatException | InputMismatchException exception) {
                wasErr = true;
                loop = true;
            }
        } while (loop);

        return res;
    }

    /**
     * @param name name of the variable we input.
     * @return inputted String
     */
    public String inpString(String name) {
        System.out.println(name + " (String)");
        return sc.nextLine();
    }

    /**
     * @return description for the AskInputManager.
     */
    @Override
    public String toString() {
        return "Input manager which prints tips for inputting data";
    }

}
