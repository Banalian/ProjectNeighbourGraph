package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

/**
 * Controller for the whole canvas
 * currently handles the drawing of points, the data for the points and anything related to that canvas
 */
public class CanvasController {


    @FXML private Canvas canvas;

    private ArrayList<Node> nodeArrayList;
    private int pointCounter;
    private int size;
    private int frameMargin;


    public CanvasController(){
        nodeArrayList = new ArrayList<Node>();
        pointCounter = 0;
        size = 2;
        frameMargin = 10;
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
        for(Node node : nodeArrayList){

            double xToDraw = reMapVar(node.getUnitxPos(), 0,1,frameMargin,canvas.getWidth()+frameMargin);
            double yToDraw = reMapVar(node.getUnityPos(), 0,1,canvas.getHeight()+frameMargin,frameMargin);
            node.setxPos(xToDraw);
            node.setyPos(yToDraw);
            drawX(xToDraw, yToDraw, size, gc);
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
            nodeArrayList.add(new Node(xClicked, yClicked,xSetPos,ySetPos, pointCounter++));
            System.out.println("New node array :\n"+nodeArrayList);
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
