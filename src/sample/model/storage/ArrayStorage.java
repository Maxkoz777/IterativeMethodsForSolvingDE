package sample.model.storage;

import sample.model.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ArrayStorage extends Storage {

    {
        coordinates = new ArrayList<>();
    }

    @Override
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void clear() {
        coordinates.clear();
    }
}
