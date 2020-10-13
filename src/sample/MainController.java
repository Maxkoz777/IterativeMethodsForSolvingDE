package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    AnchorPane root;

    public void loadSolutions(ActionEvent actionEvent) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("SolutionGraphs.fxml"));
            root.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
