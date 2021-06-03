package main.java.starMap;

public interface StarMapBody {
    void encrypt(AbstractEncryptionStrategy strategy);

    void decrypt(AbstractEncryptionStrategy strategy);

    void display();
}
