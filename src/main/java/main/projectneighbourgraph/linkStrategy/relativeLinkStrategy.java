package main.projectneighbourgraph.linkStrategy;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Class that implements the Relative Neighborhood Graph Algorithm using the LinkStrategy interface.
 * Also has a DistanceStrategy that needs to be set before using the algorithm.
 */
public class relativeLinkStrategy implements LinkStrategy {

    /**
     * Uses the RNG algorithm to make a list of edges
     *
     * @param nodeList the list of nodes to link
     * @param arg Array of argument, here it is useless
     * @param distanceStrategy the distance strategy to use
     */
    public ArrayList<Edge> link(ArrayList<Node> nodeList, int[] arg, DistanceStrategy distanceStrategy) {
        ArrayList<Edge> RNG = new ArrayList<>();
        //Initialisation:
        //Distance between i and j ---> d(i, j)
        double d[][] = new double[nodeList.size()][nodeList.size()];
        //Max Distance between d(i, k) and d(j, k) ---> d(i, j, k)
        double dkmax[][][] = new double[nodeList.size()][nodeList.size()][nodeList.size()];
        ////////////////////////////////////////////////////////
        //Step 1: Calculate distance between each pair of points
        for (int i = 0; i < nodeList.size(); i++){
            for (int j = 0; j < nodeList.size(); j++){
                if (i != j){
                    Node node1 = nodeList.get(i);
                    Node node2 = nodeList.get(j);
                    double centreX = Math.abs((node1.getUnitxPos() + node2.getUnitxPos()) / 2);
                    double CentreY = Math.abs((node1.getUnityPos() + node2.getUnityPos()) / 2);
                    d[i][j] = Math.sqrt(Math.pow((node1.getUnitxPos() - node2.getUnitxPos()), 2) + Math.pow((node1.getUnityPos() - node2.getUnityPos()), 2)) / 2;
                } else {
                    d[i][j] = 0;
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Step 2: Calculate the max distance for each pair of points and for k = 1......n between (i, k) & (j, k)
        for (int i = 0; i < nodeList.size(); i++){
            for (int j = 0; j < nodeList.size(); j++){
                for (int k = 0; k < nodeList.size(); k++){
                    if (i != j & i != k & j != k){
                        dkmax[i][j][k] = Math.max(d[k][i], d[j][k]);
                    } else {
                        dkmax[i][j][k] = 0;
                    }
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Step 3: Verify if there is an edge between i & j, if a dkmax is smaller than d(i, j), no edge will be created
        for (int i = 0; i < nodeList.size(); i++){
            for (int j = 0; j < nodeList.size(); j++){
                boolean edgeBtwIAndJ = true;
                for (int k = 0; k < nodeList.size(); k++){
                    if (i != j & i != k & j != k){
                        if (dkmax[i][j][k] < d[i][j]){
                            edgeBtwIAndJ = false;
                        }
                    }
                }
                if (edgeBtwIAndJ){
                    RNG.add(new Edge(nodeList.get(i), nodeList.get(j)));
                }
            }
        }
        return RNG;
    }

}