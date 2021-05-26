package main.java.stateMachine;

import main.java.characters.TFlier;
import main.java.characters.THero;
import main.java.logging.TLogger;

public class AcquireTFlierCommand implements Command {

    THero tHero;

    public AcquireTFlierCommand(THero tHero) {
        this.tHero = tHero;
    }

    @Override
    public void execute() {
        TLogger.shared.log(tHero + " request for a TFlier");
        tHero.setTFlier(new TFlier());
    }

}
