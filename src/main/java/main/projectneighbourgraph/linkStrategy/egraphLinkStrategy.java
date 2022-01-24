package main.projectneighbourgraph.linkStrategy;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.math.MathGraph;

import java.util.ArrayList;

public class egraphLinkStrategy implements LinkStrategy {

    public ArrayList<Edge> link(ArrayList<Node> nodeList, double[] arg, DistanceStrategy distanceStrategy) {
        ArrayList<Edge> toLink = new ArrayList<>(); //List of edges to create

        //check if there is one and only one argument
        if (arg.length != 1) {
            throw new IllegalArgumentException("egraphLinkStrategy only use one argument, too many or no arguments given");
        }
        //check if the argument is valid
        if (arg[0] < 0)
            throw new IllegalArgumentException("Error : epsilon argument is too low, must be at least 0");

        if(arg[0] > 1)
            throw new IllegalArgumentException("Error : epsilon argument is too high, cannot be more than 1");


        if (distanceStrategy == null) {
            throw new IllegalArgumentException("Error : distance strategy is null");
        }

        double epsilon = arg[0];

        //Take 2 nodes from the nodes list
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                if (i != j){
                    Node node1 = nodeList.get(i);
                    Node node2 = nodeList.get(j);

                    double distanceBetweenTwoNodes = Math.sqrt(Math.pow((node1.getUnitxPos() - node2.getUnitxPos()), 2) + Math.pow((node1.getUnityPos() - node2.getUnityPos()), 2)) ;


                    //link the nodes
                    if (distanceBetweenTwoNodes < epsilon){
                        toLink.add(new Edge(node1, node2));
                         }
                }

            }
        }
        return toLink;
    }
}
