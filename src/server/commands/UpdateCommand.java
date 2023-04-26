/**
 * @author Troitskaya Tamara (cgsg-tt6)
 */
package server.commands;

import resources.task.Route;
import server.CollectionManager;
import client.input_manager.Input;
import resources.task.Coordinates;
import resources.task.Location;
import server.Deserializer;

import java.util.Scanner;

import static java.lang.Math.sqrt;

/**
 * Handle 'update' method.
 */
public class UpdateCommand extends AbstractCommand implements Command {
    private final CollectionManager storage;

    /**
     * Set name and description for 'update' command.
     * @param collectionManager storage of the collection.
     */
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "updates element with id inputted;");
        this.storage = collectionManager;
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
                        + "\nresourses.Location From: " + r.getFrom().getName() + " " + r.getFrom().getX() + " " + r.getFrom().getY() + " " + r.getFrom().getZ()
                        + "\nresourses.Location To:   " + r.getTo().getName() + " " + r.getTo().getX() + " " + r.getTo().getY() + " " + r.getTo().getZ()
                        + "\nDistance:      " + r.getDistance() + "\n");
            }
        }
    }

    /**
     * Updates element with id inputted.
     */
    @Override
    public void execute(String args) {
        Scanner scanner = new Scanner(args);
        String idStr = scanner.nextLine();
        scanner.close();
        Long id = Long.parseLong(idStr);
        String json = "";

        for (int i = idStr.length(); i < args.length(); i++) {
            json += args.charAt(i);
        }

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

        Route route = Deserializer.readRoute(json);

        String Name = route.getName();
        Coordinates coords = route.getCoordinates();
        Location f = route.getFrom();
        Location t = route.getTo();
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
