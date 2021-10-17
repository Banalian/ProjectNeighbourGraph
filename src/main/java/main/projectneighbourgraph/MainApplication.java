package main.projectneighbourgraph;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("IHM_graphes_2D_v2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        MainController mainController = fxmlLoader.getController();
        CanvasController canvasController = mainController.startCanvasController();
        canvasController.createFrame();

        stage.widthProperty().addListener((ov, oldValue, newValue) -> {
            canvasController.setCanvasWidth(newValue.doubleValue()*0.70);
            canvasController.reDraw();
        });

        stage.heightProperty().addListener((ov, oldValue, newValue) -> {
            canvasController.setCanvasHeight(newValue.doubleValue()*0.70);
            canvasController.reDraw();
        });


        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}