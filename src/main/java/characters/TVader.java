package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;

public class TVader extends TRover implements TFlierOperations {

    TFlier tFlier;

    public TVader(String id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
    }


    @Override
    public void fly2(Location location) {

    }

    @Override
    public String toString() {
        return "TVader{" +
                "id=" + id +
                '}';
    }

}
