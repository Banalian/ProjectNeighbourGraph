package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;


public class StatsTableController {

    @FXML
    private TableView<Node> tableView;
    @FXML private TableColumn<Node, String> pointNameColumn;
    @FXML private TableColumn<Node, Double> xCoordColumn;
    @FXML private TableColumn<Node, Double> yCoordColumn;
    @FXML private TableColumn<Node, String> linkedToColumn;
    @FXML private TableColumn<Node, Color> PointColor;
    private MainController MC;
    int test;

    private Graph graphData;

    public StatsTableController(){
        tableView = new TableView<Node>() ;
        pointNameColumn = new TableColumn<>();
        xCoordColumn = new TableColumn<>();
        yCoordColumn = new TableColumn<>();
        linkedToColumn = new TableColumn<>();
        PointColor = new TableColumn<>();
        test=0;


    }


    public void SetMainController(MainController MC){
        this.MC = MC;
    }

    public void reDraw(){
        int pointname = 1;

        ArrayList<Node> nodes = graphData.getNodeArrayList();
        tableView.getItems().clear();
        for (Node node : nodes) {
            tableView.getItems().add(new Node(250, 250, node.getUnitxPos(), node.getUnityPos(),pointname++,node.getNodeColor()));
        }

    }

    public void testTabView(){
        pointNameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        xCoordColumn.setCellValueFactory(new PropertyValueFactory<>("unitxPos"));
        yCoordColumn.setCellValueFactory(new PropertyValueFactory<>("unityPos"));
        linkedToColumn.setCellValueFactory(new PropertyValueFactory<>("idN"));
        PointColor.setCellValueFactory((new PropertyValueFactory<>("nodeColor")));
        //tableView.getItems().add(new Node(250,250,0.5,0.5,0));
    }

    public void setGraphData(Graph graphData) {
        this.graphData = graphData;
    }
}