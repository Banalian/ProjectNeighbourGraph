package main.projectneighbourgraph;

import javafx.application.Platform;
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
    @FXML
    private Label welcomeText;

    @FXML
    private Button refreshButton;
    @FXML
    private Button ButtonClick;
    @FXML
    private Button ButtonBrush;
    @FXML
    private TextField radiusBrush;
    @FXML
    private TextField pointsPerClick;

    /**
     * ColorPicker to choose a color for the nodes
     */
    @FXML
    private ColorPicker colorPick;

    @FXML
    private CanvasController canvasController;
    @FXML
    private StatsTableController statsTableController;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem kNNSelection;
    @FXML
    private MenuItem egraphSelection;
    @FXML
    private MenuItem ggSelection;
    @FXML
    private MenuItem relativeSelection;

    @FXML
    private MenuItem euclideanSelection;
    @FXML
    private MenuItem cosineSelection;

    /**
     * Checkbox to enable or disable the automatic refresh of the graph
     */
    @FXML
    private CheckBox autoRefreshCB;

    /**
     * Boolean to check if the automatic refresh is enabled
     */
    boolean autoRefresh = false;


    @FXML
    private Label totalNodeNbLabel;

    @FXML
    private Label totalEdgeNbLabel;


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


    public MainController() {
    }


    /**
     * set the strategy for the link creation
     * @param linkStrategy the new strategy to use
     */
    void setStrategy(LinkStrategy linkStrategy) {
        this.linkStrategy = linkStrategy;
    }

    /**
     * Executes the current strategy on the graph's node list.
     *
     * @param nodeArrayList the arraylist of nodes
     * @param arg the arguments used by the strategy (like for example the number of neighbours for kNN)
     */
    void executeLinkStrategy(ArrayList<Node> nodeArrayList, int[] arg) {
        if (distanceStrategy == null) {
            System.out.println("No distance strategy selected, please select one");
        } else {
            try {
                ArrayList<Edge> result = linkStrategy.link(nodeArrayList, arg, distanceStrategy);
                graphData.setEdgeArrayList(result);
                canvasController.reDraw();
                statsTableController.reDraw();
            }catch (Exception e){
                //show an alert box with the error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error : " + e.getMessage());
                alert.showAndWait();
            }

        }
    }


    /**
     * set the strategy for the link creation
     * @param distanceStrategy the new strategy to use
     */
    void setStrategy(DistanceStrategy distanceStrategy) {
        this.distanceStrategy = distanceStrategy;
    }


    public StatsTableController getStatsTableController() {
        return statsTableController;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    public void onActionButtonClick(ActionEvent actionevent) {
        canvasController.changeMouseMode(1);
    }

    public void onActionButtonBrush(ActionEvent actionevent) {
        canvasController.changeMouseMode(2);
    }

    public void getPointsPerClick(ActionEvent actionevent) {
        canvasController.setPointsPerClick(Integer.parseInt(pointsPerClick.getCharacters().toString()));
    }

    public void getRadiusBrush(ActionEvent actionevent) {
        canvasController.setRadiusBrush(Double.parseDouble(radiusBrush.getCharacters().toString()));
    }

    public void setGraphData(Graph graphData) {
        this.graphData = graphData;
    }

    /**
     * Refresh the links of the node of the graph, using the current strategy
     * @param actionEvent the event that triggered this method
     */
    public void refresh(ActionEvent actionEvent) {
        int[] arg = {2}; //TODO : change this with a correct argument system
        if (linkStrategy != null && distanceStrategy != null) {
            executeLinkStrategy(graphData.getNodeArrayList(), arg);
        }

        if (linkStrategy == null) {
            System.out.println("linkStrategy is missing, can't refresh");
        }
        if (distanceStrategy == null) {
            System.out.println("distanceStrategy is missing, can't refresh");
        }
        statsTableController.reDraw();
        totalNodeNbLabel.setText("Number of nodes : " + graphData.getNodeArrayList().size());
        totalEdgeNbLabel.setText("Number of nodes : " + graphData.getEdgeArrayList().size());
    }

    /**
     * refresh the canvas if the auto refresh is checked
     */
    public void autoRefresh(){
        ArrayList<Edge> edges = graphData.getEdgeArrayList();
        if(autoRefresh){
            refresh(null);
        }else{
            statsTableController.reDraw();
            totalNodeNbLabel.setText("Number of nodes : " + graphData.getNodeArrayList().size());
            totalEdgeNbLabel.setText("Number of nodes : " + graphData.getEdgeArrayList().size());
        }

    }

    /**
     * Change the auto refresh mode
     * @param actionEvent the event that triggered this method
     */
    public void changeAutoRefresh(ActionEvent actionEvent) {
        autoRefresh = autoRefreshCB.isSelected();
    }

    /**
     * Action to set the new distance strategy to the euclidean strategy
     * @param actionEvent the event that triggered this method
     */
    public void euclideanDistance(ActionEvent actionEvent) {
        setStrategy(new EuclideanStrategy());
        setDistanceSelectionText("Euclidean");
    }

    /**
     * Action to set the new distance strategy to the cosine strategy
     * @param actionEvent the event that triggered this method
     */
    public void cosineDistance(ActionEvent actionEvent) {
        setStrategy(new CosineStrategy());
        setDistanceSelectionText("Cosine");
    }

    /**
     * Action to set the new link strategy to the kNN strategy
     * @param actionEvent the event that triggered this method
     */
    public void kNNLink(ActionEvent actionEvent) {
        setStrategy(new kNNLinkStrategy());
        setLinkSelectionText("kNN");
    }

    public void egraphLink(ActionEvent actionEvent) {
        setStrategy(new egraphLinkStrategy());
        setLinkSelectionText("e-graph");
    }

    /**
     * Action to set the new link strategy to the gg gabriel strategy
     * @param actionEvent the event that triggered this method
     */
    public void ggLink(ActionEvent actionEvent) {
        setStrategy(new ggLinkStrategy());
        setLinkSelectionText("GG Gabriel");
    }

    public void relativeLink(ActionEvent actionEvent) {
        setStrategy(new relativeLinkStrategy());
        setLinkSelectionText("Relative neighbors");
    }

    /**
     * Sets the labels of the menu items for the link strategy
     * @param linkSelectionText the menu item to set to selected
     */
    public void setLinkSelectionText(String linkSelectionText) {
        kNNSelection.setText("kNN");
        egraphSelection.setText("ε-graph");
        ggSelection.setText("GG Gabriel");
        relativeSelection.setText("Relative neighbors");

        switch (linkSelectionText) {
            case "kNN" -> kNNSelection.setText("kNN (selected)");
            case "e-graph" -> egraphSelection.setText("ε-graph (selected)");
            case "GG Gabriel" -> ggSelection.setText("GG Gabriel (selected)");
            case "Relative neighbors" -> relativeSelection.setText("Relative neighbors (selected)");
        }
    }

    /**
     * Sets the labels of the menu items for the distance strategy
     * @param distanceSelectionText the menu item to set to selected
     */
    public void setDistanceSelectionText(String distanceSelectionText) {
        euclideanSelection.setText("Euclidean");
        cosineSelection.setText("Cosine");
        switch (distanceSelectionText) {
            case "Euclidean" -> euclideanSelection.setText("Euclidean (selected)");
            case "Cosine" -> cosineSelection.setText("Cosine (selected)");
        }
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

    /**
     * Choose a new color to use for the next nodes
     * @param actionEvent the event that triggered this method
     */
    public void chooseColor(ActionEvent actionEvent) {
        graphData.setColorToUse(colorPick.getValue());
    }

    /**
     * Opens an alert box with credits and information about the program
     * @param actionEvent the event that triggered this method
     */
    public void aboutMenuAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About");
        alert.setContentText("This is a simple application that allows you to create a graph by adding nodes on a canvas " +
                "and then draw the graph using different strategies to link the nodes between them.\n" +
                "\nMade by: \n" +
                "  - Lilian Pouvreau\n" +
                "  - Corentin Esnault\n" +
                "  - Robin Lejeune\n" +
                "  - Mouna Kanouni\n" +
                "\nUnder the supervision of: \n" +
                "  - Gilles Venturini\n");
        alert.showAndWait();
    }

    /**
     * Open an alert box to explain the use of the different strategies/algorithms
     * @param actionEvent the event that triggered this method
     */
    public void explanationMenuAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Algorithm explanation");
        alert.setHeaderText("Algorithm explanation");
        alert.setContentText("The currently implemented strategies/algorithms are:\n" +
                "\nDistance Strategies :\n" +
                "  - Manhattan (L1-Norm) : the distance between two nodes is the sum of the absolute values of the differences between their attributes\n" +
                "  - Euclidean (L2-Norm): the distance between two nodes is the euclidean distance\n" +
                "  - Cosine: we use the cosine similarity of the nodes to differentiate their distance\n" +
                "\nLink Strategies :\n" +
                "  - kNN: we link each nodes to its k-nearest neighbors (for example its 2 closest for)\n" +
                "  - e-graph: EXPLANATION NEEDED\n" +
                "  - GG Gabriel: for each node, you check all the other node and create a circle between them, and if no nodes are within that circle, we link the nodes.\n" +
                "  - Relative neighbors: First, for each pair of nodes i and j, we calculate the distance between them d(i, j). Secondly for each other points k != i, j; we take the max distance Dk(i, j) between (i, k) or (j, k). And to finish, we link i and j if there is 0 Dk(i, j) smaller than d(i, j)\n");

        alert.showAndWait();
    }

    /**
     * Exits the application
     * @param actionEvent the event that triggered this method
     */
    public void exitProgram(ActionEvent actionEvent) {
        Platform.exit();
    }


}