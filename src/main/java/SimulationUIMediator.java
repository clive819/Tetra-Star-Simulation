package main.java;

import main.java.ui.TetraUIButtonsPanel;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class SimulationUIMediator {
    private TetraUIButtonsPanel buttonsPanel;
    private TetraUIDrawingPanel drawingPanel;
    private TetraStarSimulator simulation;

    // one time setters for each connectable object
    public void setButtonsPanel(TetraUIButtonsPanel buttonsPanel) {
        if (this.buttonsPanel == null) {
            this.buttonsPanel = buttonsPanel;
        }
    }

    public void setSimulation(TetraStarSimulator simulation) {
        if (this.simulation == null) {
            this.simulation = simulation;
        }
    }

    public void setDrawingPanel(TetraUIDrawingPanel drawingPanel) {
        if (this.drawingPanel == null) {
            this.drawingPanel = drawingPanel;
        }
    }

    // control tools
    public void start() {
        simulation.start();
    }

    public void stop() {
        simulation.stop();
    }

    public void nextStep() {
        simulation.nextStep();
    }

    public void render(Graphics g, TetraUIDrawingPanel p) {
        simulation.render(g, p);
    }

    public void updateRender() {
        this.drawingPanel.repaint();
    }
}
