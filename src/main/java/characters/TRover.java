package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.logging.TLogger;

public class TRover {

    public String id;
    public Gender gender;
    public static int count = 0;

    // the surface that TetRover reside in
    public TFace tFace;
    public Location currentLocation;

    public TRover(String id, Gender gender, TFace tFace, Location location) {
        this.id = id;
        this.tFace = tFace;
        this.gender = gender;
        this.currentLocation = location;
        count += 1;
    }


    public void move() {
        Location nextLocation = tFace.getAdjacent(currentLocation, false);

        if (nextLocation != null) {
            TLogger.shared.log(this + " moved from " + currentLocation + " to " + nextLocation);
            currentLocation.move2(nextLocation);
            currentLocation = nextLocation;
        } else {
            TLogger.shared.log(this + " cannot make a move anymore");
        }
    }

    public boolean isHero(){
        return false;
    }

    public boolean isVader(){
        return false;
    }

    @Override
    public String toString() {
        return "TRover{" +
                "id=" + id +
                '}';
    }
}
