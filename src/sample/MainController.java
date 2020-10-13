package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import sample.model.methods.EulerMethod;

import java.io.IOException;

public class MainController {
    // Anchor pane for plotting charts
    @FXML
    AnchorPane root;

    private Main main;

    /**
     * shows solution graph with all lines
     *
     * @param actionEvent
     */

    public void loadSolutions(ActionEvent actionEvent) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("SolutionGraphs.fxml"));
            plotGraphs(main.solutionController.getGraph());
            root.getChildren().setAll(anchorPane);
            boolean flag = main.solutionController.getGraph().isVisible();
            main.primaryStage.setScene(main.mainScene);
            //main.primaryStage.setScene(new Scene(main.solutionController.getGraph()));
            main.primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates link to main app
     *
     * @param main
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
     *
     * @param actionEvent
     */

    public void loadErrors(ActionEvent actionEvent) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("LocalErrors.fxml"));
            root.getChildren().setAll(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
