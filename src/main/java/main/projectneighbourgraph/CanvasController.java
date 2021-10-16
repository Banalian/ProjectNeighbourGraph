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


    public CanvasController(Canvas newCanvas){
        this.canvas = newCanvas;
        nodeArrayList = new ArrayList<Node>();
        pointCounter = 0;
    }



    public void addNewPoint(MouseEvent event){
        //System.out.println("you pressed !");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();
        drawX(xClicked, yClicked,2, gc);
        nodeArrayList.add(new Node(xClicked, yClicked, pointCounter++));
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

}
