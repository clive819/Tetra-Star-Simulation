import java.util.ArrayList;

public class StarAtlasHeader implements StarMapHeader {

    ArrayList<StarMap> maps;
    private final Location location;

    public StarAtlasHeader(Location location) {
        this.location = location;
        this.maps = new ArrayList<>();
    }

    @Override
    public int getNumberOfItems() {
        return maps.size();
    }

    @Override
    public String getLocationID() {
        return location.id;
    }

    @Override
    public StarMapHeader encrypted(THero tHero, String date) {
        for (StarMap map : maps) {
            map.encrypt(tHero, date);
        }
        return this;
    }

    @Override
    public StarMapHeader decrypted(THero tHero) {
        for (StarMap map : maps) {
            map.decrypt(tHero);
        }
        return this;
    }

    public void addStarMap(StarMap map) {
        maps.add(map);
    }

}
