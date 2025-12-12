public class RouteCard extends Card{

    public final City startCity;
    public final City endCity;
    public final int pointValue;

    public RouteCard(City startCity, City endCity, int pointValue) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.pointValue = pointValue;
    }

    @Override
    public String toString() {
        return (startCity.toString() + " -> " + endCity.toString() + " for " + pointValue + " points!");
    }
}
