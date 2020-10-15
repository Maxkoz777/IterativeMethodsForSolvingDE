package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExceptionWindow {
    @FXML
    public AnchorPane dialogScene;
    private Stage dialogStage;

    private Main main;

    @FXML
    public Button OKButton;
    @FXML
    public Label TextArea;

    public void closeWindow() {
        dialogStage.close();
    }

    enum text{
        NOT_FULLY_FILLED_FORM("You should fill each row\n" +
                "to apply changes to charts"),
        INCORRECT_NUMBER_FORMAT("x0, y0, X should be real numbers\n" +
                "n0 and N are integer numbers");

        private final String message;

        public String getMessage() {
            return message;
        }

        text(String message) {
            this.message = message;
        }
    }

    public Stage getStage() {
        return dialogStage;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void incorrectNumbers(){
        TextArea.setText(text.INCORRECT_NUMBER_FORMAT.getMessage());
        dialogStage.show();
    }

    @FXML
    private void initialize(){
        dialogStage = new Stage();
        dialogStage.setScene(new Scene(dialogScene));
    }
}
