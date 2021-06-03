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
    public void encrypt(AbstractEncryptionStrategy strategy) {
        direction = strategy.encrypt(direction);
        this.symbol = strategy.getSymbol();
    }

    @Override
    public void decrypt(AbstractEncryptionStrategy strategy) {
        direction = strategy.decrypt(direction);
        TLogger.shared.log("Symbol: " + symbol);
    }

    @Override
    public void display() {
        TLogger.shared.log("Content: " + direction);
    }
}
