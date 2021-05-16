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
    public StarMapHeader encrypted(THero tHero, String date, String symbol) {
        return new StarMapEncryptedHeader(this, tHero, date, symbol);
    }


    @Override
    public StarMapHeader decrypted(THero tHero) {
        return this;
    }

    @Override
    public boolean isEncrypted() {
        return false;
    }

}
