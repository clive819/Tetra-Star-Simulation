package main.java.locations;

import main.java.logging.TLogger;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

// Tetra: (singleton)
// contains the planet class which holds the representation of the face of the planet (can be non-rectangular)
// also holds the date and carrys nextStep() annd reset() commands to the tFace
public class Tetra {

    public static Tetra shared = new Tetra();
    public TFace tFace;
    public int day = 1;

    private Tetra() {
    }

    public void setTFace(TFace tFace) {
        this.tFace = tFace;
    }

    public void nextStep() {
        TLogger.shared.log(">>>>>>>>> day " + day + "! <<<<<<<<<");
        day++;
        tFace.nextStep();
    }

    public void reset() {
        day = 1;
        tFace.reset();
    }

    public void render(Graphics g, TetraUIDrawingPanel p) {
        tFace.render(g, p);
    }
}
