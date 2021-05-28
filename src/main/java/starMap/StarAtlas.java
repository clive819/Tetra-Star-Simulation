package main.java.starMap;

import main.java.characters.THero;

import java.util.ArrayList;

public class StarAtlas extends AbstractStarMap {

    private ArrayList<AbstractStarMap> starMaps;

    public StarAtlas(String id, String locationID) {
        super(id, locationID);
        starMaps = new ArrayList<>();
    }

    @Override
    public int numberOfItems() {
        return starMaps.size();
    }

    @Override
    public void encrypt(THero tHero, String symbol) {
        for (AbstractStarMap starMap:starMaps) {
            starMap.encrypt(tHero, symbol);
        }
    }

    @Override
    public void decrypt(THero tHero) {
        for (AbstractStarMap starMap:starMaps) {
            starMap.decrypt(tHero);
        }
    }

    @Override
    public void display() {
        for (AbstractStarMap starMap:starMaps) {
            starMap.display();
        }
    }

    @Override
    public AbstractStarMap add(AbstractStarMap abstractStarMap) {
        starMaps.add(abstractStarMap);
        return this;
    }

    @Override
    public boolean hasAuthority(THero tHero) {
        for (AbstractStarMap starMap:starMaps) {
            if (starMap.hasAuthority(tHero)) {
                return true;
            }
        }
        return false;
    }
}
