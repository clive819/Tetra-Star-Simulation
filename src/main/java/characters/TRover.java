package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.starMap.AbstractStarMap;
import main.java.stateMachine.Command;
import main.java.stateMachine.MoveCommand;
import main.java.stateMachine.NoMoreMovesCommand;
import main.java.stateMachine.StateMachine;

import java.util.ArrayList;

public class TRover implements StateMachine {

    public String id;
    public Gender gender;
    public static int count = 0;

    // the surface that TetRover reside in
    protected TFace tFace;
    public Location currentLocation;

    public ArrayList<Command> queue;

    public TRover(String id, Gender gender, TFace tFace, Location location) {
        this.id = id;
        this.tFace = tFace;
        this.gender = gender;
        this.currentLocation = location;
        this.queue = new ArrayList<>();
        count += 1;
    }


    protected void randomMove() {
        Location nextLocation = tFace.getAdjacent(currentLocation, false, false, false);

        if (nextLocation != null) {
            queue.add(new MoveCommand(this, nextLocation));
        } else {
            queue.add(new NoMoreMovesCommand(this));
        }
    }

    public void delayExecute(Command command) {
        queue.add(0, command);
    }

    public void fullStall(Command command) {
        //no behavior on TRover currently
        //currently used to allow TVader to break backtrace
    }

    @Override
    public void update() {
        if (queue.isEmpty()) {
            randomMove();
        }

        queue.remove(0).execute();
    }

    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    public AbstractStarMap getStarMap() {
        return null;
    }

    public TFlier getFlier() {
        return null;
    }

    public boolean isHero() {
        return false;
    }

    public boolean isVader() {
        return false;
    }

    @Override
    public String toString() {
        return "TRover{" +
                "id=" + id +
                '}';
    }
}
