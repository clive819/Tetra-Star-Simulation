package main.java.locations;

import main.java.characters.TRover;

public class Location {

    int row;
    int col;
    TRover rover;
    Terrain terrain;
    public String id;

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

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                ", terrain=" + terrain +
                '}';
    }
}
