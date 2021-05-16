public interface StarMapHeader {

    int getNumberOfItems();

    String getLocationID();

    StarMapHeader encrypted(THero tHero, String date);

    StarMapHeader decrypted(THero tHero);

}
