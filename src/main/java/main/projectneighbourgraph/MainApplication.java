package main.projectneighbourgraph;

import javafx.application.Application;
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

        CanvasController canvasController = mainController.getCanvasController();
        StatsTableController statsTableController = new StatsTableController();
               statsTableController = mainController.getStatsTableController();

        /// adds 2 listeners to do the resize action if necessary, since canvas don't have any resize properties by default.
        stage.widthProperty().addListener((ov, oldValue, newValue) -> {
            canvasController.setCanvasWidth(newValue.doubleValue()*0.70);
            canvasController.reDraw();
        });
        stage.heightProperty().addListener((ov, oldValue, newValue) -> {
            canvasController.setCanvasHeight(newValue.doubleValue()*0.70);
            canvasController.reDraw();
        });

        statsTableController.testTabView();

        stage.setTitle("Neighbor Graph");
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(400);
        stage.setMinWidth(350);
    }

    public static void main(String[] args) {
        launch();
    }
}