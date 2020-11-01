package sample.model.methods;

import sample.model.util.ComputationalConditions;
import sample.model.util.Coordinate;
import sample.model.util.MethodName;

import java.util.ArrayList;

public class ImprovedEulerMethod extends Method {
    public ImprovedEulerMethod() {
        super(MethodName.IMPROVED_EULER);
    }

    public ImprovedEulerMethod(ComputationalConditions conditions) {
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
            double y = py + h / 2 * (f(px, py) + f(x, py + h * f(px, py)));
            coordinates.add(new Coordinate(x, y));
            px = x;
            py = y;
        }
        storage.setCoordinates(coordinates);
    }

    @Override
    double functionForLocalError(int i, double y_exact) {
        double x = storage.getCoordinates().get(i - 1).getX();
        double x_next = storage.getCoordinates().get(i).getX();
        double h = conditions.getH();
        return h / 2 * (f(x, y_exact) + f(x_next, y_exact + h * f(x, y_exact)));
    }
}
