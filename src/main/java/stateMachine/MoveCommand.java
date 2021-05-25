package main.java.stateMachine;

import main.java.characters.TRover;
import main.java.locations.Location;
import main.java.logging.TLogger;

public class MoveCommand implements Command {

    TRover tRover;
    Location from;
    Location to;

    public MoveCommand(TRover tRover, Location from, Location to) {
        this.tRover = tRover;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        if (to.isEmpty()) {
            TLogger.shared.log(tRover + " moved from " + from + " to " + to);
            from.leave(tRover);
            to.enter(tRover);
            tRover.setCurrentLocation(to);
        } else {
            TLogger.shared.log(tRover + " stalled for 1 round");
            tRover.delayExecute(this);
        }
    }
}
