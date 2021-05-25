package main.java.locations;

import main.java.characters.*;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TRectangularFace extends TFace {

    public int rows;
    public int cols;

    private final ArrayList<ArrayList<Location>> cells = new ArrayList<>();
    private final ArrayList<TRover> rovers = new ArrayList<>();
    private Location vaderBase;

    private final int[][] offsets = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public TRectangularFace(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // initialize grid
        for (int row = 0; row < rows; row++) {
            ArrayList<Location> dummyArray = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                Location location = new Location(new Ground(), row, col, "Location" + "(" + row + ", " + col + ")");
                dummyArray.add(location);
            }
            cells.add(dummyArray);
        }

        testInitialize();
    }

    public void testInitialize() {
        spawnHero();
        spawnVader();
    }

    @Override
    public void nextStep() {
        for (TRover rover : rovers) {
            rover.update();
        }
    }

    @Override
    public Location getAdjacent(Location currentLocation, boolean heroBaseAllowed, boolean hasTFlier) {
        ArrayList<Location> dummyArray = getNeighbors(currentLocation, heroBaseAllowed, hasTFlier);
        return dummyArray.get(new Random().nextInt(dummyArray.size()));
    }

    @Override
    public Location getVaderBase() {
        return vaderBase;
    }

    @Override
    public Location spawnRover() {
        int[] coordinate = getRandomEmptyCoordinate();
        Location location = cells.get(coordinate[0]).get(coordinate[1]);

        TRover tRover = new TRover("Rover " + TRover.count, Gender.male, this, location);
        location.enter(tRover);

        rovers.add(tRover);
        return location;
    }

    @Override
    public Location spawnHero() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Location location = cells.get(row).get(col);

                if (location.terrain.getType() == TerrainType.heroBase && location.isEmpty()) {
                    THero tHero = new THero("Hero " + THero.count, Gender.male, this, location);
                    location.enter(tHero);
                    rovers.add(tHero);
                    return location;
                }
            }
        }

        // if no HeroBase exist
        spawnHeroBase();
        return spawnHero();
    }

    @Override
    public Location spawnVader() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Location location = cells.get(row).get(col);

                if (location.terrain.getType() == TerrainType.vaderBase && location.isEmpty()) {
                    TVader tVader = new TVader("Vader " + TVader.count, Gender.male, this, location);
                    location.enter(tVader);
                    rovers.add(tVader);
                    return location;
                }
            }
        }

        // if no VaderBase exist
        spawnVaderBase();
        return spawnVader();
    }

    @Override
    public void spawnVaderBase() {
        int[] coordinate = getRandomEmptyNonEdgeCoordinate();
        Location location = cells.get(coordinate[0]).get(coordinate[1]);
        ArrayList<Location> neighbors = getNeighbors(location, false, true);

        if (neighbors.size() < 4) {
            spawnVaderBase();
        } else {
            location.setTerrain(new TVaderBase());
            vaderBase = location;
            for (Location l : neighbors) {
                l.setTerrain(new River());
            }
        }
    }

    @Override
    public void spawnHeroBase() {
        int[] coordinate = getRandomEmptyEdgeCoordinate();
        Location location = cells.get(coordinate[0]).get(coordinate[1]);
        location.setTerrain(new THeroBase());
    }

    private int[] getRandomEmptyCoordinate() {
        int row = new Random().nextInt(rows);
        int col = new Random().nextInt(cols);

        if (!cells.get(row).get(col).isEmpty()) {
            // *** what to do if there are no empty location? ***
            return getRandomEmptyCoordinate();
        }
        return new int[]{row, col};
    }

    private int[] getRandomEmptyEdgeCoordinate() {
        ArrayList<int[]> dummyArray = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isEdge(row, col) && cells.get(row).get(col).isEmpty()) {
                    dummyArray.add(new int[]{row, col});
                }
            }
        }

        return dummyArray.get(new Random().nextInt(dummyArray.size()));
    }

    private int[] getRandomEmptyNonEdgeCoordinate() {
        ArrayList<int[]> dummyArray = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!isEdge(row, col) && cells.get(row).get(col).isEmpty()) {
                    dummyArray.add(new int[]{row, col});
                }
            }
        }

        return dummyArray.get(new Random().nextInt(dummyArray.size()));
    }

    private boolean isEdge(int row, int col) {
        return row == 0 || row == (rows - 1) || col == 0 || col == (cols - 1);
    }

    private ArrayList<Location> getNeighbors(Location currentLocation, boolean heroBaseAllowed, boolean hasTFlier) {
        ArrayList<Location> dummyArray = new ArrayList<>();

        for (int[] offset : offsets) {
            int row = currentLocation.row + offset[0];
            int col = currentLocation.col + offset[1];

            if (row > -1 && row < rows && col > -1 && col < cols) {
                Location location = cells.get(row).get(col);
                TerrainType type = location.terrain.getType();
                if ((type == TerrainType.heroBase && !heroBaseAllowed) || (type == TerrainType.river && !hasTFlier)) {
                    continue;
                }
                dummyArray.add(location);
            }
        }

        return dummyArray;
    }

    public void render(Graphics g, TetraUIDrawingPanel p) {
        for (ArrayList<Location> list : cells) {
            for (Location l : list) {
                l.render(g, p, this);
            }
        }
    }
}
