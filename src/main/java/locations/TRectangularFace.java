package main.java.locations;

import main.java.characters.Gender;
import main.java.characters.THero;
import main.java.characters.TRover;
import main.java.characters.TVader;
import main.java.starMap.StarMap;
import main.java.starMap.StarMapBody;
import main.java.starMap.StarMapTextBody;
import main.java.ui.TetraUIDrawingPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TRectangularFace extends TFace {

    public int rows;
    public int cols;

    private ArrayList<ArrayList<Location>> cells;
    private ArrayList<TRover> rovers;
    private Location vaderBase;

    private final int[][] offsets = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public TRectangularFace(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        reset();
    }

    private void initialize() {
        for (int row = 0; row < rows; row++) {
            ArrayList<Location> dummyArray = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                Location location = new Location(Terrain.ground, row, col, "Location" + "(" + row + ", " + col + ")");
                dummyArray.add(location);
            }
            cells.add(dummyArray);
        }
    }

    @Override
    public void reset() {
        cells = new ArrayList<>();
        rovers = new ArrayList<>();
        initialize();
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
    public void spawnRover() {
        int[] coordinate = getRandomEmptyCoordinate(true);
        Location location = cells.get(coordinate[0]).get(coordinate[1]);

        TRover tRover = new TRover("Rover " + TRover.count, Gender.male, this, location);
        location.enter(tRover);

        rovers.add(tRover);
    }

    @Override
    public void spawnHero() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Location location = cells.get(row).get(col);

                if (location.terrain == Terrain.heroBase && location.isEmpty(false)) {
                    THero tHero = new THero("Hero " + THero.count, Gender.male, this, location);
                    location.enter(tHero);
                    rovers.add(tHero);
                    return;
                }
            }
        }

        // if no HeroBase exist
        spawnHeroBase();
        spawnHero();
    }

    @Override
    public void spawnVader() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Location location = cells.get(row).get(col);

                if (location.terrain == Terrain.vaderBase && location.isEmpty(false)) {
                    TVader tVader = new TVader("Vader " + TVader.count, Gender.male, this, location);
                    location.enter(tVader);
                    rovers.add(tVader);
                    return;
                }
            }
        }

        // if no VaderBase exist
        spawnVaderBase();
        spawnVader();
    }

    @Override
    public void spawnMapBase() {
        int[] coordinate = getRandomEmptyCoordinate(true);
        Location location = cells.get(coordinate[0]).get(coordinate[1]);

        StarMap starMap = new StarMap("StarMap " + StarMap.count, location.id);
        StarMapBody starMapBody = new StarMapTextBody("Directions to Planet Earth from Tetra");

        starMap.setBody(starMapBody);
        location.setTerrain(Terrain.mapBase);
        location.store(starMap);
    }

    @Override
    public void spawnVaderBase() {
        int[] coordinate = getRandomEmptyNonEdgeCoordinate(true);
        Location location = cells.get(coordinate[0]).get(coordinate[1]);
        ArrayList<Location> neighbors = getNeighbors(location, false, true);

        if (neighbors.size() < 4) {
            spawnVaderBase();
        } else {
            location.setTerrain(Terrain.vaderBase);
            vaderBase = location;
            for (Location l : neighbors) {
                l.setTerrain(Terrain.river);
            }
        }
    }

    @Override
    public void spawnHeroBase() {
        int[] coordinate = getRandomEmptyEdgeCoordinate(true);
        Location location = cells.get(coordinate[0]).get(coordinate[1]);
        location.setTerrain(Terrain.heroBase);
    }

    private int[] getRandomEmptyCoordinate(boolean requireGround) {
        int row = new Random().nextInt(rows);
        int col = new Random().nextInt(cols);

        if (!cells.get(row).get(col).isEmpty(requireGround)) {
            // *** what to do if there are no empty location? ***
            return getRandomEmptyCoordinate(requireGround);
        }
        return new int[]{row, col};
    }

    private int[] getRandomEmptyEdgeCoordinate(boolean requireGround) {
        ArrayList<int[]> dummyArray = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isEdge(row, col) && cells.get(row).get(col).isEmpty(requireGround)) {
                    dummyArray.add(new int[]{row, col});
                }
            }
        }

        return dummyArray.get(new Random().nextInt(dummyArray.size()));
    }

    private int[] getRandomEmptyNonEdgeCoordinate(boolean requireGround) {
        ArrayList<int[]> dummyArray = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!isEdge(row, col) && cells.get(row).get(col).isEmpty(requireGround)) {
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
                if ((location.terrain == Terrain.heroBase && !heroBaseAllowed) || (location.terrain == Terrain.river && !hasTFlier)) {
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
