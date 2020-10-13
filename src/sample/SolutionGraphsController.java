package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class SolutionGraphsController {
    @FXML
    static LineChart<Number, Number> graph;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public static void setGraph(LineChart<Number, Number> graph) {
        SolutionGraphsController.graph = graph;
    }

    @FXML
    private void initialize(){
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        graph = new LineChart<>(xAxis, yAxis);
        graph.setTitle("Solutions");
    }

    public LineChart<Number, Number> getGraph() {
        return graph;
    }
}
