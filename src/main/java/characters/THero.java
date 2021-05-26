package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.locations.Terrain;
import main.java.logging.TLogger;
import main.java.starMap.StarMap;
import main.java.stateMachine.MoveCommand;
import main.java.stateMachine.NoMoreMovesCommand;
import main.java.stateMachine.StateMachine;

public class THero extends TRover implements StateMachine {

    TFlier tFlier;
    Location base;

    public THero(String id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
        this.base = location;
    }


    @Override
    public void randomMove() {
        Location nextLocation = tFace.getAdjacent(currentLocation, true, tFlier != null);

        if (nextLocation != null) {
            queue.add(new MoveCommand(this, currentLocation, nextLocation));
        } else {
            queue.add(new NoMoreMovesCommand(this));
        }
    }

    @Override
    public void setCurrentLocation(Location location) {
        super.setCurrentLocation(location);

        if (location.terrain == Terrain.mapBase) {
            if (location.isEmpty()) {
                requestTFlier();
                queue.add(new MoveCommand(this, currentLocation, tFace.getVaderBase()));
            } else {
                enterMapBase(location);
            }
        } else if (location.terrain == Terrain.vaderBase) {
            enterVaderBase(location);
        }
    }

    private void enterMapBase(Location location) {
        StarMap starMap = location.starMap;

        if (starMap != null) {
            if (starMap.header.isEncrypted()) {
                if (starMap.header.hasAuthority(this)) {
                    starMap.decrypt(this);
                }else {
                    starMap.header.authorize(this);
                }
            }
            starMap.display();
        }
    }

    private void enterVaderBase(Location location) {
//        At TVaderBase, checks if the particular StarMap is there, by sending it a signal.
//        If it is found, makes a clone of it, encrypts the original, stores it back in its base and flies to his homebase with the clone.
//          i. If he finds that the StarMap was already encrypted when it was stolen: if it was encrypted by him previously, simply increments the restoration_counter.
//          ii. If it was encrypted by another THero, adds his ID to the header.

        // TODO
    }

    private void requestTFlier() {
        if (tFlier == null) {
            TLogger.shared.log(this + " request for a TFlier");
            tFlier = new TFlier();
        }
    }

    public boolean isHero() {
        return true;
    }

    @Override
    public String toString() {
        return "THero{" +
                "id=" + id +
                '}';
    }

}
