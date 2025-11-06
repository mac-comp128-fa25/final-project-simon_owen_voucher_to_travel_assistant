public class Track {

    private int length;
    private TrainColor color1;
    private TrainColor color2;
    private int tracks = 1;

    public Track(int length, TrainColor color) {
        this.length = length;
        this.color1 = color;
    }

    public Track(int length, TrainColor color1, TrainColor color2, int tracks) {
        this.length = length;
        this.color1 = color1;
        this.color2 = color2;
        this.tracks = tracks;
    }

    public int getLength() {
        return length;
    }

    public TrainColor getColor() {
        if(tracks > 1) {
            
        }
        return color;
    }

    public int getTracks() {
        return tracks;
    }

    public void removeTrack() {
        if(tracks > 0) {
            tracks -= 1;
        }
    }
    
}
