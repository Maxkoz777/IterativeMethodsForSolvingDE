package sample;

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

    @FXML
    public Button OKButton;
    @FXML
    public Label TextArea;

    public void closeWindow() {
        dialogStage.close();
    }

    public void notFilledForm() {
        TextArea.setText(text.NOT_FULLY_FILLED_FORM.getMessage());
        dialogStage.show();
    }

    enum text {
        NOT_FULLY_FILLED_FORM("You should fill each row\n" +
                "to apply changes to charts"),
        INCORRECT_NUMBER_FORMAT("x0, y0, X should be real numbers\n" +
                "n0 and N are integer numbers"),
        INCORRECT_DOMAIN_OF_FUNCTION("x0 should be changed\n" +
                "Domain of the function: (0; +∞)");

        private final String message;

        public String getMessage() {
            return message;
        }

        text(String message) {
            this.message = message;
        }
    }

    public void setMain() {
    }

    public void wrongDomain(){
        TextArea.setText(text.INCORRECT_DOMAIN_OF_FUNCTION.getMessage());
        dialogStage.show();
    }

    public void incorrectNumbers() {
        TextArea.setText(text.INCORRECT_NUMBER_FORMAT.getMessage());
        dialogStage.show();
    }

    @FXML
    private void initialize() {
        dialogStage = new Stage();
        dialogStage.setScene(new Scene(dialogScene));
    }
}
