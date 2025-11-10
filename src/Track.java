public class Track {

    private int length;
    private TrainColor color;

    public Track(int length, TrainColor color) {
        this.length = length;
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public TrainColor getColor() {
        return color;
    }
}
