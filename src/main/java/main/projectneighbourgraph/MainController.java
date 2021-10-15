package main.projectneighbourgraph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private Canvas mainCanvas;

    private ArrayList<Node> nodeArrayList;
    private int pointCounter;


    public MainController(){
        nodeArrayList = new ArrayList<Node>();
        pointCounter = 0;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void addPoint(MouseEvent event){
        //System.out.println("you pressed !");
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        double xClicked = event.getX();
        double yClicked = event.getY();
        drawX(xClicked, yClicked,5, gc);
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

