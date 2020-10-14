package sample.model.methods;

import sample.model.Coordinate;
import sample.model.MethodName;

import java.util.ArrayList;

public class ImprovedEulerMethod extends Method {
    public ImprovedEulerMethod() {
        super(MethodName.IMPROVED_EULER);
    }

    @Override
    public void fullFillStorage() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        double px = conditions.getX0(), py = conditions.getY0(), h = conditions.getH();
        coordinates.add(new Coordinate(px, py));
        // px - previous value of x, so no need to go through storage for finding it
        // so complexity is O(n), but not O(n^2), so does py
        for (int i = 1; i < conditions.getStep(); i++) {
            double x = px + h;
            double y = py + h * f(px + h/2, py + h / 2 * f(px, py));
            coordinates.add(new Coordinate(x, y));
            px = x;
            py = y;
        }
        storage.setCoordinates(coordinates);
    }
}
