public class Track{

    public int length;
    public TrainColor color;
    public City startCity;
    public City endCity;

    public Track(int length, TrainColor color) {
        this.length = length;
        this.color = color;
    }

    public Track(City startCity, City endCity, int length, TrainColor color) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.length = length;
        this.color = color;
    }

    public String toString() {
        return startCity + " to " + endCity + ", length: " + length + ", color: " + color;
    }
}
