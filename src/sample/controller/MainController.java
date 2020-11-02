package sample.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.model.methods.*;
import sample.model.util.ComputationalConditions;

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
    public TextField n0_changer;
    @FXML
    public TextField N_changer;
    @FXML
    public RadioButton displayExact;
    @FXML
    public RadioButton displayEuler;
    @FXML
    public RadioButton displayImproved;
    @FXML
    public RadioButton displayRK;
    // Anchor pane for plotting charts
    @FXML
    AnchorPane root;

    private EulerMethod eulerMethod;
    private ImprovedEulerMethod improvedEulerMethod;
    private ExactMethod exactMethod;
    private RungeKuttaMethod rungeKuttaMethod;

    private LineChart<Number, Number> solutionChart;
    private BarChart<String, Number> errorChart;
    private LineChart<Number, Number> totalErrorChart;

    private Main main;

    /**
     * shows solution graph with all lines
     */

    public void loadErrors() {
        solutionChart.setVisible(false);
        errorChart.setVisible(true);
        totalErrorChart.setVisible(false);
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
     * after applying changes all variables for each method are reinstated
     */

    public void updateValues() throws NoSuchMethodException {
        String x0, y0, _X, n0, N;
        x0 = x0_changer.getText();
        y0 = y0_changer.getText();
        _X = X_changer.getText();
        n0 = n0_changer.getText();
        N = N_changer.getText();
        ComputationalConditions computationalConditions;
        if (x0.equals("") || y0.equals("") || _X.equals("") || N.equals("") || n0.equals("")) {
            showNotFilledFormWindow();
            return;
        }
        try {
            computationalConditions = new ComputationalConditions(Double.parseDouble(x0),
                    Double.parseDouble(y0), Double.parseDouble(_X), Integer.parseInt(n0), Integer.parseInt(N));
        } catch (NumberFormatException exception) {
            showIncorrectInputWindow();
            return;
        }
        if (!Method.Restrictions.inDomain(Double.parseDouble(x0))){
            showNotInDomainWindow();
            return;
        }
        eulerMethod = new EulerMethod(computationalConditions);
        improvedEulerMethod = new ImprovedEulerMethod(computationalConditions);
        exactMethod = new ExactMethod(computationalConditions);
        rungeKuttaMethod = new RungeKuttaMethod(computationalConditions);
        setDataOnCharts();
    }

    private void showNotInDomainWindow() {
        main.exceptionWindow.wrongDomain();
    }

    private void showNotFilledFormWindow() {
        main.exceptionWindow.notFilledForm();
    }

    private void showIncorrectInputWindow() {
        main.exceptionWindow.incorrectNumbers();
    }

    private void initializeGraphs() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        solutionChart = new LineChart<>(xAxis, yAxis);
        solutionChart.setPadding(new Insets(0, 0, 50, 0));
        solutionChart.setTitle("Solutions");

        final CategoryAxis xAxis1 = new CategoryAxis();
        xAxis1.setLabel("x");
        final NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("error");
        errorChart = new BarChart<>(xAxis1, yAxis1);
        errorChart.setTitle("LTE");
        errorChart.setPadding(new Insets(0, 0, 50, 0));

        final NumberAxis xAxis2 = new NumberAxis();
        xAxis2.setLabel("step number");
        final NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("error");
        totalErrorChart = new LineChart<>(xAxis2, yAxis2);
        totalErrorChart.setPadding(new Insets(0, 0, 50, 0));
        totalErrorChart.setTitle("Total Errors");
    }

    private void setDataOnCharts() throws NoSuchMethodException {
        solutionChart.getData().clear();
        errorChart.getData().clear();
        totalErrorChart.getData().clear();
        if (displayEuler.isSelected()) {
            solutionChart.getData().add(eulerMethod.getLineChart());
            errorChart.getData().add(eulerMethod.getBarChart(exactMethod));
            totalErrorChart.getData().add(eulerMethod.getTotalErrorChart());
        }
        if (displayImproved.isSelected()) {
            solutionChart.getData().add(improvedEulerMethod.getLineChart());
            errorChart.getData().add(improvedEulerMethod.getBarChart(exactMethod));
            totalErrorChart.getData().add(improvedEulerMethod.getTotalErrorChart());
        }
        if (displayRK.isSelected()) {
            solutionChart.getData().add(rungeKuttaMethod.getLineChart());
            errorChart.getData().add(rungeKuttaMethod.getBarChart(exactMethod));
            totalErrorChart.getData().add(rungeKuttaMethod.getTotalErrorChart());
        }
        if (displayExact.isSelected()) {
            solutionChart.getData().add(exactMethod.getLineChart());
        }
        solutionChart.setVisible(false);
        errorChart.setVisible(false);
        totalErrorChart.setVisible(false);
    }

    /**
     * shows error graph
     */

    public void loadSolutions() {
        solutionChart.setVisible(true);
        totalErrorChart.setVisible(false);
        errorChart.setVisible(false);
    }

    private void createCharts() throws NoSuchMethodException {
        setDataOnCharts();
        root.getChildren().setAll(solutionChart, errorChart, totalErrorChart);
    }

    public void initialize() throws NoSuchMethodException {
        eulerMethod = new EulerMethod();
        improvedEulerMethod = new ImprovedEulerMethod();
        exactMethod = new ExactMethod();
        rungeKuttaMethod = new RungeKuttaMethod();

        initializeGraphs();

        createCharts();
    }

    public void setInitialValues() throws NoSuchMethodException {
        eulerMethod = new EulerMethod();
        improvedEulerMethod = new ImprovedEulerMethod();
        exactMethod = new ExactMethod();
        rungeKuttaMethod = new RungeKuttaMethod();
        solutionChart.getData().clear();
        errorChart.getData().clear();
        totalErrorChart.getData().clear();
        setDataOnCharts();
    }

    public void loadTotalErrors() {
        solutionChart.setVisible(false);
        totalErrorChart.setVisible(true);
        errorChart.setVisible(false);
    }

    public void showProperMethods() throws NoSuchMethodException {
        setDataOnCharts();
    }
}
