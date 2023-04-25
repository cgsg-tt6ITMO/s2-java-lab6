/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package task;

import exceptions.NullLocationException;
import management.AskInputManager;
import management.CollectionManager;

import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Math.sqrt;

/**
 * Elements of the collection.
 */
public class Route implements Comparable<Route>{
    private Long id; // Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationTime; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Location to; //Поле может быть null
    private Double distance; //Значение поля должно быть больше 1

    private final AskInputManager aim = new AskInputManager(new Scanner(System.in));

    @Override
    public String toString() {
        return "Name:" + name;
    }

    @Override
    public int hashCode() {
        return (int)(Math.round(Double.parseDouble(String.valueOf(distance*1000))));
    }

    /**
     * Compares this route with the one in argument.
     * @param r - Route to be compared with
     * @return <ul><li>1 - this > r</li>
     *             <li>1 - this &lt; r</li>
     *             <li>0 - this = r</li></ul>
     */
    @Override
    public int compareTo(Route r) {
        return Double.compare(distance, r.getDistance());
    }

    /**
     * Default constructor for debugging.
     */
    public Route() {
        setId();
        setCreationTime(ZonedDateTime.now());
        setName("route#" + id);
        setCoordinates(new Coordinates(5.17, 3.41f));
        setFrom(new Location());
        setTo(new Location(1.34f, 5.61f, 45, "loc-to"));
        Double dist = sqrt((getFrom().getX()-getTo().getX()) * (getFrom().getX()-getTo().getX())
                + (getFrom().getY()-getTo().getY()) * (getFrom().getY()-getTo().getY())
                + (getFrom().getZ()-getTo().getZ()) * (getFrom().getZ()-getTo().getZ()));
        setDistance(dist);
    }

    /**
     * The most frequently used constructor.
     */
    public Route(String Name, Coordinates coords, Location f, Location t) {
        this();
        setName(Name);
        setCoordinates(coords);
        setFrom(f);
        setTo(t);
        Double dist = sqrt((f.getX()-t.getX()) * (f.getX()-t.getX()) + (f.getY()-t.getY()) * (f.getY()-t.getY())
                + (f.getZ()-t.getZ()) * (f.getZ()-t.getZ()));
        setDistance(dist);
    }

    /**
     * In case we need to set distance not automatically.
     */
    public Route(String Name, Coordinates coords, Location f, Location t, Double distance) {
        this(Name, coords, f, t);
        setDistance(distance);
    }

    /**
     * Needed for 'insert_at'
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Automatically generated unique id.
     */
    public void setId() {
        CollectionManager.setLastId(CollectionManager.getLastId() + 1);;
        this.id = CollectionManager.getLastId();
    }

    /**
     * @return id of the Route.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets name and suggests you to correct your output.
     */
    public void setName(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
        } else {
            System.err.println("Class Route: Field name is null or ''");
            System.out.println("Input correct data:\nName (String)");
            setName(new Scanner(System.in).nextLine());
        }
    }

    /**
     * @return Name of the Route.
     */
    public String getName() {
        return name;
    }

    /**
     * @param coordinates set coordinates for route.
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates != null) {
            this.coordinates = coordinates;
        } else {
            System.err.println("Class Route: Field coordinates is null");
            Coordinates coords = aim.inpCoordinates("Input correct data:\nCoordinates(Double, Float)");
            setCoordinates(coords);
        }
    }

    /**
     * @return Coordinates of this Route.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return time when the collection was created.
     */
    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime time when the collection was created.
     */
    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Sets where do we go to.
     * @param to != null, Location - point of destination.
     */
    public void setTo(Location to) {
        if (to != null) {
            this.to = to;
        }
        else {
            System.err.println("Class Route: Location 'to' is null");;
            Location t = aim.inpLocation("Input correct data:\nLocation(Float, Float, Long, String name)");
            setTo(t);
        }
    }

    /**
     * @return Location To of the Route.
     */
    public Location getTo() {
        return to;
    }

    /**
     * Sets where do we come from.
     * @param from != null, Location - the beginning of our route.
     */
    public void setFrom(Location from) {
        boolean loop = true, wasErr = false;
        do {
            try {
                if (from != null) {
                    this.from = from;
                } else {
                    wasErr = true;
                    throw new NullLocationException("setFrom");
                }
                if (wasErr) {
                    System.err.println("Class Route: Location 'from' is null");
                    Location f = aim.inpLocation("Input correct data:\nLocation(Float, Float, Long, String name)");
                    setFrom(f);
                }
                loop = false;
            }
            catch (NullLocationException e) {
                loop = true;
            }
        } while (loop);
    }

    public Location getFrom() {
        return from;
    }

    /**
     * Sets the length of the route.
     * @param distance - the length (long)
     */
    public void setDistance(Double distance) {
        try {
            if (distance > 1) {
                this.distance = distance;
            } else {
                System.err.println("Class Route: distance is less than 1 (or equals 1)");
                throw new InputMismatchException();
            }
        }
        catch (NumberFormatException | InputMismatchException e) {
            boolean loop = true;
            do {
                try {
                    System.err.println("setDistance: incorrect input");
                    System.out.println("Input correct data:");
                    setDistance(Double.parseDouble(new Scanner(System.in).next()));
                    loop = false;
                } catch (NumberFormatException | InputMismatchException exception) {
                    loop = true;
                }
            } while (loop);
        }
    }

    public Double getDistance() {
        return distance;
    }
}
