/**
 * @author Troitskaya Tamara (TT6)
 */

/**
 * This class has the same name as task\Location.
 */
public class Location {
    private double x;
    private Double y; //Поле не может быть null
    private int z;
    private String name; //Поле не может быть null

    /**
     * Default Location constructor.
     */
    public Location() {
        this(0, 0., 0, "");
    }

    /**
     * Location constructor of 4 arguments.
     * First three arguments are coordinates.
     * @param X - abscissa;
     * @param Y - ordinate (!= null);
     * @param Z - applicate;
     * @param nm - name of the location (!= null).
     */
    public Location(double X, Double Y, int Z, String nm) {
        setX(X);
        setY(Y);
        setZ(Z);
        setName(nm);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(Double y) {
        if (y != null) {
            this.y = y;
        } else {
            System.err.println("Class src\\Location: Y is null");
        }
    }

    public Double getY() {
        return y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            System.err.println("Class src\\Location: name is null");
        }
    }

    public String getName() {
        return name;
    }
}