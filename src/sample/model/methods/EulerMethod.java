package sample.model.methods;

import sample.model.util.ComputationalConditions;
import sample.model.util.Coordinate;
import sample.model.util.MethodName;

import java.util.ArrayList;

public class EulerMethod extends Method {
    public EulerMethod() {
        super(MethodName.EULER);
    }

    public EulerMethod(ComputationalConditions conditions) {
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
            double y = py + h * f(px, py);
            coordinates.add(new Coordinate(x, y));
            px = x;
            py = y;
        }
        storage.setCoordinates(coordinates);
    }
}
