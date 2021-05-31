package main.java.locations;

import main.java.characters.Colors;
import main.java.characters.TRover;
import main.java.starMap.AbstractStarMap;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class Location {

    int row;
    int col;

    public TRover rover;
    public Terrain terrain;
    public String id;
    public AbstractStarMap starMap;

    private final int RENDER_PADDING = 20;

    public Location(Terrain terrain, int row, int col, String id) {
        this.row = row;
        this.col = col;
        this.terrain = terrain;
        this.id = id;
        starMap = null;
    }

    public boolean isEmpty(boolean requireGround) {
        return rover == null && (!requireGround || terrain == Terrain.ground);
    }

    public boolean hasStarMap() {
        return starMap != null;
    }

    public void enter(TRover rover) {
        this.rover = rover;
    }

    public void leave(TRover rover) {
        if (this.rover == rover) {
            this.rover = null;
        }
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void store(AbstractStarMap starMap) {
        if (starMap == null) {
            return;
        }

        if (this.starMap == null) {
            this.starMap = starMap;
        } else {
            this.starMap = this.starMap.add(starMap);
        }
        starMap.setLocation(this);
    }

    public void render(Graphics g, TetraUIDrawingPanel p, TRectangularFace f) {
        renderTerrain(g, p, f);
        renderRover(g, p, f);
    }

    private void renderTerrain(Graphics g, TetraUIDrawingPanel p, TRectangularFace f) {
        switch (this.terrain) {
            case river:
                g.setColor(Colors.Terrain.river);
                break;
            case ground:
                g.setColor(Colors.Terrain.ground);
                break;
            case heroBase:
                g.setColor(Colors.Terrain.heroBase);
                break;
            case vaderBase:
                g.setColor(Colors.Terrain.vaderBase);
                break;
            default:
                g.setColor(Colors.Terrain.defaultColor);
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

    private void renderRover(Graphics g, TetraUIDrawingPanel p, TRectangularFace f) {
        if (this.rover != null) {
            if (this.rover.isHero()) {
                g.setColor(Colors.Rover.hero);
            } else if (this.rover.isVader()) {
                g.setColor(Colors.Rover.vader);
            } else {
                g.setColor(Colors.Rover.rover);
            }

            int widthPerCell = p.WIDTH / f.cols;
            int heightPerCell = p.HEIGHT / f.rows;
            int roverXPadding = p.xScale(widthPerCell / 2);
            int roverYPadding = p.yScale(heightPerCell / 2);
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
                '}';
    }
}
