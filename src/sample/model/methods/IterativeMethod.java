package sample.model.methods;

import javafx.scene.chart.XYChart;
import sample.model.MethodName;
import sample.model.storage.ArrayStorage;
import sample.model.Coordinate;
import sample.model.storage.Storage;

public abstract class IterativeMethod {
    private Storage storage = new ArrayStorage();
    private double c, x0, y0, _X, h;
    private String name;

    public IterativeMethod(MethodName methodName) {
        this.name = methodName.name();
        c = -2;
        x0 = 1;
        y0 = 0;
        _X = 8;
        h = 10;
    }

    abstract void fullFillStorage();

    XYChart.Series<Number, Number> getLineChart(){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (Coordinate pair : storage.getCoordinates()){
            series.getData().add(new XYChart.Data<>(pair.getX(), pair.getY()));
        }
        return series;
    }
}
