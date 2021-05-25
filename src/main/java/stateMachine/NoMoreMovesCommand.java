package main.java.stateMachine;

import main.java.characters.TRover;
import main.java.logging.TLogger;

public class NoMoreMovesCommand implements Command {

    TRover tRover;

    public NoMoreMovesCommand(TRover tRover) {
        this.tRover = tRover;
    }

    @Override
    public void execute() {
        TLogger.shared.log(tRover + " cannot make a move anymore");
    }
}
