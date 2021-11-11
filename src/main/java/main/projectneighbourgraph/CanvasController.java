package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Graph;
import main.projectneighbourgraph.graphdata.Node;
import java.lang.Math;
import java.util.Random;

import java.util.ArrayList;
import java.util.RandomAccess;

/**
 * Controller for the whole canvas
 * currently handles the drawing of points, the data for the points and anything related to that canvas
 */
public class CanvasController {


    @FXML private Canvas canvas;

    //private ArrayList<Node> nodeArrayList;
    //private ArrayList<Edge> edgeArrayList;

    private int pointCounter;
    private int size;
    private int frameMargin;
    private int pointsPerClick;
    private double radiusBrush;


    private Graph graphData;

    public CanvasController(){
        //nodeArrayList = new ArrayList<Node>();
        //edgeArrayList = new ArrayList<Edge>();

        pointCounter = 0;
        size = 2;
        frameMargin = 10;
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

        for(Node node : nodes){

            double xToDraw = reMapVar(node.getUnitxPos(), 0,1,frameMargin,canvas.getWidth()+frameMargin);
            double yToDraw = reMapVar(node.getUnityPos(), 0,1,canvas.getHeight()+frameMargin,frameMargin);
            node.setxPos(xToDraw);
            node.setyPos(yToDraw);
            drawX(xToDraw, yToDraw, size, gc);
        }

        for(Edge edge : edges){
            drawEdge(edge, gc);
        }

        /*
        for(Node node : nodeArrayList){

            double xToDraw = reMapVar(node.getUnitxPos(), 0,1,frameMargin,canvas.getWidth()+frameMargin);
            double yToDraw = reMapVar(node.getUnityPos(), 0,1,canvas.getHeight()+frameMargin,frameMargin);
            node.setxPos(xToDraw);
            node.setyPos(yToDraw);
            drawX(xToDraw, yToDraw, size, gc);
        }

        for(Edge edge : edgeArrayList){
            drawEdge(edge, gc);
        }
        */
    }

    public void changeMouseMode(int numberMode){
        if (numberMode == 1){
            canvas.setOnMouseClicked(this::addNewPoint);
            canvas.setOnMouseDragged(null);
        }
        if (numberMode == 2){
            canvas.setOnMouseClicked(this::addNewBrush);
            canvas.setOnMouseDragged(this::addNewBrushDragged);
        }
    }
    /**
     * Adds a new point a the given MouseEvent location
     * will check if the position is valid, before adding the data and drawing the point
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
            drawX(xClicked, yClicked,size, gc);
            //nodeArrayList.add(new Node(xClicked, yClicked,xSetPos,ySetPos, pointCounter++));
            Node newNode = new Node(xClicked, yClicked,xSetPos,ySetPos, pointCounter++);
            graphData.addNode(newNode);
            //System.out.println("New node array :\n"+nodeArrayList);

            //to comment/remove when not needed anymore
            //TODO : rewrite if necessary with the new graph system
            /*
            if(pointCounter%2 == 0){
                System.out.println("test draw of Edge");
                int nbNode = nodeArrayList.size();
                Edge newEdge = new Edge(nodeArrayList.get(nbNode-2), nodeArrayList.get(nbNode-1));
                edgeArrayList.add(newEdge);
                drawEdge(newEdge, gc);
            }
            */



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

        if(posIsInFrame(xClicked, yClicked, frameMargin)){
            double xSetPos = reMapVar(xClicked-frameMargin,0,canvas.getWidth(),0,1);
            double ySetPos = reMapVar(yClicked-frameMargin,canvas.getHeight(),0,0,1);
            int i = 0;
            for(i = 0; i < this.pointsPerClick; i++){
                double a = r.nextDouble()*Math.PI*2;
                double b = r.nextDouble()*this.radiusBrush;
                double x = xClicked + b * Math.cos(a);
                double y = yClicked + b * Math.sin(a);
                if(x <= xClicked+this.radiusBrush & x > xClicked-this.radiusBrush & y <= yClicked+this.radiusBrush & y > yClicked-this.radiusBrush) {
                    drawX(x, y, size, gc);
                    Node newNode = new Node(x, y,xSetPos,ySetPos, pointCounter++);
                    graphData.addNode(newNode);
                }
            }

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
    public void addNewBrushDragged(MouseEvent event){
        //System.out.println("you pressed !");
        Random r = new Random();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();

        if(posIsInFrame(xClicked, yClicked, frameMargin)){
            double xSetPos = reMapVar(xClicked-frameMargin,0,canvas.getWidth(),0,1);
            double ySetPos = reMapVar(yClicked-frameMargin,canvas.getHeight(),0,0,1);
            int i = 0;
            for(i = 0; i < this.pointsPerClick; i++){
                double a = r.nextDouble()*Math.PI*2;
                double b = r.nextDouble()*this.radiusBrush;
                double x = xClicked + b * Math.cos(a);
                double y = yClicked + b * Math.sin(a);
                if(x <= xClicked+this.radiusBrush & x > xClicked-this.radiusBrush & y <= yClicked+this.radiusBrush & y > yClicked-this.radiusBrush) {
                    drawX(x, y, size, gc);
                    Node newNode = new Node(x, y,xSetPos,ySetPos, pointCounter++);
                    graphData.addNode(newNode);
                }
            }

        }else{
            System.out.println("Position not in frame");
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
     * @param centerX the x position of the center of the point
     * @param centerY the y position of the center of the point
     * @param size size of the point to draw
     * @param gc GraphicsContext to draw to
     */
    private void drawX(double centerX, double centerY, double size, GraphicsContext gc){
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
     * Draw the edge between two points, using an Edge. Will be expanded to work with classes later on
     * @param edge the edge to draw
     * @param gc the GraphicsContext to draw on
     */
    public void drawEdge(Edge edge, GraphicsContext gc){
        drawLineBetweenTwoPoint(edge.getNode1(), edge.getNode2(), gc);
    }

    /**
     * Draw a line between the two points, using their canvas position.
     * Will store the line in an array
     *
     * @param point1 the first node
     * @param point2 the second node
     * @param gc the GraphicsContext to draw on
     */
    public void drawLineBetweenTwoPoint(Node point1, Node point2, GraphicsContext gc){
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
     * That is, a value of fromLow would get mapped to toLow, a value of fromHigh to toHigh, values in-between to values in-between, etc.
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
