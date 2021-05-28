package main.java.starMap;

import main.java.logging.TLogger;

public class StarMapTextBody implements StarMapBody {

    private String direction;
    private String symbol;

    public StarMapTextBody(String direction) {
        this.direction = direction;
        symbol = "";
    }

    @Override
    public void encrypt(String symbol) {
        direction = new StringBuilder(direction).reverse().toString();
        this.symbol = symbol;
    }

    @Override
    public void decrypt() {
        direction = new StringBuilder(direction).reverse().toString();
        TLogger.shared.log("Symbol: " + symbol);
    }

    @Override
    public void display() {
        TLogger.shared.log("Content: " + direction);
    }
}
