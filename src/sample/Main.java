package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public Stage primaryStage;
    public MainController appController;
    public ExceptionWindow exceptionWindow;
    public Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainPage.fxml"));
        Parent root = loader.load();
        appController = loader.getController();
        appController.setMain(this);

        FXMLLoader windowLoader = new FXMLLoader();
        windowLoader.setLocation(Main.class.getResource("ExceptionWindow.fxml"));
        windowLoader.load();
        exceptionWindow = windowLoader.getController();

        mainScene = new Scene(root, 800, 400);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon/IconForApp.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
