/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package commands;

import management.CollectionManager;
import management.Input;
import task.Coordinates;
import task.Location;

import static java.lang.Math.sqrt;

/**
 * Handle 'update' method.
 */
public class UpdateCommand extends AbstractCommand implements Command {
    private final CollectionManager storage;
    private final Input aim;

    /**
     * Set name and description for 'update' command.
     * @param collectionManager storage of the collection.
     */
    public UpdateCommand(CollectionManager collectionManager, Input inputManager) {
        super("update", "updates element with id inputted;");
        this.storage = collectionManager;
        this.aim = inputManager;
    }

    /**
     * @param id index of element to be shown.
     */
    private void show_by_id(Long id) {
        for (var r : storage.stack()) {
            if (r.getId().equals(id)) {
                System.out.println("Route Id:      " + r.getId() + "\nName:          " + r.getName()
                        + "\nCreation date: " + r.getCreationTime()
                        + "\nCoordinates:   " + r.getCoordinates().getX() + " " + r.getCoordinates().getY()
                        + "\nLocation From: " + r.getFrom().getName() + " " + r.getFrom().getX() + " " + r.getFrom().getY() + " " + r.getFrom().getZ()
                        + "\nLocation To:   " + r.getTo().getName() + " " + r.getTo().getX() + " " + r.getTo().getY() + " " + r.getTo().getZ()
                        + "\nDistance:      " + r.getDistance() + "\n");
            }
        }
    }

    /**
     * Updates element with id inputted.
     */
    @Override
    public void execute() {
        System.out.println("Id of element to be updated:");
        Long id = aim.inpLong("id");

        boolean exist = false;

        for (var el : storage.stack()) {
            if (el.getId().equals(id)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            System.out.println("Element with this id doesn't exist.");
            return;
        }

        show_by_id(id);

        String Name = aim.inpString("name");
        Coordinates coords = aim.inpCoordinates("Coordinates (Double X, Float Y)");
        Location f = aim.inpLocation("Location from (Float X, Float Y, Long Z, String name)");
        Location t = aim.inpLocation("Location to (Float X, Float Y, Long Z, String name)");

        for (var r : storage.stack()) {
            if (r.getId().equals(id)) {
                r.setName(Name);
                r.setCoordinates(coords);
                r.setFrom(f);
                r.setTo(t);
                Double dist = sqrt((f.getX()-t.getX()) * (f.getX()-t.getX()) + (f.getY()-t.getY()) * (f.getY()-t.getY())
                        + (f.getZ()-t.getZ()) * (f.getZ()-t.getZ()));
                r.setDistance(dist);
                break;
            }
        }
        System.out.println("ELEMENT UPDATED SUCCESSFULLY\n");
    }
}
