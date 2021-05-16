public class StarMapEncryptedHeader implements StarMapHeader {

    private final StarMapUnencryptedHeader header;
    private final THero tHero;
    public String encryptionDate;
    public int restorationCount;
    String symbol;

    public StarMapEncryptedHeader(StarMapUnencryptedHeader header, THero tHero, String date, String symbol) {
        this.header = header;
        this.tHero = tHero;
        this.encryptionDate = date;
        this.restorationCount = 0;
        this.symbol = symbol;
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
    public StarMapHeader encrypted(THero tHero, String date, String symbol) {
        return this;
    }

    @Override
    public StarMapHeader decrypted(THero tHero) {
        TLogger.shared.log(this + " is decrypted, Symbol: " + symbol);
        return this.header;
    }

    @Override
    public boolean isEncrypted() {
        return true;
    }

    public String getHeroID() {
        return tHero.id;
    }

}
