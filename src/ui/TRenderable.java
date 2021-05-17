package ui;

import java.awt.*;

// necessary interface for a class that is renderable on the screen by TetraUIDrawingPanel
public interface TRenderable {
    void render(Graphics g, TetraUIDrawingPanel panel);
    int getRenderLayer();

    //Predefined object layers, highest = render on top, (names pending)
    //if renderables all refer to these variables, render layer values can be easily adjusted
    int BACKGROUND = -1;
    int TFACE = 0;
    int LANDMARK = 1;
    int ROVER = 2;
    int STARMAP = 3;
}
