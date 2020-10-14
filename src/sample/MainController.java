package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.model.ComputationalConditions;
import sample.model.methods.EulerMethod;
import sample.model.methods.ExactMethod;
import sample.model.methods.ImprovedEulerMethod;
import sample.model.methods.RungeKuttaMethod;

public class MainController {

    // table with ability of changing computational conditions
    @FXML
    public GridPane table;
    @FXML
    public TextField x0_changer;
    @FXML
    public TextField y0_changer;
    @FXML
    public TextField X_changer;
    @FXML
    public TextField step_changer;
    // Anchor pane for plotting charts
    @FXML
    AnchorPane root;

    EulerMethod eulerMethod;
    ImprovedEulerMethod improvedEulerMethod;
    ExactMethod exactMethod;
    RungeKuttaMethod rungeKuttaMethod;

    LineChart<Number, Number> solutionChart;
    BarChart<String, Number> errorChart;

    private Main main;

    /**
     * shows solution graph with all lines
     */

    public void loadErrors() {
        solutionChart.setVisible(false);
        errorChart.setVisible(true);
    }

    /**
     * creates link to main app
     *
     * @param main is a reference to main app
     */

    public void setMain(Main main) {
        this.main = main;
    }

    public void updateValues(){
        String x0, y0, _X, step;
        x0 = x0_changer.getText();
        y0 = y0_changer.getText();
        _X = X_changer.getText();
        step = step_changer.getText();
        if (!x0.equals("") && !y0.equals("") && !_X.equals("") && !step.equals("")){
            ComputationalConditions computationalConditions = new ComputationalConditions(Double.parseDouble(x0), Double.parseDouble(y0), Double.parseDouble(_X), Double.parseDouble(step));
            eulerMethod = new EulerMethod(computationalConditions);
            improvedEulerMethod = new ImprovedEulerMethod(computationalConditions);
            exactMethod = new ExactMethod(computationalConditions);
            rungeKuttaMethod = new RungeKuttaMethod(computationalConditions);
            solutionChart.getData().clear();
            errorChart.getData().clear();
            setDataOnCharts();
        }
    }

    public void initializeGraphs(){
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        solutionChart = new LineChart<>(xAxis, yAxis);
        solutionChart.setPadding(new Insets(0, 0, 50, 0));
        solutionChart.setTitle("Solutions");

        final CategoryAxis xAxis1 = new CategoryAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis1 = new NumberAxis();
        yAxis.setLabel("errors");
        errorChart = new BarChart<>(xAxis1, yAxis1);
        errorChart.setTitle("Errors");
        errorChart.setPadding(new Insets(0, 0, 50, 0));
    }

    public void setDataOnCharts(){
        solutionChart.getData().addAll(eulerMethod.getLineChart(), improvedEulerMethod.getLineChart(), exactMethod.getLineChart(), rungeKuttaMethod.getLineChart());
        errorChart.getData().addAll(eulerMethod.getBarChart(exactMethod), improvedEulerMethod.getBarChart(exactMethod), rungeKuttaMethod.getBarChart(exactMethod));
        solutionChart.setVisible(false);
        errorChart.setVisible(false);
    }

    /**
     * shows error graph
     */

    public void loadSolutions() {
        solutionChart.setVisible(true);
        errorChart.setVisible(false);
    }

    private void createCharts(){
        setDataOnCharts();
        root.getChildren().setAll(solutionChart, errorChart);
    }

    public void initialize() {
        eulerMethod = new EulerMethod();
        improvedEulerMethod = new ImprovedEulerMethod();
        exactMethod = new ExactMethod();
        rungeKuttaMethod = new RungeKuttaMethod();

        initializeGraphs();

        createCharts();
    }

    public void setInitialValues() {

    }
}
