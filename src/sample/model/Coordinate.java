package sample.model;

public class Coordinate implements Comparable<Coordinate>{
    double x;
    double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Coordinate o) {
        return Double.compare(x, o.x);
    }
}
