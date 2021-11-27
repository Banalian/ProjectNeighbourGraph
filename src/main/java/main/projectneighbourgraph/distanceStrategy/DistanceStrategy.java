package main.projectneighbourgraph.distanceStrategy;

import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public interface DistanceStrategy {

    double distBetweenTwoNodes(Node a, Node b);

    double distBetweenNodeAndCoord(Node a, double x, double y);

    /**
     * Creates a square matrix telling the distance between two nodes
     *
     * @param nodeArrayList the list of Nodes to make the matrix from
     * @return a square matrix of dimension 2 (dim2 array) and nodeNb size filled with the distances between two nodes A and B
     */
    double[][] getDistanceMatrix(ArrayList<Node> nodeArrayList);

}

