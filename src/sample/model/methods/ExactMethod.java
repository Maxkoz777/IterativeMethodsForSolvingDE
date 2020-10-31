package sample.model.methods;

import sample.model.util.ComputationalConditions;
import sample.model.util.Coordinate;
import sample.model.util.MethodName;

import java.util.ArrayList;

public class ExactMethod extends Method{

    public ExactMethod() {
        super(MethodName.COMMON);
    }

    public ExactMethod(ComputationalConditions conditions) {
        super(conditions);
        fullFillStorage();
    }

    @Override
    void fullFillStorage() {
        storage.clear();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(conditions.getX0(), conditions.getY0()));
        for (int i = 1; i < conditions.getStep(); i++) {
            double x = conditions.getX0() + conditions.getH() * i;
            coordinates.add(new Coordinate(x, -x * Math.log(x + conditions.getC())));
        }
        storage.setCoordinates(coordinates);
    }
}
