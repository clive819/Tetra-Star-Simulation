package main.java.ui;

import main.java.SimulationUIMediator;

import javax.swing.*;
import java.awt.*;

public class TetraUIDrawingPanel extends JPanel {

    // The reference max width and height:
    // for example, if an object is defined to have a width of 200 pixels and the WIDTH value is 2000,
    // the object should take up 1/10 of the width after all scaling calculations are completed
    public final int WIDTH = 2000;
    public final int HEIGHT = 1200;

    private SimulationUIMediator mediator;

    // render call
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mediator != null) {
            mediator.render(g, this);
        }
    }

    public double getXScale() {
        return (double) getWidth() / WIDTH;
    }

    public double getYScale() {
        return (double) getHeight() / HEIGHT;
    }

    //given an x/y value, convert it to the proper x/y relative to the max WIDTH and HEIGHT
    public int xScale(int x) {
        return (int) ((double) x * getWidth() / WIDTH);
    }

    public int yScale(int y) {
        return (int) ((double) y * getHeight() / HEIGHT);
    }

    public void setMediator(SimulationUIMediator mediator) {
        if (this.mediator == null) {
            this.mediator = mediator;
            mediator.setDrawingPanel(this);
        }
    }
}
