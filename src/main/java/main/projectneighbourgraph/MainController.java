package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.strategy.LinkStrategy;

import java.util.ArrayList;

//Main controller of the software

public class MainController {
    @FXML private Label welcomeText;

    @FXML private Button refreshButton;
    @FXML private Button ButtonClick;
    @FXML private Button ButtonBrush;
    @FXML private TextField radiusBrush;
    @FXML private TextField pointsPerClick;
    @FXML private CanvasController canvasController;
    @FXML private StatsTableController statsTableController;

    private Graph graphData;

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

    public void onActionButtonClick(ActionEvent actionevent){
        canvasController.changeMouseMode(1);
    }

    public void onActionButtonBrush(ActionEvent actionevent){
        canvasController.changeMouseMode(2);
    }

    public void getPointsPerClick(ActionEvent actionevent){
        canvasController.setPointsPerClick(Integer.parseInt(pointsPerClick.getCharacters().toString()));
    }

    public void getRadiusBrush(ActionEvent actionevent){
        canvasController.setRadiusBrush(Double.parseDouble(radiusBrush.getCharacters().toString()));
    }

    public void setGraphData(Graph graphData) {
        this.graphData = graphData;
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