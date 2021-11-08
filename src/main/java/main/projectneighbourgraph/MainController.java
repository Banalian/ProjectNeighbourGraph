package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.projectneighbourgraph.strategy.LinkStrategy;

import java.util.ArrayList;

//Main controller of the software

public class MainController {
    @FXML private Label welcomeText;

    @FXML private Button refreshButton;
    @FXML private CanvasController canvasController;
    @FXML private StatsTableController statsTableController;

    public MainController(){}

    private LinkStrategy strategy;

    void setStrategy(LinkStrategy strategy) {
        this.strategy = strategy;
    }
    void executeStrategy(ArrayList nodeList, int arg){
        strategy.link(nodeList, arg);
    }

    public StatsTableController getStatsTableController() {
        return statsTableController;
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