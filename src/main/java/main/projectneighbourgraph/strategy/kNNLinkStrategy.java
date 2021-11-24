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
        //check if the argument is valid
        if(arg < 1)
            throw new IllegalArgumentException("Error : argument is too low, must be at least one");
        if(arg > nodeList.size()){
            //commented because it's not a problem, better to just warn the user
            //throw new IllegalArgumentException("Error : argument is too high, must be less than the numbers of nodes");
            System.out.println("Warning : argument is too high, must be less than the numbers of nodes\n"+
                    "No edges added");
            return new ArrayList<>();
        }




        //create the distance matrix and initialize the variables
        double[][] distanceMatrix = MathGraph.getDistanceMatrix(nodeList);
        double[] distanceToINode;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        int nodeNb = nodeList.size();
        boolean addEdge = true;

        //for each node, sort the distance to the other node
        for(int i = 0; i < nodeNb; i++){
            distanceToINode = distanceMatrix[i];

            NearestNode[] nearestNodes = new NearestNode[nodeNb];
            for(int j = 0; j < nodeNb; j++){
                nearestNodes[j] = new NearestNode(j, distanceToINode[j]);
            }

            //sort the array
            NearestNode.SortDistance(nearestNodes);

            //keep the k nearest neighbours to create edges
            for(int k = 1; k <= arg; k++){
                if(k >= nodeList.size()){
                    break;
                }
                //we check if the possible edge already exists/was already created
                for(Edge edge : edgeArrayList){

                    if( (edge.getNode1() == nodeList.get(nearestNodes[k].Position)) &&
                        (edge.getNode2() == nodeList.get(i)))
                    {
                        //the edge already exists, so we don't add it
                        addEdge = false;
                        break;
                    }
                }
                if(addEdge){
                    edgeArrayList.add(new Edge(nodeList.get(i), nodeList.get(nearestNodes[k].Position)));
                }else{
                    addEdge = true;
                }

            }

        }

        /*
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
        */

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
