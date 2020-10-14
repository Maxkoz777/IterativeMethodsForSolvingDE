package sample.model.storage;

import sample.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage {
    private List<Coordinate> coordinates = new ArrayList<>();

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void clear(){
        coordinates.clear();
    }
}
