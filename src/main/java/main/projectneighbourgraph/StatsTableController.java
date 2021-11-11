package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.graphdata.Node;

public class StatsTableController {

    @FXML
    private TableView<Node> tableView;
    @FXML private TableColumn<Node, String> pointNameColumn;
    @FXML private TableColumn<Node, Double> xCoordColumn;
    @FXML private TableColumn<Node, Double> yCoordColumn;
    @FXML private TableColumn<Node, String> linkedToColumn;
    int test;

    private Graph graphData;

    public StatsTableController(){
        tableView = new TableView<Node>() ;
        pointNameColumn = new TableColumn<Node, String>();
        xCoordColumn = new TableColumn<Node, Double>();
        yCoordColumn = new TableColumn<Node, Double>();
        linkedToColumn = new TableColumn<Node, String>();
        test=0;
    }

    public void testTabView(){
        pointNameColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("id"));
        xCoordColumn.setCellValueFactory(new PropertyValueFactory<Node, Double>("unitxPos"));
        yCoordColumn.setCellValueFactory(new PropertyValueFactory<Node, Double>("unityPos"));
        //linkedToColumn.setCellValueFactory(new PropertyValueFactory<Node, String>("id"));
        tableView.getItems().add(new Node(250,250,0.5,0.5,1));
    }

    public void setGraphData(Graph graphData) {
        this.graphData = graphData;
    }
}
