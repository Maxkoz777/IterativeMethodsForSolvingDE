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
        coordinates.add(new Coordinate(conditions.getX0(), conditions.getY0()));
        for (int i = 1; i < conditions.getStep(); i++) {
            double x = conditions.getX0() + conditions.getH() * i;
            coordinates.add(new Coordinate(x, -x * Math.log(x + conditions.getC())));
        }
        storage.setCoordinates(coordinates);
    }
}
