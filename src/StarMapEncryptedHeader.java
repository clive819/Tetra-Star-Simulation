public class StarMapEncryptedHeader implements StarMapHeader {

    private final StarMapUnencryptedHeader header;
    private final THero tHero;
    public String encryptionDate;
    public int restorationCount;

    public StarMapEncryptedHeader(StarMapUnencryptedHeader header, THero tHero, String date) {
        this.header = header;
        this.tHero = tHero;
        this.encryptionDate = date;
        this.restorationCount = 0;
    }

    @Override
    public int getNumberOfItems() {
        return 1;
    }

    @Override
    public String getLocationID() {
        return header.getLocationID();
    }

    @Override
    public StarMapHeader encrypted(THero tHero, String date) {
        return this;
    }

    @Override
    public StarMapHeader decrypted(THero tHero) {
        return this.header;
    }

    public String getHeroID() {
        return tHero.id;
    }

}
