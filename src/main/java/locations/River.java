package main.java.locations;

public class River implements Terrain {
    @Override
    public TerrainType getType() {
        return TerrainType.river;
    }
}
