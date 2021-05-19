package main.java.locations;

import main.java.SimulationUIMediator;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class Tetra {

    public static Tetra instance = new Tetra();
    public TFace tFace;

    private Tetra() {
    }

    public void setTFace(TFace tFace){
        this.tFace = tFace;
    }

    public void render(Graphics g, TetraUIDrawingPanel p){
        tFace.render(g,p);
    }
}
