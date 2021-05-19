package main.java.characters;

import main.java.locations.Terrain;
import main.java.locations.TerrainType;

public class THeroBase implements Terrain {
    @Override
    public TerrainType getType() {
        return TerrainType.heroBase;
    }
}
