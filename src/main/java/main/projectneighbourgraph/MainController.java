package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.strategy.*;

import java.util.ArrayList;


/**
 * Main controller of the software
 */
public class MainController {
    @FXML private Label welcomeText;

    @FXML private Button refreshButton;
    @FXML private Button ButtonClick;
    @FXML private Button ButtonBrush;
    @FXML private TextField radiusBrush;
    @FXML private TextField pointsPerClick;

    @FXML private CanvasController canvasController;
    @FXML private StatsTableController statsTableController;

    /**
     * reference to the graph and its data (nodes and edges)
     */
    private Graph graphData;

    public MainController(){}

    /**
     * Strategy used to create the graph's edges
     */
    private LinkStrategy strategy;

    /**
     * set the strategy for the link creation
     * @param strategy the new strategy to use
     */
    void setStrategy(LinkStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Executes the current strategy on the graph's node list.
     * @param nodeArrayList the arraylist of nodes
     * @param arg the argument used by the strategy (like for exemple the number of neighbours for kNN)
     */
    void executeStrategy(ArrayList<Node> nodeArrayList, int arg){
        ArrayList<Edge> result = strategy.link(nodeArrayList, arg);
        graphData.setEdgeArrayList(result);
        canvasController.reDraw();
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

    /**
     * refresh the links of the node of the graph, using the current strategy
     * @param actionEvent the event that triggered this method
     */
    public void refresh(ActionEvent actionEvent) {
        statsTableController.reDraw();
        if(strategy!= null){
            executeStrategy(graphData.getNodeArrayList(), 2);
        }
    }

    public void euclidianDistance(ActionEvent actionEvent) {
    }

    public void sineDistance(ActionEvent actionEvent) {
    }

    /**
     * Action to set the new strategy to the kNN strategy
     * @param actionEvent the event that triggered this method
     */
    public void kNNLink(ActionEvent actionEvent) {
        setStrategy(new kNNLinkStrategy());
    }

    public void egraphLink(ActionEvent actionEvent) {
        setStrategy(new egraphLinkStrategy());
    }

    public void ggLink(ActionEvent actionEvent) {
        setStrategy(new ggLinkStrategy());
    }

    public void relativeLink(ActionEvent actionEvent) {
        setStrategy(new relativeLinkStrategy());
    }

    /**
     * Removes all nodes and edges from the graph. Clears the canvas and the stats table.
     * @param actionEvent the event that triggered this method
     */
    public void clearAll(ActionEvent actionEvent) {
        graphData.clearAll();
        statsTableController.reDraw();
        canvasController.reDraw();
    }
}