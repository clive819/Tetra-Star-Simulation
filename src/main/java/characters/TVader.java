package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.locations.Terrain;
import main.java.logging.TLogger;
import main.java.starMap.AbstractStarMap;
import main.java.stateMachine.Command;
import main.java.stateMachine.MoveCommand;
import main.java.stateMachine.NoMoreMovesCommand;
import main.java.stateMachine.StateMachine;

import java.util.ArrayList;

public class TVader extends TRover implements StateMachine {

    TFlier tFlier;

    private AbstractStarMap starMap;

    private ArrayList<Command> history;


    public TVader(String id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
        history = new ArrayList<>();
        tFlier = new TFlier();
        starMap = null;
    }


    @Override
    public void randomMove() {
        Location nextLocation = tFace.getAdjacent(currentLocation, false, true);

        if (nextLocation != null) {
            Command command = new MoveCommand(this, nextLocation);
            queue.add(command);
            history.add(command);
        } else {
            queue.add(new NoMoreMovesCommand(this));
        }
    }

    @Override
    public void setCurrentLocation(Location location) {
        super.setCurrentLocation(location);

        if (location.terrain == Terrain.mapBase) {
            stealStarMap(location);

        }else if (location.terrain == Terrain.vaderBase) {
            location.store(starMap);
            starMap = null;
        }
    }

    private void stealStarMap(Location location) {
        starMap = location.starMap;

        if (starMap != null) {
            TLogger.shared.log(this + " steals " + starMap);
            location.starMap = null;
            backtrace();
        }
    }

    public boolean isVader() {
        return true;
    }

    private void backtrace() {
        queue = (ArrayList<Command>) history.clone();
        history.clear();
    }

    @Override
    public String toString() {
        return "TVader{" +
                "id=" + id +
                '}';
    }

}
