package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;
import sample.model.methods.EulerMethod;
import sample.model.methods.ExactMethod;
import sample.model.methods.ImprovedEulerMethod;
import sample.model.methods.RungeKuttaMethod;

public class MainController {
    // Anchor pane for plotting charts
    @FXML
    AnchorPane root;

    EulerMethod eulerMethod;
    ImprovedEulerMethod improvedEulerMethod;
    ExactMethod exactMethod;
    RungeKuttaMethod rungeKuttaMethod;

    LineChart<Number, Number> solutionChart;
    LineChart<Number, Number> errorChart;

    private Main main;

    /**
     * shows solution graph with all lines
     */

    public void loadErrors() {
        solutionChart.setVisible(false);
        errorChart.setVisible(true);
        //plotGraphs(errorChart);
        root.getChildren().setAll(errorChart);
    }

    /**
     * creates link to main app
     *
     * @param main is a reference to main app
     */

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Plots graphs onto Line chart
     *
     * @param chart is a Line Chart where graphs will be plotted
     */
    public void plotGraphs(LineChart<Number, Number> chart) {
        updateValues();
    }

    public void updateValues(){
        eulerMethod = new EulerMethod();
        improvedEulerMethod = new ImprovedEulerMethod();
        exactMethod = new ExactMethod();
        rungeKuttaMethod = new RungeKuttaMethod();
    }

    /**
     * shows error graph
     */

    public void loadSolutions() {
        solutionChart.setVisible(true);
        errorChart.setVisible(false);
        plotGraphs(solutionChart);
        root.getChildren().setAll(solutionChart);
    }

    private void createCharts(){
        solutionChart.getData().addAll(eulerMethod.getLineChart(), improvedEulerMethod.getLineChart(), exactMethod.getLineChart(), rungeKuttaMethod.getLineChart());
    }

    public void initialize() {
        eulerMethod = new EulerMethod();
        improvedEulerMethod = new ImprovedEulerMethod();
        exactMethod = new ExactMethod();
        rungeKuttaMethod = new RungeKuttaMethod();
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        solutionChart = new LineChart<>(xAxis, yAxis);
        solutionChart.setPadding(new Insets(0, 0, 50, 0));

        solutionChart.setTitle("Solutions");
        final NumberAxis xAxis1 = new NumberAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis1 = new NumberAxis();
        yAxis.setLabel("y");
        errorChart = new LineChart<>(xAxis1, yAxis1);
        errorChart.setTitle("Errors");
        errorChart.setPadding(new Insets(0, 0, 50, 0));
        createCharts();
    }
}
