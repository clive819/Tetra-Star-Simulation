package main.java.starMap;

import main.java.logging.TLogger;

public class StarMapTextBody implements StarMapBody {

    String direction;

    public StarMapTextBody(String direction) {
        this.direction = direction;
    }

    @Override
    public void encrypt() {
        direction = new StringBuilder(direction).reverse().toString();
    }

    @Override
    public void decrypt() {
        direction = new StringBuilder(direction).reverse().toString();
    }

    @Override
    public void display() {
        TLogger.shared.log("Content: " + direction);
    }
}
