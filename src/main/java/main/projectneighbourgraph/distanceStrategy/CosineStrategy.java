package main.projectneighbourgraph.distanceStrategy;

import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public class CosineStrategy implements DistanceStrategy {

    public double distBetweenTwoNode(Node a, Node b){
        //TODO : write the cosine distance algorithm
        return 0;
    }

    @Override
    public double distBetweenNodeAndCoord(Node a, double x, double y) {
        return 0;
    }

    /**
     * Creates a square matrix telling the distance between two nodes
     *
     * @param nodeArrayList the list of Nodes to make the matrix from
     * @return a square matrix of dimension 2 (dim2 array) and nodeNb size filled with the distances between two nodes A and B
     */
    @Override
    public double[][] getDistanceMatrix(ArrayList<Node> nodeArrayList) {
        int nodeNb = nodeArrayList.size();
        int curNode1 = 0;
        int curNode2;
        double[][] distanceMatrix = new double[nodeNb][nodeNb];

        // we go through the list for the first node
        for (Node node1: nodeArrayList) {
            curNode2 = 0;

            //for each node to make a duo node1 and node2
            for(Node node2 : nodeArrayList){

                //if it's two different nodes
                if((node1!=node2)){
                    //get the length between them and add it to the matrix
                    double length = distBetweenTwoNode(node1,node2);
                    distanceMatrix[curNode1][curNode2] = length;
                    distanceMatrix[curNode2][curNode1] = length;
                }else{
                    //distance is 0 since it's itself
                    distanceMatrix[curNode1][curNode2] = 0;
                }
                curNode2++;
            }

            curNode1++;
        }

        return distanceMatrix;
    }


}
