package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private Canvas mainCanvas;

    CanvasController canvasController;


    public MainController(){

    }

    public void startCanvasController(){
        canvasController = new CanvasController(mainCanvas);
    }

    @FXML
    private void addPoint(MouseEvent event){
        canvasController.addNewPoint(event);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}

