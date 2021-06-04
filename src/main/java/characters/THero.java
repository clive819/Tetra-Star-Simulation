package main.java.characters;

import main.java.locations.Location;
import main.java.locations.TFace;
import main.java.locations.Terrain;
import main.java.locations.Tetra;
import main.java.logging.TLogger;
import main.java.starMap.AbstractEncryptionStrategy;
import main.java.starMap.AbstractStarMap;
import main.java.starMap.ReverseEncryptionStrategy;
import main.java.stateMachine.AcquireTFlierCommand;
import main.java.stateMachine.MoveCommand;
import main.java.stateMachine.NoMoreMovesCommand;
import main.java.stateMachine.StateMachine;

//THero:
//can encrypt and decrypt starmaps
//visits the tVaderBase if a mapBase is found to be empty to retrieve a starmap
public class THero extends TRover implements StateMachine {

    private TFlier tFlier;
    private AbstractStarMap starMap;
    private AbstractEncryptionStrategy encryptionStrategy;

    private final Location base;


    public THero(String id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
        this.encryptionStrategy = new ReverseEncryptionStrategy("*");
        base = location;
        starMap = null;
    }


    @Override
    protected void randomMove() {
        Location nextLocation = tFace.getAdjacent(currentLocation, true, tFlier != null, false);

        if (nextLocation != null) {
            queue.add(new MoveCommand(this, nextLocation));
        } else {
            queue.add(new NoMoreMovesCommand(this));
        }
    }

    // location setter: perform special behavior based on destination
    @Override
    public void setCurrentLocation(Location location) {
        super.setCurrentLocation(location);

        if (location.terrain == Terrain.mapBase) {
            if (starMap != null) {

                try {
                    AbstractStarMap starMapCopy = starMap.clone();
                    starMap.encrypt(this);
                    location.store(starMap);
                    starMap = starMapCopy;
                    queue.add(new MoveCommand(this, base));
                    TLogger.shared.log(this + " store " + location.starMap + " back to its base.");
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    TLogger.shared.log(e.toString());
                }
            } else if (!location.hasStarMap()) {
                requestTFlier();
                queue.add(new MoveCommand(this, tFace.getVaderBase()));
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
            starMap.display(this, Tetra.shared.day);
        }
    }

    private void enterVaderBase(Location location) {
        if (AbstractStarMap.ping(location.id)) {
            starMap = location.starMap;
            location.starMap = null;
            TLogger.shared.log(this + " found " + starMap + " in vader base");

            if (starMap.isEncrypted()) {
                if (starMap.hasAuthority(this)) {
                    starMap.restorationCount += 1;
                } else {
                    starMap.authorize(this);
                }
            }

            queue.add(new MoveCommand(this, starMap.base));
        }
    }

    public TFlier getFlier() {
        return tFlier;
    }

    public AbstractStarMap getStarMap() {
        return starMap;
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

    public AbstractEncryptionStrategy getEncryptionStrategy() {
        return this.encryptionStrategy;
    }

    public void setEncryptionStrategy(AbstractEncryptionStrategy strategy) {
        this.encryptionStrategy = strategy;
    }

    @Override
    public String toString() {
        return "THero{" +
                "id=" + id +
                '}';
    }

}
