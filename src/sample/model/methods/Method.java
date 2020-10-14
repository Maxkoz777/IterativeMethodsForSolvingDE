package sample.model.methods;

import javafx.scene.chart.XYChart;
import sample.model.MethodName;
import sample.model.storage.ArrayStorage;
import sample.model.Coordinate;
import sample.model.storage.Storage;

public abstract class Method {
    String name;
    Storage storage = new ArrayStorage();
    double c;
    double x0;
    double y0;
    double _X;
    double h;
    double step;

    /**
     * initially puts given numbers to all variables
     * @param methodName is responsible for name of method
     */

    public Method(MethodName methodName) {
        this();
        this.name = methodName.name();
    }

    public Method(){
        c = 0;
        x0 = 1;
        y0 = 0;
        _X = 8;
        step = 7;
        h = Math.abs(_X - x0) / step;
    }

    public double f(double x, double y){
        return y / x - x * Math.exp(y/x);
    }

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

    /**
     * fills storage with proper coordinates according to chosen method
     */

    abstract void fullFillStorage();
}
