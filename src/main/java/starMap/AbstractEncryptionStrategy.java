package main.java.starMap;

public abstract class AbstractEncryptionStrategy {
    protected String symbol;

    public AbstractEncryptionStrategy(String symbol){
        this.symbol = symbol;
    }

    public String encrypt(String input){
        return input;
    }

    public String decrypt(String input){
        return input;
    }

    public String getSymbol(){
        return symbol;
    }
}
