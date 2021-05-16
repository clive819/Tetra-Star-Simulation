public class StarMapUnencryptedHeader implements StarMapHeader {

    private final Location location;

    public StarMapUnencryptedHeader(Location location) {
        this.location = location;
    }

    @Override
    public int getNumberOfItems() {
        return 1;
    }

    @Override
    public String getLocationID() {
        return location.id;
    }

    @Override
    public StarMapHeader encrypted(THero tHero, String date) {
        return new StarMapEncryptedHeader(this, tHero, date);
    }


    @Override
    public StarMapHeader decrypted(THero tHero) {
        return null;
    }

}
