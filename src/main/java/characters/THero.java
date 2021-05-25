package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.logging.TLogger;
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

        if (location.isMapBase()) {
            if (location.isEmpty()) {
                requestTFlier();
                queue.add(new MoveCommand(this, currentLocation, tFace.getVaderBase()));
            } else {
                enterMapBase(location);
            }
        } else if (location.isVaderBase()) {
            // TODO
        }
    }

    private void enterMapBase(Location location) {
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

    private void encrypt() {
        // TODO
    }

    private void decrypt() {
        // TODO
    }

    @Override
    public String toString() {
        return "THero{" +
                "id=" + id +
                '}';
    }

}
