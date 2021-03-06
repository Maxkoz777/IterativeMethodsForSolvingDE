package sample.model.util;

public class Coordinate implements Comparable<Coordinate>{
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Coordinate o) {
        return Double.compare(x, o.x);
    }
}
