package sample.model.methods;

import sample.model.Coordinate;
import sample.model.MethodName;

import java.util.ArrayList;

public class RungeKuttaMethod extends Method {
    public RungeKuttaMethod() {
        super(MethodName.RUNGE_KUTTA);
    }

    @Override
    public void fullFillStorage() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(x0, y0));
        // px - previous value of x, so no need to go through storage for finding it
        // so complexity is O(n), but not O(n^2), so does py
        double px = x0, py = y0;
        for (int i = 1; i < step; i++) {
            double x = px + h;
            double k1 = f(px, py);
            double k2 = f(px + h / 2, py + h * k1 / 2);
            double k3 = f(px + h / 2, py + h * k2 / 2);
            double k4 = f(px + h, py + h * k3);
            double y = py + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            coordinates.add(new Coordinate(x, y));
            px = x;
            py = y;
        }
        storage.setCoordinates(coordinates);
    }
}
