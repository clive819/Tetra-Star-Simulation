package main.java.locations;

import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

// TFace:
// Abstract class containing all the necessary control methods for a TFace (spawning and simulation control)
public abstract class TFace {
    public abstract Location getAdjacent(Location currentLocation, boolean heroBaseAllowed, boolean hasTFlier, boolean roverAllowed);

    public abstract Location getVaderBase();

    public abstract void spawnRover();

    public abstract void spawnHero();

    public abstract void spawnVader();

    public abstract void spawnMapBase();

    public abstract void spawnVaderBase();

    public abstract void spawnHeroBase();

    public abstract void nextStep();

    public abstract void render(Graphics g, TetraUIDrawingPanel p);

    public abstract void reset();
}
