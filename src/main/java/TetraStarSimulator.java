package main.java;

import main.java.locations.TRectangularFace;
import main.java.locations.Tetra;
import main.java.ui.TetraUI;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class TetraStarSimulator {
    public SimulationUIMediator mediator;

    public TetraStarSimulator(){
        Tetra.instance.setTFace(new TRectangularFace(5, 8));
    }

    public void start(){
        System.out.println("start");
    }

    public void stop(){
        System.out.println("stop");
    }

    public void nextStep(){
        System.out.println("nextStep");
    }

    public void render(Graphics g, TetraUIDrawingPanel p){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,p.getWidth(), p.getHeight());
        Tetra.instance.render(g,p);
    }

    public void setMediator(SimulationUIMediator mediator) {
        if(this.mediator == null) {
            this.mediator = mediator;
            mediator.setSimulation(this);
        }
    }

    ///////////////
    ///// CLIENT
    ///////////////

    public static void main(String[] args){
        SimulationUIMediator mediator = new SimulationUIMediator();

        TetraUI ui = new TetraUI();
        ui.setMediator(mediator);

        TetraStarSimulator simulator = new TetraStarSimulator();
        simulator.setMediator(mediator);
    }
}