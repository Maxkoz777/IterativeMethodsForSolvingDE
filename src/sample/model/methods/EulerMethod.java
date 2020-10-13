package sample.model.methods;

import com.sun.javafx.collections.MappingChange;
import sample.model.Coordinate;
import sample.model.MethodName;

import java.util.ArrayList;

public class EulerMethod extends IterativeMethod{
    public EulerMethod() {
        super(MethodName.EULER);
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
            double y = py + h * (py / px - px * Math.exp(py/px));
            coordinates.add(new Coordinate(x, y));
            px = x;
            py = y;
        }
        storage.setCoordinates(coordinates);
    }
}
