package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.linkStrategy.*;
import main.projectneighbourgraph.distanceStrategy.*;

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
    @FXML private ColorPicker colorPick;

    @FXML private CanvasController canvasController;
    @FXML private StatsTableController statsTableController;

    /**
     * reference to the graph and its data (nodes and edges)
     */
    private Graph graphData;

    /**
     * Strategy used to create the graph's edges
     */
    private LinkStrategy linkStrategy;

    /**
     * Strategy used to create the graph's edges
     */
    private DistanceStrategy distanceStrategy;


    public MainController(){}


    /**
     * set the strategy for the link creation
     * @param linkStrategy the new strategy to use
     */
    void setStrategy(LinkStrategy linkStrategy) {
        this.linkStrategy = linkStrategy;
    }

    /**
     * Executes the current strategy on the graph's node list.
     * @param nodeArrayList the arraylist of nodes
     * @param arg the argument used by the strategy (like for exemple the number of neighbours for kNN)
     */
    void executeLinkStrategy(ArrayList<Node> nodeArrayList, int[] arg){
        if(distanceStrategy == null){
            System.out.println("No distance strategy selected, please select one");
        }else{
            ArrayList<Edge> result = linkStrategy.link(nodeArrayList, arg, distanceStrategy);
            graphData.setEdgeArrayList(result);
            canvasController.reDraw();
        }
    }


    /**
     * set the strategy for the link creation
     * @param distanceStrategy the new strategy to use
     */
    void setStrategy(DistanceStrategy distanceStrategy) {
        this.distanceStrategy = distanceStrategy;
    }

    /**
     * Executes the current strategy on the graph's node list.
     * @param nodeArrayList the arraylist of nodes
     * @param arg the argument used by the strategy (like for exemple the number of neighbours for kNN)
     */
    void executeDistanceStrategy(ArrayList<Node> nodeArrayList, int arg){
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
        int[] arg = {2}; //TODO : change this with a correct argument system
        if(linkStrategy!= null && distanceStrategy != null) {
            executeLinkStrategy(graphData.getNodeArrayList(), arg);
        }

        if(linkStrategy == null) {
            System.out.println("linkStrategy is missing, can't refresh");
        }
        if(distanceStrategy == null) {
            System.out.println("distanceStrategy is missing, can't refresh");
        }
    }

    public void euclideanDistance(ActionEvent actionEvent) {
        setStrategy(new EuclideanStrategy());
    }

    public void cosineDistance(ActionEvent actionEvent) {
        setStrategy(new CosineStrategy());
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

    public void chooseColor(ActionEvent actionEvent) {
        graphData.setColorToUse(colorPick.getValue());
    }
}