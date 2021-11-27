package main.projectneighbourgraph.linkStrategy;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public interface LinkStrategy {

    ArrayList<Edge> link(ArrayList<Node> nodeList, int arg, DistanceStrategy distanceStrategy) ;//for kNN and egraph algorithms

}
