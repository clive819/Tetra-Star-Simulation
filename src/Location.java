public class Location {

    int row;
    int col;
    TRover rover;
    Terrain terrain;

    public Location(Terrain terrain, int row, int col) {
        this.row = row;
        this.col = col;
        this.terrain = terrain;
    }

    public boolean isEmpty() {
        return rover == null;
    }

    public void occupiedBy(TRover rover) {
        this.rover = rover;
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
