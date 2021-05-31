package main.java.stateMachine;

import main.java.characters.TRover;
import main.java.locations.Location;
import main.java.logging.TLogger;

public class MoveCommand implements Command {

    TRover tRover;
    Location from;
    Location to;
    int stallCount;

    public MoveCommand(TRover tRover, Location from, Location to) {
        this.tRover = tRover;
        this.from = from;
        this.to = to;
        stallCount = 0;
    }

    @Override
    public void execute() {
        if (to.isEmpty(false)) {
            TLogger.shared.log(tRover + " moved from " + from + " to " + to);
            from.leave(tRover);
            to.enter(tRover);
            tRover.setCurrentLocation(to);
        } else {
            if (stallCount < 3) {
                TLogger.shared.log(tRover + " stalled for 1 round");
                tRover.delayExecute(this);
                stallCount += 1;
            } else {
                TLogger.shared.log(this + " is discard since it was not able to be executed for 3 rounds)");
            }
        }
    }

    @Override
    public String toString() {
        return "MoveCommand{" +
                "tRover=" + tRover +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
