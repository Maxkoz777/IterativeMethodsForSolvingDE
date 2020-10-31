package sample.model.methods;

import javafx.scene.chart.XYChart;
import sample.model.ComputationalConditions;
import sample.model.Coordinate;
import sample.model.MethodName;
import sample.model.storage.ArrayStorage;
import sample.model.storage.Storage;

import java.util.ArrayList;
import java.util.EmptyStackException;

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
        this.name = methodName.getName();
        fullFillStorage();
    }

    public Method(){
        conditions = new ComputationalConditions();
    }

    public Method(ComputationalConditions conditions) {
        this.conditions = conditions;
        if (EulerMethod.class.equals(this.getClass())) {
            name = MethodName.EULER.getName();
        } else if (ImprovedEulerMethod.class.equals(this.getClass())) {
            name = MethodName.IMPROVED_EULER.getName();
        } else if (RungeKuttaMethod.class.equals(this.getClass())) {
            name = MethodName.RUNGE_KUTTA.getName();
        } else {
            name = MethodName.COMMON.getName();
        }
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
     *
     * @param exactMethod is provided exact method
     * @return error bars comparing to exact method
     */

    public XYChart.Series<String, Number> getBarChart(ExactMethod exactMethod){
        fullFillStorage();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (int i = 0; i < conditions.getStep(); i++) {
            double x = storage.getCoordinates().get(i).getX();
            series.getData().add(new XYChart.Data<>("" + x, getError(exactMethod, i)));
        }
        return series;
    }

    /**
     *
     * @return series for total error chart
     */

    public XYChart.Series<Number, Number> getTotalErrorChart(){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (int i = conditions.getN0(); i < conditions.getN() + 1; i++) {
            ComputationalConditions newConditions = new ComputationalConditions(conditions.getX0(), conditions.getY0(), conditions.get_X(), conditions.getN0(), i);
            Method currentMethod = getMethodTypeByName(name, newConditions);
            ExactMethod currentExactMethod = new ExactMethod(newConditions);
            currentMethod.fullFillStorage();
            currentExactMethod.fullFillStorage();
            series.getData().add(new XYChart.Data<>(i, currentMethod.getMaxError(currentExactMethod)));
        }
        return series;
    }

    /**
     *
     * @param exactMethod is provided exact method
     * @param i is index in coordinates of methods
     * @return error between y of this method and y of exact method
     */

    private double getError(ExactMethod exactMethod, Integer i){
        double y = storage.getCoordinates().get(i).getY();
        double y0 = exactMethod.storage.getCoordinates().get(i).getY();
        return Math.abs(y - y0);
    }

    /**
     *
     * @param exactMethod is given exact method
     * @return max error according to all errors comparing to given exact method
     */

    private double getMaxError(ExactMethod exactMethod) {
        ArrayList<Double> errors = new ArrayList<>();
        for (int i = 0; i < conditions.getStep(); i++) {
            errors.add(getError(exactMethod, i));
        }
        return errors.stream().mapToDouble(x -> x).max().orElseThrow(EmptyStackException::new);
    }

    /**
     *
     * @param name - name of method
     * @param conditions - init conditions for new method
     * @return sufficient method according to its name
     */

    private Method getMethodTypeByName(String name, ComputationalConditions conditions) {
        if (name.equals(MethodName.EULER.getName())){
            return new EulerMethod(conditions);
        }
        else if (name.equals(MethodName.IMPROVED_EULER.getName())){
            return new ImprovedEulerMethod(conditions);
        }
        else if (name.equals(MethodName.RUNGE_KUTTA.getName())){
            return new RungeKuttaMethod(conditions);
        }
        else return new ExactMethod(conditions);
    }

    /**
     * fills storage with proper coordinates according to chosen method
     */

    abstract void fullFillStorage();
}
