package sample.model;

public class ComputationalConditions {
    double x0;
    double y0;
    double _X;
    int n0;
    int N;
    int step;
    double h;
    double c;

    public ComputationalConditions() {
        x0 = 1;
        y0 = 0;
        _X = 8;
        N = 7;
        n0 = 2;
        step = Math.abs(N);
        h = Math.abs(_X - x0) / step;
        c = 0;
    }

    public ComputationalConditions(double x0, double y0, double _X, int n0, int N) {
        this.x0 = x0;
        this.y0 = y0;
        this._X = _X;
        this.n0 = n0;
        this.N = N;
        this.step = Math.abs(N);
        h = Math.abs(_X - x0) / step;
        c = 0;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
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

    public int getN0() {
        return n0;
    }

    public double get_X() {
        return _X;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }
}
