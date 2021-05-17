package ui;

import java.awt.*;

public class TestTFaceRenderable implements TRenderable {
    public int x, y, xMax, yMax;
    public final int PADDING = 20;
    public TestTFaceRenderable(int x, int y, int xMax, int yMax){
        this.x = x;
        this.y = y;
        this.xMax = xMax;
        this.yMax = yMax;
    }
    @Override
    public void render(Graphics g, TetraUIDrawingPanel panel) {
        g.setColor(new Color(228,195,167,255));
        int finalXPadding = panel.xScale(PADDING);
        int finalYPadding = panel.yScale(PADDING);
        int widthPerCell = TetraUIDrawingPanel.WIDTH/xMax;
        int heightPerCell = TetraUIDrawingPanel.HEIGHT/yMax;
        int finalX = panel.xScale(x*widthPerCell+finalXPadding);
        int finalY = panel.yScale(y*heightPerCell+finalYPadding);
        int finalWidth = panel.xScale(widthPerCell - finalXPadding*2);
        int finalHeight = panel.yScale(heightPerCell - finalYPadding*2);
        g.fillRect(finalX,finalY,finalWidth,finalHeight);
    }

    @Override
    public int getRenderLayer() {
        return TRenderable.TFACE;
    }
}
