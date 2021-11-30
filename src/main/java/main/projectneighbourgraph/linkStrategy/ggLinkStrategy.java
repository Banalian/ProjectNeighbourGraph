package main.projectneighbourgraph.linkStrategy;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.math.MathGraph;

import java.util.ArrayList;

public class ggLinkStrategy implements LinkStrategy {

    public ArrayList<Edge> link(ArrayList<Node> nodeList, int[] arg, DistanceStrategy distanceStrategy) {
        ArrayList<Edge> toLink = new ArrayList<>(); //List of edges to create

        //Take 2 nodes from the nodes list and create their circumscribed circle
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                if (i != j){
                    Node node1 = nodeList.get(i);
                    Node node2 = nodeList.get(j);
                    double centerXpoint = Math.abs((node1.getUnitxPos() + node2.getUnitxPos()) / 2);
                    double centerYpoint = Math.abs((node1.getUnityPos() + node2.getUnityPos()) / 2);
                    double radius = Math.sqrt(Math.pow((node1.getUnitxPos() - node2.getUnitxPos()), 2) + Math.pow((node1.getUnityPos() - node2.getUnityPos()), 2)) / 2;

                    //Check if any other points are in the circle
                    int k = 0;
                    boolean nodeFound = false;
                    while (k < nodeList.size() && !nodeFound) {
                        if (k != i && k != j){
                            if(MathGraph.isPointInCircle(nodeList.get(k), centerXpoint, centerYpoint, radius)){
                                nodeFound = true;
                            }
                        }
                        k++;
                    }
                    //If there isn't any, link the nodes
                    if (!nodeFound){
                        toLink.add(new Edge(node1, node2));}

                }
            }

        }
        return toLink;
    }
}