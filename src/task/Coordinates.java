/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package task;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Coordinates class due to the task.
 */
public class Coordinates {
    private Double x; //Поле не может быть null
    private float y;

    /**
     * Default constructor for jackson.
     */
    public Coordinates() {}

    /**
     * Coordinates constructor.
     * @param X abscissa (!= null);
     * @param Y ordinate.
     */
    public Coordinates(Double X, float Y) {
        setX(X);
        y = Y;
    }

    /**
     * Sets X, without any exceptions written by me.
     */
    public void setX(Double x) {
        if (x != null) {
            this.x = x;
        }
        else {
            boolean loop = true;
            do {
                try {
                    System.err.println("Coordinate: You are trying to make X equal null");
                    System.out.println("Input X again:");
                    setX(new Scanner(System.in).nextDouble());
                    loop = true;
                } catch (NumberFormatException | InputMismatchException e) {
                    loop = false;
                }
            } while (loop);
        }
    }

    /**
     * @param y - ordinate to set.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return abscissa.
     */
    public Double getX() {
        return x;
    }

    /**
     * Needed for jackson.
     * @return ordinate of the point.
     */
    public float getY() {
        return y;
    }
}
