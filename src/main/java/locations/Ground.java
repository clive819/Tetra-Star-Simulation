package main.java.locations;

public class Ground implements Terrain {
    @Override
    public TerrainType getType() {
        return TerrainType.ground;
    }
}
