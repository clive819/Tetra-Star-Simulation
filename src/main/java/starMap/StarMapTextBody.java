package main.java.starMap;

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
}
