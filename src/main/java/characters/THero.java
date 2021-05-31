package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.locations.Terrain;
import main.java.logging.TLogger;
import main.java.starMap.AbstractStarMap;
import main.java.stateMachine.AcquireTFlierCommand;
import main.java.stateMachine.MoveCommand;
import main.java.stateMachine.NoMoreMovesCommand;
import main.java.stateMachine.StateMachine;

public class THero extends TRover implements StateMachine {

    TFlier tFlier;

    private AbstractStarMap starMap;

    private final Location base;


    public THero(String id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
        base = location;
        starMap = null;
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
            if (location.isEmpty(false)) {
                requestTFlier();
                queue.add(new MoveCommand(this, currentLocation, tFace.getVaderBase()));
            } else {
                enterMapBase(location);
            }
        } else if (location.terrain == Terrain.vaderBase) {
            enterVaderBase(location);
        } else if (location == base) {
            base.store(starMap);
            starMap = null;
        }
    }

    private void enterMapBase(Location location) {
        AbstractStarMap starMap = location.starMap;

        if (starMap != null) {
            if (starMap.isEncrypted()) {
                if (starMap.hasAuthority(this)) {
                    starMap.decrypt(this);
                } else {
                    starMap.authorize(this);
                }
            }
            starMap.display();
        }
    }

    private void enterVaderBase(Location location) {
        if (AbstractStarMap.ping(location.id)) {
            try {
                starMap = location.starMap.clone();

                if (starMap.isEncrypted()) {
                    if (starMap.hasAuthority(this)) {
                        starMap.restorationCount += 1;
                    }else {
                        starMap.authorize(this);
                    }
                }

                queue.add(new MoveCommand(this, location, base));
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                TLogger.shared.log(e.toString());
            }
        }
    }

    private void requestTFlier() {
        if (tFlier == null) {
            queue.add(new AcquireTFlierCommand(this));
        }
    }

    public void setTFlier(TFlier tFlier) {
        this.tFlier = tFlier;
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
