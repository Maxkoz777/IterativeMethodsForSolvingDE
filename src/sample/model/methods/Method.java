package sample.model.methods;

import javafx.scene.chart.XYChart;
import sample.model.ComputationalConditions;
import sample.model.MethodName;
import sample.model.storage.ArrayStorage;
import sample.model.Coordinate;
import sample.model.storage.Storage;

public abstract class Method {
    String name;
    Storage storage = new ArrayStorage();
    ComputationalConditions conditions;

    /**
     * initially puts given numbers to all variables
     * @param methodName is responsible for name of method
     */

    public Method(MethodName methodName) {
        this();
        this.name = methodName.name();
    }

    public Method(){
        conditions = new ComputationalConditions();
    }

    public Method(ComputationalConditions conditions) {
        this.conditions = conditions;
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

    public XYChart.Series<String, Number> getBarChart(ExactMethod exactMethod){
        fullFillStorage();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (int i = 0; i < conditions.getStep(); i++) {
            double x = storage.getCoordinates().get(i).getX();
            double y = storage.getCoordinates().get(i).getY();
            double y0 = exactMethod.storage.getCoordinates().get(i).getY();
            series.getData().add(new XYChart.Data<>("" + x, Math.abs(y - y0)));
        }
        return series;
    }

    /**
     * fills storage with proper coordinates according to chosen method
     */

    abstract void fullFillStorage();
}
