public class Track{

    public int length;
    public TrainColor color;
    public City startCity;
    public City endCity;

    public Track(City startCity, City endCity, int length, TrainColor color) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.length = length;
        this.color = color;
    }

    public String toString() {
        return startCity + " to " + endCity + ", length: " + length + ", color: " + color;
    }

    public City getOtherCity(City city1) {
        if(startCity == city1) {
            return endCity;
        } else if(endCity == city1) {
            return startCity;
        }
        return null;
    }

    public boolean hasCity(City city) {
        if(startCity == city || endCity == city) {
            return true;
        }
        return false;
    }
}