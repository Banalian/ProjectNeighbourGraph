package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.strategy.LinkStrategy;

import java.util.ArrayList;

//controlleur principale du logiciel

public class MainController {
    @FXML private Label welcomeText;

    @FXML private Button refreshButton;
    @FXML private CanvasController canvasController;


    @FXML private TableView<Node> tableView;
    @FXML private TableColumn<Node, String> pointNameColumn;
    @FXML private TableColumn<Node, Double> xCoordColumn;
    @FXML private TableColumn<Node, Double> yCoordColumn;
    @FXML private TableColumn<Node, String> linkedToColumn;

    public MainController(){

    }

    private LinkStrategy strategy;

    void setStrategy(LinkStrategy strategy) {
        this.strategy = strategy;
    }
    void executeStrategy(ArrayList nodeList, int arg){
        strategy.link(nodeList, arg);
    }

    public void testTabView(){
        pointNameColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("id"));
        xCoordColumn.setCellValueFactory(new PropertyValueFactory<Node, Double>("unitxPos"));
        yCoordColumn.setCellValueFactory(new PropertyValueFactory<Node, Double>("unityPos"));
        //linkedToColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("id"));
        tableView.getItems().add(new Node(250,250,0.5,0.5,1));
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