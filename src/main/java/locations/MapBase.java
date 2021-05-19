package main.java.locations;

public class MapBase implements Terrain {
    @Override
    public TerrainType getType() {
        return TerrainType.mapBase;
    }
}
