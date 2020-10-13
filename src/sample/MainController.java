package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;
import sample.model.methods.EulerMethod;

public class MainController {
    // Anchor pane for plotting charts
    @FXML
    AnchorPane root;

    LineChart<Number, Number> solutionChart;

    private Main main;

    /**
     * shows solution graph with all lines
     */

    public void loadErrors() {

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
        EulerMethod eulerMethod = new EulerMethod();
        chart.getData().add(eulerMethod.getLineChart());
    }

    /**
     * shows error graph
     */

    public void loadSolutions() {
        solutionChart.setVisible(true);
        plotGraphs(solutionChart);
        root.getChildren().setAll(solutionChart);
    }

    public void initialize() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        solutionChart = new LineChart<>(xAxis, yAxis);
        solutionChart.setPadding(new Insets(15));
        solutionChart.setTitle("Solutions");
    }
}
