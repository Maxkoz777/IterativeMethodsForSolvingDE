package sample.model.methods;

import javafx.scene.chart.XYChart;
import sample.model.MethodName;
import sample.model.storage.ArrayStorage;
import sample.model.Coordinate;
import sample.model.storage.Storage;

public abstract class IterativeMethod {
    Storage storage = new ArrayStorage();
    double c, x0, y0, _X, h, step;
    String name;

    /**
     * initially puts given numbers to all variables
     * @param methodName is responsible for name of method
     */

    public IterativeMethod(MethodName methodName) {
        this.name = methodName.name();
        c = -2;
        x0 = 1;
        y0 = 0;
        _X = 8;
        step = 7;
        h = Math.abs(_X - x0) / step;
    }

    /**
     * fills storage with proper coordinates according to chosen method
     */

    abstract void fullFillStorage();

    /**
     *
     * @return series that could be added to graph
     */

    public XYChart.Series<Number, Number> getLineChart(){
        fullFillStorage();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (Coordinate pair : storage.getCoordinates()){
            series.getData().add(new XYChart.Data<>(pair.getX(), pair.getY()));
        }
        return series;
    }
}
