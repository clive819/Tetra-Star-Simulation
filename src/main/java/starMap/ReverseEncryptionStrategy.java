package main.java.starMap;

public class ReverseEncryptionStrategy extends AbstractEncryptionStrategy {

    public ReverseEncryptionStrategy(String symbol) {
        super(symbol);
    }

    public String encrypt(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public String decrypt(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
