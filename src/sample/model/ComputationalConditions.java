package sample.model;

public class ComputationalConditions {
    double x0;
    double y0;
    double _X;
    double step;
    double h;
    double c;

    public ComputationalConditions() {
        x0 = 1;
        y0 = 0;
        _X = 8;
        step = 7;
        h = Math.abs(_X - x0) / step;
        c = 0;
    }

    public ComputationalConditions(double x0, double y0, double _X, double step) {
        this.x0 = x0;
        this.y0 = y0;
        this._X = _X;
        this.step = step;
        h = Math.abs(_X - x0) / step;
        c = 0;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double get_X() {
        return _X;
    }

    public double getStep() {
        return step;
    }

    public double getH() {
        return h;
    }

    public double getC() {
        return c;
    }
}
