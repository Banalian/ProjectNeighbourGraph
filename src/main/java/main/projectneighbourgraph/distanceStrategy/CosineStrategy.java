package main.projectneighbourgraph.distanceStrategy;

import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public class CosineStrategy implements DistanceStrategy {

    /**
     * Calculates the cosine similarity between two nodes
     * @param a the first node
     * @param b the second node
     * @return the cosine similarity between the two nodes
     */
    @Override
    public double distBetweenTwoNodes(Node a, Node b){
        //x and y are vectors
        //x is the vector of a's coordinates
        //y is the vector of b's coordinates
        // result is (x dot y)/(sqrt(x dot x) * sqrt(y dot y))
        // x dot y = x1*y1 + x2*y2 + ... + xn*yn
        double aX = a.getUnitxPos();
        double aY = a.getUnityPos();
        double bX = b.getUnitxPos();
        double bY = b.getUnityPos();

        double xDotY = aX*bX + aY*bY;
        double xDotX = aX*aX + aY*aY;
        double yDotY = bX*bX + bY*bY;

        return 1-(xDotY/(Math.sqrt(xDotX)*Math.sqrt(yDotY)));

    }


    /**
     * Calculates the cosine similarity between a node and a coordinate
     * @param a the node
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the cosine similarity between the node and the coordinate
     */
    @Override
    public double distBetweenNodeAndCoord(Node a, double x, double y) {

        //x and y are vectors
        //x is the vector of a's coordinates
        //y is the vector of b's coordinates
        // result is (x dot y)/(sqrt(x dot x) * sqrt(y dot y))
        // x dot y = x1*y1 + x2*y2 + ... + xn*yn
        double aX = a.getUnitxPos();
        double aY = a.getUnityPos();
        double bX = x;
        double bY = y;

        double xDotY = aX*bX + aY*bY;
        double xDotX = aX*aX + aY*aY;
        double yDotY = bX*bX + bY*bY;

        return xDotY/(Math.sqrt(xDotX)*Math.sqrt(yDotY));

    }

    /**
     * Creates a square matrix telling the cosine similarity between two nodes
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
                    //get the cosineSim between them and add it to the matrix
                    double cosineSim = distBetweenTwoNodes(node1,node2);
                    distanceMatrix[curNode1][curNode2] = cosineSim;
                    distanceMatrix[curNode2][curNode1] = cosineSim;
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
