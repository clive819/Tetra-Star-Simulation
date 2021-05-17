package ui;

import java.awt.*;

public class TestBackgroundRenderable implements TRenderable {
    int x, y, max_x, max_y;
    @Override
    public void render(Graphics g, TetraUIDrawingPanel panel) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,panel.getWidth(), panel.getHeight());
    }

    @Override
    public int getRenderLayer() {
        return TRenderable.BACKGROUND;
    }
}
