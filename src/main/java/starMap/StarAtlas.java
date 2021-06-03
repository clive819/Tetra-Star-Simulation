package main.java.starMap;

import main.java.characters.THero;
import main.java.locations.Location;

import java.util.ArrayList;

public class StarAtlas extends AbstractStarMap {

    private final ArrayList<AbstractStarMap> starMaps;

    public StarAtlas(String id, Location base) {
        super(id, base);
        starMaps = new ArrayList<>();
    }

    @Override
    public int numberOfItems() {
        return starMaps.size();
    }

    @Override
    public void encrypt(THero tHero) {
        for (AbstractStarMap starMap : starMaps) {
            starMap.encrypt(tHero);
        }
    }

    @Override
    public void decrypt(THero tHero) {
        for (AbstractStarMap starMap : starMaps) {
            starMap.decrypt(tHero);
        }
    }

    @Override
    public void display(THero tHero, int date) {
        for (AbstractStarMap starMap : starMaps) {
            starMap.display(tHero, date);
        }
    }

    @Override
    public AbstractStarMap add(AbstractStarMap abstractStarMap) {
        starMaps.add(abstractStarMap);
        return this;
    }

    @Override
    public boolean hasAuthority(THero tHero) {
        for (AbstractStarMap starMap : starMaps) {
            if (starMap.hasAuthority(tHero)) {
                return true;
            }
        }
        return false;
    }
}
