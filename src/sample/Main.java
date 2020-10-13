package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.model.methods.EulerMethod;

import java.io.IOException;

public class Main extends Application {
    public Stage primaryStage;
    private Parent root;
    public MainController appController;
    public SolutionGraphsController solutionController;
    public Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainPage.fxml"));
        root = loader.load();
        appController = loader.getController();
        appController.setMain(this);


        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(Main.class.getResource("SolutionGraphs.fxml"));
        loader1.load();
        solutionController = loader1.getController();
        solutionController.setMain(this);
        mainScene = new Scene(root, 800, 400);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon/IconForApp.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
