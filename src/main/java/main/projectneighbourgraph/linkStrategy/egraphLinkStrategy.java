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

    public ArrayList<Edge> link(ArrayList<Node> nodeList, int[] arg, DistanceStrategy distanceStrategy) {
        ArrayList<Edge> toLink = new ArrayList<>(); //List of edges to create
        double epsilon = 0.05;

        //check if there is one and only one argument
        if (arg.length != 1) {
            throw new IllegalArgumentException("egraphLinkStrategy only use one argument, too many or no arguments given");
        }
        //check if the argument is valid
        if (arg[0] < 1)
            throw new IllegalArgumentException("Error : k argument is too low, must be at least one");

        if (arg[0] > nodeList.size()) {
            //commented because it's not a problem, better to just warn the user
            //throw new IllegalArgumentException("Error : argument is too high, must be less than the numbers of nodes");
            System.out.println("Warning : argument is too high, must be less than the numbers of nodes\n" +
                    "No edges added");
            return new ArrayList<>();
        }


        if (distanceStrategy == null) {
            throw new IllegalArgumentException("Error : distance strategy is null");
        }

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
