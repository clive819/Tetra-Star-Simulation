package main.java.locations;

import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class TRectangularFace extends TFace {

    public int rows;
    public int cols;
    private final int CELL_RENDER_PADDING = 20;

    public TRectangularFace(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public Location getAdjacent(Location currentLocation, boolean heroBaseAllowed) {
        return null;
    }

    @Override
    public Location getVaderBase() {
        return null;
    }

    @Override
    public Location spawnRover() {
        return null;
    }

    @Override
    public Location spawnHero() {
        return null;
    }

    @Override
    public Location spawnVader() {
        return null;
    }

    @Override
    public void spawnVaderBase() {

    }

    @Override
    public void spawnHeroBase() {

    }

    public void render(Graphics g, TetraUIDrawingPanel p){
        for(int x = 0; x < cols; x++){
            for(int y = 0; y < rows; y++){
                renderCell(g, p, x, y);
            }
        }
    }

    public void renderCell(Graphics g, TetraUIDrawingPanel p, int x, int y) {
        g.setColor(new Color(228,195,167,255));
        int finalXPadding = p.xScale(CELL_RENDER_PADDING);
        int finalYPadding = p.yScale(CELL_RENDER_PADDING);
        int widthPerCell = p.WIDTH/cols;
        int heightPerCell = p.HEIGHT/rows;
        int finalX = p.xScale(x*widthPerCell+finalXPadding);
        int finalY = p.yScale(y*heightPerCell+finalYPadding);
        int finalWidth = p.xScale(widthPerCell - finalXPadding*2);
        int finalHeight = p.yScale(heightPerCell - finalYPadding*2);
        g.fillRect(finalX,finalY,finalWidth,finalHeight);
    }
}
