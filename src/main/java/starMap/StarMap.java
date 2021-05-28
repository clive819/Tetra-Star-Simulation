package main.java.starMap;

import main.java.characters.THero;
import main.java.logging.TLogger;

import java.util.Date;

public class StarMap extends AbstractStarMap {

    private StarMapBody body;

    public StarMap(String id, String locationID) {
        super(id, locationID);
    }

    public void setBody(StarMapBody starMapBody) {
        body = starMapBody;
    }

    @Override
    public int numberOfItems() {
        return 1;
    }

    @Override
    public void encrypt(THero tHero, String symbol) {
        if (!isEncrypted()) {
            dateOfEncryption = new Date().toString();
            body.encrypt(symbol);
            heroes.add(tHero);
            TLogger.shared.log(this + " is encrypted by " + tHero);
        }
    }

    @Override
    public void decrypt(THero tHero) {
        if (isEncrypted() && hasAuthority(tHero)) {
            body.decrypt();
            dateOfEncryption = "";
            TLogger.shared.log(this + " is decrypted by " + tHero);
        }
    }

    @Override
    public void display() {
        TLogger.shared.log("********************");
        body.display();
        TLogger.shared.log("********************");
    }

    @Override
    public AbstractStarMap add(AbstractStarMap abstractStarMap) {
        StarAtlas starAtlas = new StarAtlas("StarAtlas" + StarAtlas.count, locationID);
        starAtlas.add(this);
        starAtlas.add(abstractStarMap);
        return starAtlas;
    }

}
