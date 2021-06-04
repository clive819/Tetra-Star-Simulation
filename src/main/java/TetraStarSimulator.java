package main.java;

import main.java.locations.TRectangularFace;
import main.java.locations.Tetra;
import main.java.ui.TetraUI;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class TetraStarSimulator {
    public SimulationUIMediator mediator;

    public TetraStarSimulator() {
        Tetra.shared.setTFace(new TRectangularFace(5, 7));
    }

    public void setupSimulation() {
        Tetra.shared.tFace.spawnRover();
        Tetra.shared.tFace.spawnHero();
        //Tetra.shared.tFace.spawnHero();
        Tetra.shared.tFace.spawnVader();
        Tetra.shared.tFace.spawnMapBase();
    }

    public void start() {
        Tetra.shared.reset();
        setupSimulation();
        mediator.updateRender();
    }

    public void stop() {
        Tetra.shared.reset();
        mediator.updateRender();
    }

    public void nextStep() {
        Tetra.shared.nextStep();
        mediator.updateRender();
    }

    public void render(Graphics g, TetraUIDrawingPanel p) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, p.getWidth(), p.getHeight());
        Tetra.shared.render(g, p);
    }

    public void setMediator(SimulationUIMediator mediator) {
        if (this.mediator == null) {
            this.mediator = mediator;
            mediator.setSimulation(this);
        }
    }

    ///////////////
    ///// CLIENT
    ///////////////

    public static void main(String[] args) {
        SimulationUIMediator mediator = new SimulationUIMediator();

        TetraUI ui = new TetraUI();
        ui.setMediator(mediator);

        TetraStarSimulator simulator = new TetraStarSimulator();
        simulator.setMediator(mediator);
    }
}
