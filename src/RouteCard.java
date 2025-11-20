public class RouteCard extends Card{

    public City startCity;
    public City endCity;
    public int pointValue;

    public RouteCard(City startCity, City endCity, int pointValue) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.pointValue = pointValue;
    }
}
