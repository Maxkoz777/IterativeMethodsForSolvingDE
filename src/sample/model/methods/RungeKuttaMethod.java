package sample.model.methods;

import sample.model.util.ComputationalConditions;
import sample.model.util.Coordinate;
import sample.model.util.MethodName;

import java.util.ArrayList;

public class RungeKuttaMethod extends Method {
    public RungeKuttaMethod() {
        super(MethodName.RUNGE_KUTTA);
    }

    public RungeKuttaMethod(ComputationalConditions conditions) {
        super(conditions);
        fullFillStorage();
    }

    @Override
    public void fullFillStorage() {
        storage.clear();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        double px = conditions.getX0(), py = conditions.getY0(), h = conditions.getH();
        coordinates.add(new Coordinate(px, py));
        // px - previous value of x, so no need to go through storage for finding it
        // so complexity is O(n), but not O(n^2), so does py

        for (int i = 1; i < conditions.getStep(); i++) {
            double x = px + h;
            double y = py + computationalFunction(px, py, h);
            coordinates.add(new Coordinate(x, y));
            px = x;
            py = y;
        }
        storage.setCoordinates(coordinates);
    }

    @Override
    double functionForLocalError(int i, double y_exact) {
        double x = storage.getCoordinates().get(i - 1).getX();
        double h = conditions.getH();
        return computationalFunction(x, y_exact, h);
    }

    private double computationalFunction(double x, double y, double h){
        double k1 = f(x, y);
        double k2 = f(x + h / 2, y + h * k1 / 2);
        double k3 = f(x + h / 2, y + h * k2 / 2);
        double k4 = f(x + h, y + h * k3);
        return h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
    }
}
