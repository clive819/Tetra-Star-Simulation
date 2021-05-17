package ui;

import java.awt.*;

public class TestForegroundRenderable implements TRenderable {
    @Override
    public void render(Graphics g, TetraUIDrawingPanel panel) {
        g.setColor(Color.BLUE);
        g.fillRect(panel.xScale(50),panel.yScale(50),panel.xScale(100), panel.yScale(100));
    }

    @Override
    public int getRenderLayer() {
        return TRenderable.ROVER;
    }
}
