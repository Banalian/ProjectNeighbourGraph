package main.projectneighbourgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.graphdata.Node;
import java.lang.Math;
import java.util.Random;

import java.util.ArrayList;

/**
 * Controller for the whole canvas
 * currently handles the drawing of points, the data for the points and anything related to that canvas
 */
public class CanvasController {
    //TODO: change to remove the need for the reference to the main controller

    /**
     * the canvas to draw on
     */
    @FXML private Canvas canvas;


    private int pointCounter;

    /**
     * pixel size of the points to draw
     */
    private int size;
    /**
     * margin to give to the canvas (in all directions) (in pixels)
     */
    private int frameMargin;

    private int pointsPerClick;
    private double radiusBrush;
    private MainController MC;

    /**
     * reference to the graph and its data (nodes, edges and color)
     */
    private Graph graphData;

    public CanvasController(){

        pointCounter = 0;
        size = 1;
        frameMargin = 10;
    }

    public void setMainController(MainController MC){
        this.MC = MC;
    }

    public void setGraphData(Graph graphData) {
        this.graphData = graphData;
    }

    public void setPointsPerClick(int i){
        pointsPerClick = i;
    }

    public void setRadiusBrush(double d){
        radiusBrush = d;
    }

    /**
     * Creates the frame with the class margin and canvas size
     */
    private void createFrame(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        double canvasHeight = canvas.getHeight();
        double canvasWidth = canvas.getWidth();
        //horizontal arrow
        gc.strokeLine(frameMargin,canvasHeight-frameMargin,canvasWidth-frameMargin, canvasHeight-frameMargin);
        gc.strokeLine(canvasWidth-frameMargin, canvasHeight-frameMargin,canvasWidth-frameMargin-5, canvasHeight-frameMargin-5);
        gc.strokeLine(canvasWidth-frameMargin, canvasHeight-frameMargin,canvasWidth-frameMargin-5, canvasHeight-5);
        //vertical arrow
        gc.strokeLine( frameMargin, canvasHeight-frameMargin, frameMargin, frameMargin);
        gc.strokeLine(frameMargin, frameMargin, 5,frameMargin+5);
        gc.strokeLine(frameMargin, frameMargin, frameMargin+5,frameMargin+5);
    }

    /**
     *  Redraw the whole frame, while recalculating the new positions for the points
     *  used when resizing the windows
     */
    public void reDraw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        createFrame();

        ArrayList<Node> nodes = graphData.getNodeArrayList();
        ArrayList<Edge> edges = graphData.getEdgeArrayList();

        for(Edge edge : edges){
            drawEdge(edge, gc);
        }

        for(Node node : nodes){

            double xToDraw = reMapVar(node.getUnitxPos(), 0,1,frameMargin,canvas.getWidth()+frameMargin);
            double yToDraw = reMapVar(node.getUnityPos(), 0,1,canvas.getHeight()+frameMargin,frameMargin);
            node.setxPos(xToDraw);
            node.setyPos(yToDraw);
            drawX(xToDraw, yToDraw, size, gc, node.getNodeColor());
        }



    }

    public void changeMouseMode(int numberMode){
        if (numberMode == 1){
            canvas.setOnMouseClicked(this::addNewPoint);
            canvas.setOnMouseDragged(null);
        }
        if (numberMode == 2){
            MC.getRadiusBrush(null);
            MC.getPointsPerClick(null);

            canvas.setOnMouseClicked(this::addNewBrush);
            canvas.setOnMouseDragged(this::addNewBrushDragged);
        }
    }
    /**
     * Adds a new point a the given MouseEvent location
     * will check if the position is valid, before adding the data and drawing the point
     * the color of the node/point will be taken from graphData, whether it's null or not
     * @param event the MouseEvent to use
     */
    @FXML
    public void addNewPoint(MouseEvent event){
        //System.out.println("you pressed !");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();

        if(posIsInFrame(xClicked, yClicked, frameMargin)){
            double xSetPos = reMapVar(xClicked-frameMargin,0,canvas.getWidth(),0,1);
            double ySetPos = reMapVar(yClicked-frameMargin,canvas.getHeight(),0,0,1);
            Color color = graphData.getColorToUse();

            drawX(xClicked, yClicked,size, gc, color);
            Node newNode = new Node(xClicked, yClicked,xSetPos,ySetPos, pointCounter++, color);
            graphData.addNode(newNode);
            this.MC.autoRefresh();
            //System.out.println("New node array :\n"+nodeArrayList);




        }else{
            System.out.println("Position not in frame");
        }

    }

    /**
     * Adds new points in a circle around the mouse at the given MouseEvent location
     * will check if the position is valid, before adding the data and drawing the point
     * @param event the MouseEvent to use
     */
    @FXML
    public void addNewBrush(MouseEvent event){
        //System.out.println("you pressed !");
        Random r = new Random();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();

        int i;
        for(i = 0; i < this.pointsPerClick; i++){
            double a = r.nextDouble()*Math.PI*2;
            double b = r.nextDouble()*this.radiusBrush;
            double x = xClicked + b * Math.cos(a);
            double y = yClicked + b * Math.sin(a);

            if(posIsInFrame(x, y, frameMargin)){
                double xSetPos = reMapVar(x-frameMargin,0,canvas.getWidth(),0,1);
                double ySetPos = reMapVar(y-frameMargin,canvas.getHeight(),0,0,1);
                if(x <= xClicked+this.radiusBrush & x > xClicked-this.radiusBrush & y <= yClicked+this.radiusBrush & y > yClicked-this.radiusBrush) {
                    drawX(x, y, size, gc, graphData.getColorToUse());
                    Node newNode = new Node(x, y,xSetPos,ySetPos, pointCounter++, graphData.getColorToUse());
                    graphData.addNode(newNode);
                }

            }else{
                System.out.println("Position not in frame");
            }
        }
    }

    /**
     * Adds new points in a circle around the mouse at the given MouseEvent location
     * will check if the position is valid, before adding the data and drawing the point
     * @param event the MouseEvent to use
     */
    @FXML
    public void addNewBrushDragged(MouseEvent event){
        //System.out.println("you pressed !");
        Random r = new Random();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();

        int i;
        for(i = 0; i < this.pointsPerClick; i++){
            double a = r.nextDouble()*Math.PI*2;
            double b = r.nextDouble()*this.radiusBrush;
            double x = xClicked + b * Math.cos(a);
            double y = yClicked + b * Math.sin(a);

            if(posIsInFrame(x, y, frameMargin)){
                double xSetPos = reMapVar(x-frameMargin,0,canvas.getWidth(),0,1);
                double ySetPos = reMapVar(y-frameMargin,canvas.getHeight(),0,0,1);
                if(x <= xClicked+this.radiusBrush & x > xClicked-this.radiusBrush & y <= yClicked+this.radiusBrush & y > yClicked-this.radiusBrush) {
                    drawX(x, y, size, gc, graphData.getColorToUse());
                    Node newNode = new Node(x, y,xSetPos,ySetPos, pointCounter++, graphData.getColorToUse());
                    graphData.addNode(newNode);
                }

            }else{
                System.out.println("Position not in frame");
            }
        }


    }

    /**
     * Checks if a given position is within a certain frame with a margin
     * @param xPos x Position to check
     * @param yPos y Position to check
     * @param marginSize the margin to use
     * @return true if the position is valid, false if it isn't
     */
    private boolean posIsInFrame(double xPos, double yPos, double marginSize){

        return xPos >= marginSize &&
                xPos <= canvas.getWidth() - marginSize &&
                yPos >= marginSize &&
                yPos <= canvas.getHeight() - marginSize;
    }

    /**
     * Draw a circle with a X in it at the given position
     * If no color is set in graphData, it will use a black color
     * @param centerX the x position of the center of the point
     * @param centerY the y position of the center of the point
     * @param size size of the point to draw
     * @param gc GraphicsContext to draw to
     * @param color the color to use to draw
     */
    private void drawX(double centerX, double centerY, double size, GraphicsContext gc, Color color){

        if(color == null) color = Color.BLACK;

        gc.setStroke(color);

        gc.strokeLine(
                centerX-size,
                centerY-size,
                centerX+size,
                centerY+size);
        gc.strokeLine(
                centerX-size,
                centerY+size,
                centerX+size,
                centerY-size);

        gc.strokeOval(
                centerX-size-size*0.5,
                centerY-size-size*0.5,
                size*3,
                size*3);
    }

    /**
     * Draw the edge between two points, using an Edge.
     * @param edge the edge to draw
     * @param gc the GraphicsContext to draw on
     */
    public void drawEdge(Edge edge, GraphicsContext gc){
        drawLineBetweenTwoPoint(edge.getNode1(), edge.getNode2(), gc, false);
    }

    /**
     * Draw a line between the two points, using their canvas position.
     * if makeGradient is true, it will use a gradient between the two points
     * else it will make a red line if the two nodes have different colors, or a black line if they have the same color
     * @param point1 the first node
     * @param point2 the second node
     * @param gc the GraphicsContext to draw on
     * @param makeGradient true if you want to use a gradient, false otherwise
     */
    public void drawLineBetweenTwoPoint(Node point1, Node point2, GraphicsContext gc, boolean makeGradient){

        Color point1Color;
        Color point2Color;
        Color colorToUse;

        if(makeGradient){
            // we want to make a gradient line using the color of the nodes

            // to make sure that the gradient is in the correct direction
            if(point1.getUnitxPos() <= point2.getUnitxPos()){
                point1Color = point1.getNodeColor();
                point2Color = point2.getNodeColor();
            }else{
                point1Color = point2.getNodeColor();
                point2Color = point1.getNodeColor();
            }

            if(point1Color == null) point1Color = Color.BLACK;
            if(point2Color == null) point2Color = Color.BLACK;

            Stop[] stops = new Stop[] { new Stop(0, point1Color), new Stop(1, point2Color)};
            LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

            gc.setStroke(lg1);
        }else{
            //we don't want to use a gradient, so we just do a red line if the 2 nodes are different colors, or a black line if they are the same color
            if(point1.getNodeColor() != point2.getNodeColor()){
                colorToUse = Color.RED;
            }else{
                colorToUse = Color.BLACK;
            }

            gc.setStroke(colorToUse);
        }


        gc.strokeLine(
                point1.getxPos(),
                point1.getyPos(),
                point2.getxPos(),
                point2.getyPos()
        );
    }

    /**
     * Changes the canvas width
     * @param newWidth new width to set
     */
    public void setCanvasWidth(double newWidth) {
        canvas.setWidth(newWidth);
    }

    /**
     * Changes the canvas height
     * @param newHeight new height to set
     */
    public void setCanvasHeight(double newHeight) {
        canvas.setHeight(newHeight);
    }

    /**
     * Re-maps a number from one range to another.
     * That is, a value of in_min would get mapped to out_min, a value of in_max to out_max, values in-between to values in-between, etc.
     * @param x the parameter that'll be remapped
     * @param in_min min range of current x
     * @param in_max max range of current x
     * @param out_min min range of the new x
     * @param out_max max range of the new x
     * @return the new x within the new range
     */
    private double reMapVar(double x, double in_min, double in_max, double out_min, double out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}