package main.java.locations;

import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public abstract class TFace {
    public abstract Location getAdjacent(Location currentLocation, boolean heroBaseAllowed, boolean hasTFlier);

    public abstract Location getVaderBase();

    public abstract Location spawnRover();

    public abstract Location spawnHero();

    public abstract Location spawnVader();

    public abstract void spawnVaderBase();

    public abstract void spawnHeroBase();

    public abstract void nextStep();

    public abstract void render(Graphics g, TetraUIDrawingPanel p);

    public abstract void reset();
}
