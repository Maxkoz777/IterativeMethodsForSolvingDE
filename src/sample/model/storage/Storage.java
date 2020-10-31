package sample.model.storage;

import sample.model.util.Coordinate;

import java.util.List;

public abstract class Storage {
    protected List<Coordinate> coordinates;

    public abstract List<Coordinate> getCoordinates();

    public abstract void setCoordinates(List<Coordinate> coordinates);

    public abstract void clear();
}
