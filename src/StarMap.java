public class StarMap {

    StarMapHeader header;
    StarMapBody body;

    public StarMap(StarMapHeader header, StarMapBody body) {
        this.header = header;
        this.body = body;
    }

    public void encrypt(THero tHero, String date) {
        header = header.encrypted(tHero, date);
        body.encrypt();
    }

    public void decrypt(THero tHero) {
        header = header.decrypted(tHero);
        body.decrypt();
    }

}
