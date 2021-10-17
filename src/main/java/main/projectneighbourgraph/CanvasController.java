package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public class CanvasController {


    private Canvas canvas;

    private ArrayList<Node> nodeArrayList;
    private int pointCounter;
    private int size;


    public CanvasController(Canvas newCanvas){
        this.canvas = newCanvas;
        nodeArrayList = new ArrayList<Node>();
        pointCounter = 0;
        size = 2;
    }



    public void createFrame(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double canvasHeight = canvas.getHeight();
        double canvasWidth = canvas.getWidth();
        //horizontal arrow
        gc.strokeLine(10,canvasHeight-10,canvasWidth-10, canvasHeight-10);
        gc.strokeLine(canvasWidth-10, canvasHeight-10,canvasWidth-15, canvasHeight-15);
        gc.strokeLine(canvasWidth-10, canvasHeight-10,canvasWidth-15, canvasHeight-5);
        //vertical arrow
        gc.strokeLine( 10, canvasHeight-10, 10, 10);
        gc.strokeLine(10, 10, 5,15);
        gc.strokeLine(10, 10, 15,15);
    }


    public void reDraw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        createFrame();
        for(Node node : nodeArrayList){

            double xToDraw = reMapVar(node.getUnitxPos(), 0,1,0,canvas.getWidth());
            double yToDraw = reMapVar(node.getUnityPos(), 0,1,0,canvas.getHeight());
            node.setxPos(xToDraw);
            node.setyPos(yToDraw);
            drawX(xToDraw, yToDraw, size, gc);
        }
    }

    public void addNewPoint(MouseEvent event){
        //System.out.println("you pressed !");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();
        double xSetPos = reMapVar(xClicked,0,canvas.getWidth(),0,1);
        @SuppressWarnings("SuspiciousNameCombination") double ySetPos = reMapVar(yClicked,0,canvas.getHeight(),0,1);
        drawX(xClicked, yClicked,size, gc);
        nodeArrayList.add(new Node(xClicked, yClicked,xSetPos,ySetPos, pointCounter++));
        System.out.println("New node array :\n"+nodeArrayList);
    }


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

    public void setCanvasWidth(double newWidth) {
        canvas.setWidth(newWidth);
    }

    public void setCanvasHeight(double newHeight) {
        canvas.setHeight(newHeight);
    }


    private double reMapVar(double x, double in_min, double in_max, double out_min, double out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
