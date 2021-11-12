package main.projectneighbourgraph.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.math.MathGraph;

/**
 * Class that implements the k Nearest Neighbours algorithm using the LinkStrategy interface.
 */
public class kNNLinkStrategy implements LinkStrategy {

    /**
     * Uses the kNN algorithm to make a list of edges corresponding to the k(arg) nearest neighbours
     * @param nodeList the list of nodes to link
     * @param arg the number of nearest neighbours to choose
     */
    public ArrayList<Edge> link(ArrayList<Node> nodeList, int arg)
    {
        //TODO : fix the algorithm

        //check if the argument is valid
        if(arg < 1)
            throw new IllegalArgumentException("Error : argument is too low, must be at least one");
        if(arg > nodeList.size())
            throw new IllegalArgumentException("Error : argument is too high, must be less than the numbers of nodes");



        //create the distance matrix and initialize the variables
        double[][] distanceMatrix = MathGraph.getDistanceMatrix(nodeList);
        double[] distanceToINode;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        int nodeNb = nodeList.size();

        //for each node
        for(int i = 0; i < nodeNb-1; i++){
            //get all the distances to the i-th node
            distanceToINode = distanceMatrix[i];

            int curI=i;
            //make a list of all nodes after the i-th node
            NearestNode[] nearestNodeArrays = new NearestNode[nodeNb-i-1];
            //for each node after the i-th node
            for(int j = curI+1; j<nodeNb;j++,curI++){
                //add the distance to the list
                nearestNodeArrays[curI-i] = new NearestNode(j,distanceToINode[j]);
                //nearestNodeArrays[j-curI+1] = new NearestNode(j,distanceToINode[j]);
            }

            NearestNode.SortDistance(nearestNodeArrays);
            //from here, there's the array with the distance sorted, and the node position in nodeArraylist attached

            //for k nearest neighbours
            for(int k = 0; k < arg; k++){
                //if there isn't enough nodes, we stop
                if(curI+k >= nodeNb){
                    break;
                }
                NearestNode curNear = nearestNodeArrays[k];
                //find the 2 nodes
                Node node1 = nodeList.get(i);
                Node node2 = nodeList.get(curNear.Position);
                //create the edge and add it to the list
                edgeArrayList.add(new Edge(node1,node2));
            }
        }


        return edgeArrayList;
    }

}

/**
 * Class that contains the position and the distance of a node
 * used in the kNN algorithm to sort the nodes by distance
 */
class NearestNode
{
    public NearestNode(int position, double distance)
    {
        this.Position = position;
        this.Distance = distance;
    }
    public int Position;
    public double Distance;

    public static NearestNode[] SortDistance(NearestNode[] items)
    {
        Arrays.sort(items, new DistanceSort());
        return items;
    }

}

/**
 * Comparator that compares the distance of 2 NearestNode
 */
class DistanceSort implements Comparator<NearestNode>
{
    public int compare(NearestNode o1, NearestNode o2)
    {
        return Double.compare(o1.Distance, o2.Distance);
    }
}
