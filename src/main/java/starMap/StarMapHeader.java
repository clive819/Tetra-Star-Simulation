package main.java.starMap;

import main.java.characters.THero;

public interface StarMapHeader {

    int getNumberOfItems();

    String getLocationID();

    StarMapHeader encrypted(THero tHero, String date, String symbol);

    StarMapHeader decrypted(THero tHero);

    boolean isEncrypted();

    boolean hasAuthority(THero tHero);

    void authorize(THero tHero);

    void display();

    void addStarMap(StarMap map);

}
