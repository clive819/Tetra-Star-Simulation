package main.java.starMap;

import main.java.characters.THero;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class AbstractStarMap implements Cloneable {

    public String id;
    public String locationID;
    public String dateOfEncryption;
    public ArrayList<THero> heroes;
    public int restorationCount;

    public static int count = 0;

    static HashSet<String> locations = new HashSet<>();


    public AbstractStarMap(String id, String locationID) {
        this.id = id;
        setLocationID(locationID);
        dateOfEncryption = "";
        heroes = new ArrayList<>();
        restorationCount = 0;
        count += 1;
    }


    @Override
    public AbstractStarMap clone() throws CloneNotSupportedException {
        return (AbstractStarMap)super.clone();
    }

    public abstract int numberOfItems();

    public abstract void encrypt(THero tHero, String symbol);

    public abstract void decrypt(THero tHero);

    public abstract void display();

    public abstract AbstractStarMap add(AbstractStarMap abstractStarMap);

    public boolean isEncrypted() {
        return !dateOfEncryption.equals("");
    }

    public boolean hasAuthority(THero tHero) {
        return heroes.contains(tHero);
    }

    public void authorize(THero tHero) {
        heroes.add(tHero);
    }

    public void setLocationID(String locationID) {
        if (this.locationID != null) {
            locations.remove(this.locationID);
        }
        this.locationID = locationID;
        locations.add(locationID);
    }

    public static boolean ping(String locationID) {
        return locations.contains(locationID);
    }

    @Override
    public String toString() {
        return "StarMap{" +
                "id='" + id + '\'' +
                '}';
    }
}
