public class RouteCard extends Card{

    private String startCity;
    private String endCity;
    private int pointValue;

    public RouteCard(String startCity, String endCity) {
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public int getPointValue() {
        return pointValue;
    }
    
}
