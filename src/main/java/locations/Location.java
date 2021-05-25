package main.java.locations;

import main.java.characters.TRover;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class Location {

    int row;
    int col;
    TRover rover;
    Terrain terrain;
    public String id;
    private final int RENDER_PADDING = 20;

    public Location(Terrain terrain, int row, int col, String id) {
        this.row = row;
        this.col = col;
        this.terrain = terrain;
        this.id = id;
    }

    public boolean isEmpty() {
        return rover == null;
    }

    public void occupiedBy(TRover rover) {
        this.rover = rover;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void move2(Location newLocation) {
        newLocation.occupiedBy(rover);
        rover = null;
    }

    public void render(Graphics g, TetraUIDrawingPanel p, TRectangularFace f){
        renderTerrain(g, p, f);
        renderRover(g, p, f);
    }

    private void renderTerrain(Graphics g, TetraUIDrawingPanel p, TRectangularFace f){
        switch(this.terrain.getType()){
            case river:
                g.setColor(new Color(49, 160, 228, 255));
                break;
            case ground:
                g.setColor(new Color(228, 195, 167, 255));
                break;
            case heroBase:
                g.setColor(new Color(52, 109, 107, 255));
                break;
            case vaderBase:
                g.setColor(new Color(115, 56, 59, 255));
                break;
            default:
                g.setColor(new Color(171, 172, 168, 255));
        }

        int finalXPadding = p.xScale(RENDER_PADDING);
        int finalYPadding = p.yScale(RENDER_PADDING);
        int widthPerCell = p.WIDTH / f.cols;
        int heightPerCell = p.HEIGHT / f.rows;
        int finalX = p.xScale(col * widthPerCell + finalXPadding);
        int finalY = p.yScale(row * heightPerCell + finalYPadding);
        int finalWidth = p.xScale(widthPerCell - finalXPadding * 2);
        int finalHeight = p.yScale(heightPerCell - finalYPadding * 2);
        g.fillRect(finalX, finalY, finalWidth, finalHeight);
    }

    private void renderRover(Graphics g, TetraUIDrawingPanel p, TRectangularFace f){
        if(this.rover != null){
            if(this.rover.isHero()){
                g.setColor(new Color(12, 200, 185, 255));
            }else if(this.rover.isVader()){
                g.setColor(new Color(173, 0, 5, 255));
            }else{
                g.setColor(new Color(175, 176, 175, 255));
            }

            int widthPerCell = p.WIDTH / f.cols;
            int heightPerCell = p.HEIGHT / f.rows;
            int roverXPadding = p.xScale(widthPerCell/2);
            int roverYPadding = p.yScale(heightPerCell/2);
            int finalX = p.xScale(col * widthPerCell + roverXPadding);
            int finalY = p.yScale(row * heightPerCell + roverYPadding);
            int finalWidth = p.xScale(widthPerCell - roverXPadding * 2);
            int finalHeight = p.yScale(heightPerCell - roverYPadding * 2);
            g.fillOval(finalX, finalY, finalWidth, finalHeight);
        }
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                ", terrain=" + terrain +
                '}';
    }
}
