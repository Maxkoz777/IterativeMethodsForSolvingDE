package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon/IconForApp.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
