package main.java;

import main.java.locations.TRectangularFace;
import main.java.locations.Tetra;
import main.java.logging.TLogger;
import main.java.ui.TetraUI;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class TetraStarSimulator {
    public SimulationUIMediator mediator;

    public TetraStarSimulator() {
        Tetra.shared.setTFace(new TRectangularFace(5, 8));
    }

    public void start() {
        TLogger.shared.log("start");
    }

    public void stop() {
        TLogger.shared.log("stop");
    }

    public void nextStep() {
        TLogger.shared.log("nextStep");
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

    public void setupSimulation(){

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
