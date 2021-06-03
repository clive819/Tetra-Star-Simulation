package main.java.locations;

import main.java.characters.Colors;
import main.java.characters.TFlier;
import main.java.characters.TRover;
import main.java.starMap.AbstractStarMap;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;

public class Location {

    public int row;
    public int col;
    public TRover rover;
    public Terrain terrain;
    public String id;
    public AbstractStarMap starMap;

    private final int TILE_PADDING = 10;

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
        boolean renderTerrainName = false;
        switch (this.terrain) {
            case river:
                g.setColor(Colors.Terrain.river);
                renderTerrainName = true;
                break;
            case ground:
                g.setColor(Colors.Terrain.ground);
                break;
            case heroBase:
                g.setColor(Colors.Terrain.heroBase);
                renderTerrainName = true;
                break;
            case vaderBase:
                g.setColor(Colors.Terrain.vaderBase);
                renderTerrainName = true;
                break;
            case mapBase:
                g.setColor(Colors.Terrain.mapBase);
                renderTerrainName = true;
                break;
            default:
                g.setColor(Colors.Terrain.defaultColor);
        }

        int finalXPadding = TILE_PADDING;
        int finalYPadding = TILE_PADDING;
        int widthPerCell = p.WIDTH / f.cols;
        int heightPerCell = p.HEIGHT / f.rows;
        int finalX = p.xScale(col * widthPerCell + finalXPadding);
        int finalY = p.yScale(row * heightPerCell + finalYPadding);
        int finalWidth = p.xScale(widthPerCell - finalXPadding * 2);
        int finalHeight = p.yScale(heightPerCell - finalYPadding * 2);
        g.fillRect(finalX, finalY, finalWidth, finalHeight);
        if (renderTerrainName) {
            g.setColor(Colors.Terrain.text);
            g.drawString(this.terrain.toString(), finalX, finalY + finalHeight);
        }

        if (this.starMap != null) {
            renderStarMap(g, p, f, this.starMap, false);
        }
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
            int roverXPadding = widthPerCell / 6;
            int roverYPadding = heightPerCell / 6;
            int finalX = p.xScale(col * widthPerCell + roverXPadding);
            int finalY = p.yScale(row * heightPerCell + roverYPadding);
            int finalWidth = p.xScale(widthPerCell - roverXPadding * 2);
            int finalHeight = p.yScale(heightPerCell - roverYPadding * 2);
            g.fillOval(finalX, finalY, finalWidth, finalHeight);
            g.setColor(Colors.Rover.text);
            g.drawString(this.rover.id, finalX, finalY + finalHeight / 2);

            TFlier flier = rover.getFlier();
            if (flier != null) {
                int flierHeight = finalHeight / 4;
                int flierY = finalY + finalHeight - flierHeight;
                g.setColor(Colors.Rover.flier);
                g.fillRect(finalX, flierY, finalWidth, flierHeight);
                g.setColor(Colors.Rover.text);
                g.drawString("flier", finalX, flierY + flierHeight);
            }

            AbstractStarMap map = rover.getStarMap();
            if (map != null) {
                renderStarMap(g, p, f, map, true);
            }
        }
    }

    private void renderStarMap(Graphics g, TetraUIDrawingPanel p, TRectangularFace f, AbstractStarMap starMap, boolean onRover) {
        g.setColor(Colors.StarMap.map);

        int widthPerCell = p.WIDTH / f.cols;
        int heightPerCell = p.HEIGHT / f.rows;
        int xPadding = widthPerCell / 3;
        int yPadding = heightPerCell / 3;
        int finalX = p.xScale(col * widthPerCell + xPadding);
        int finalY = p.yScale(row * heightPerCell + yPadding);
        if (!onRover) {
            finalX = p.xScale(col * widthPerCell + TILE_PADDING);
            finalY = p.yScale(row * heightPerCell + TILE_PADDING);
        }
        int finalWidth = p.xScale(widthPerCell - xPadding * 2);
        int finalHeight = p.yScale(heightPerCell - yPadding * 2);

        g.fillRect(finalX, finalY, finalWidth, finalHeight);

        g.setColor(Colors.StarMap.text);

        String extraMapCount = "";
        int numItems = starMap.numberOfItems();
        if (numItems > 1) {
            extraMapCount = " x" + numItems;
        }
        g.drawString("map" + extraMapCount, finalX, finalY + finalHeight);
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
