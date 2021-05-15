public abstract class TFace {

    public abstract Location getAdjacent(Location currentLocation, boolean heroBaseAllowed);

    public abstract Location getVaderBase();

    public abstract Location spawnRover();

    public abstract Location spawnHero();

    public abstract Location spawnVader();

    public abstract void spawnVaderBase();

    public abstract void spawnHeroBase();

}
