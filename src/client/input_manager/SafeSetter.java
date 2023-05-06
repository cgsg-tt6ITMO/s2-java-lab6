package client.input_manager;

import client.validators.Validator;
import resources.exceptions.NullLocationException;
import resources.task.Location;

import java.util.Scanner;

public class SafeSetter<T> {
    //Input inputManager;
    AskInputManager inputManager;

    public SafeSetter() {
        //inputManager = new AskInputManager(new Scanner(System.in));
    }
/*
    public SafeSetter(Input inputManager) {
        this.inputManager = inputManager;
    }

    public T setValue(Validator<T> validator, T value, boolean wasErr) {
        if (wasErr) {
            T f = inputManager.inpLocation("Input correct data:\nLocation(Float, Float, Long, String name)");
            setFrom(f);
        }
        if (validator.validate(value)) {
            return value;
        }

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

        return setValue(validator, value, true);
    }

 */
}
