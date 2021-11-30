package main.projectneighbourgraph.linkStrategy;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

/**
 * Strategy interface to link nodes in a graph.
 */
public interface LinkStrategy {

    /**
     * Links nodes in a graph.
     * @param nodeList list of nodes to link
     * @param arg Array of arguments to use in the link strategy
     * @param distanceStrategy distance strategy to use
     */
    ArrayList<Edge> link(ArrayList<Node> nodeList, int[] arg, DistanceStrategy distanceStrategy) throws Exception ;//for kNN and egraph algorithms

}
