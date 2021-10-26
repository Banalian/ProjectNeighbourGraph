package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public class MainController {
    @FXML private Label welcomeText;

    @FXML private Button refreshButton;
    //@FXML private MenuButton distanceSelectButton;
    //@FXML private MenuButton algorithmSelectButton;
    @FXML private CanvasController canvasController;

    public MainController(){

    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    public void refresh(ActionEvent actionEvent) {
    }

    public void euclidianDistance(ActionEvent actionEvent) {
    }

    public void sineDistance(ActionEvent actionEvent) {
    }

    public void kNNLink(ActionEvent actionEvent) {
    }

    public void egraphLink(ActionEvent actionEvent) {
    }

    public void ggLink(ActionEvent actionEvent) {
    }

    public void relativeLink(ActionEvent actionEvent) {
    }
}