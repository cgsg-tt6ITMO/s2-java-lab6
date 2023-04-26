/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package resources.task;

import client.input_manager.AskInputManager;

import java.util.Objects;
import java.util.Scanner;

/**
 * Stores Location data.
 */
public class Location {
    private float x;
    private Float y; //Поле не может быть null
    private long z;
    private String name; //Строка не может быть пустой, Поле может быть null

    /**
     * For 'group_counting_by_from' method.
     */
    @Override
    public String toString() {
        return name + " " + x + " " + y + " " + z;
    }

    /**
     * Default Location constructor.
     */
    public Location() {
        this(0, (float)0.010, 0, null);
    }

    /**
     * Location constructor of 4 arguments.
     * First three arguments are coordinates.
     * @param X - abscissa;
     * @param Y - ordinate;
     * @param Z - applicate;
     * @param nm - name of the location.
     */
    public Location(float X, Float Y, long Z, String nm) {
        setX(X);
        setY(Y);
        setZ(Z);
        setName(nm);
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    /**
     * Safe setting Y.
     */
    public void setY(Float y) {
        if (y != null) {
            this.y = y;
        } else {
            System.err.println("Class task\\Location: Y is null");
            AskInputManager aim = new AskInputManager(new Scanner(System.in));
            setY(aim.inpFloat("Input correct data:\nsetY (Float)"));
        }
    }

    public Float getY() {
        return y;
    }

    public void setZ(long z) {
        this.z = z;
    }

    public long getZ() {
        return z;
    }

    /**
     * In case of incorrect client.input offers you to re-client.input.
     */
    public void setName(String name) {
        if (!Objects.equals(name, "")) {
            this.name = name;
        } else {
            System.err.println("Class task\\Location: name is ''");
            System.out.println("Input correct data:\nsetName (String)");
            setName(new Scanner(System.in).nextLine());
        }
    }

    public String getName() {
        return name;
    }
}
