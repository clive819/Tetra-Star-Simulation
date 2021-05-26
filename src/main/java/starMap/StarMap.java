package main.java.starMap;

import main.java.characters.THero;
import main.java.logging.TLogger;

public class StarMap {

    public String id;
    public StarMapHeader header;
    public StarMapBody body;

    public StarMap(String id, StarMapHeader header, StarMapBody body) {
        this.id = id;
        this.header = header;
        this.body = body;
    }

    public void encrypt(THero tHero, String date, String symbol) {
        if (!header.isEncrypted()) {
            header = header.encrypted(tHero, date, symbol);
            body.encrypt();
            TLogger.shared.log(this + " encrypted successfully");
        }
    }

    public void decrypt(THero tHero) {
        if (header.isEncrypted()) {
            StarMapEncryptedHeader encryptedHeader = (StarMapEncryptedHeader) header;

            if (encryptedHeader.hasAuthority(tHero)) {
                header = header.decrypted(tHero);
                body.decrypt();
                TLogger.shared.log(this + " decrypted successfully");
            } else {
                TLogger.shared.log("Decryption failed: " + tHero + " tried to decrypt " + this
                        + " that wasn't encrypted by him.");
            }
        }
    }

    public void display() {
        if (header.getNumberOfItems() > 1) {
            header.display();
        }else {
            body.display();
        }
    }

    @Override
    public String toString() {
        return "StarMap{" +
                "id='" + id + '\'' +
                '}';
    }
}
