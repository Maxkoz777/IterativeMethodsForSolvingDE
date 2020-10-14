package sample.model.methods;

import sample.model.Coordinate;
import sample.model.MethodName;

import java.util.ArrayList;

public class ExactMethod extends Method{

    public ExactMethod() {
        super(MethodName.COMMON);
    }

    @Override
    void fullFillStorage() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(x0, y0));
        for (int i = 1; i < step; i++) {
            double x = x0 + h * i;
            coordinates.add(new Coordinate(x, -x * Math.log(x + c)));
        }
        storage.setCoordinates(coordinates);
    }
}
