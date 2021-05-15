public class THero extends TRover implements TFlierOperations {

    TFlier tFlier;
    Location base;

    public THero(int id, Gender gender, TFace tFace, Location location) {
        super(id, gender, tFace, location);
        this.base = location;
    }


    @Override
    public void move() {
        Location nextLocation = tFace.getAdjacent(currentLocation, true);

        if (nextLocation != null) {
            TLogger.shared.log(this + " moved from " + currentLocation + " to " + nextLocation);
            currentLocation.move2(nextLocation);
            currentLocation = nextLocation;
        } else {
            TLogger.shared.log(this + " cannot make a move anymore");
        }
    }

    @Override
    public void fly2(Location location) {

    }

    private void encrypt() {

    }

    private void decrypt() {

    }

    @Override
    public String toString() {
        return "THero{" +
                "id=" + id +
                '}';
    }

}
