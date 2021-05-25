package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.stateMachine.Command;
import main.java.stateMachine.MoveCommand;
import main.java.stateMachine.NoMoreMovesCommand;
import main.java.stateMachine.StateMachine;

import java.util.ArrayList;

public class TVader extends TRover implements StateMachine {

    TFlier tFlier;
    private final ArrayList<Command> history;

    public TVader(String id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
        history = new ArrayList<>();
        tFlier = new TFlier();
    }


    @Override
    public void randomMove() {
        Location nextLocation = tFace.getAdjacent(currentLocation, false, true);

        if (nextLocation != null) {
            Command command = new MoveCommand(this, currentLocation, nextLocation);
            queue.add(command);
            history.add(command);
        } else {
            queue.add(new NoMoreMovesCommand(this));
        }
    }

    @Override
    public void setCurrentLocation(Location location) {
        super.setCurrentLocation(location);


    }

    public boolean isVader() {
        return true;
    }

    @Override
    public String toString() {
        return "TVader{" +
                "id=" + id +
                '}';
    }

}
