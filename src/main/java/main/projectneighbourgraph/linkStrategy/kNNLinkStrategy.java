package main.projectneighbourgraph.linkStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import main.projectneighbourgraph.distanceStrategy.DistanceStrategy;
import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.math.MathGraph;

/**
 * Class that implements the k Nearest Neighbours algorithm using the LinkStrategy interface.
 * Also has a DistanceStrategy that needs to be set before using the algorithm.
 */
public class kNNLinkStrategy implements LinkStrategy {

    /**
     * Uses the kNN algorithm to make a list of edges corresponding to the k(arg) nearest neighbours
     *
     * @param nodeList the list of nodes to link
     * @param arg Array of argument, here only containing the number of nearest neighbours to choose
     * @param distanceStrategy the distance strategy to use
     */
    public ArrayList<Edge> link(ArrayList<Node> nodeList, int[] arg, DistanceStrategy distanceStrategy) {
        {
            //check if there is one and only one argument
            if (arg.length != 1) {
                throw new IllegalArgumentException("kNNLinkStrategy only use one argument, too many or no arguments given");
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


            //create the distance matrix and initialize the variables
            double[][] distanceMatrix = distanceStrategy.getDistanceMatrix(nodeList);
            double[] distanceToINode;
            ArrayList<Edge> edgeArrayList = new ArrayList<>();
            int nodeNb = nodeList.size();
            boolean addEdge = true;

            //for each node, sort the distance to the other node
            for (int i = 0; i < nodeNb; i++) {
                distanceToINode = distanceMatrix[i];

                NearestNode[] nearestNodes = new NearestNode[nodeNb];
                for (int j = 0; j < nodeNb; j++) {
                    nearestNodes[j] = new NearestNode(j, distanceToINode[j]);
                }

                //sort the array
                NearestNode.SortDistance(nearestNodes);

                //keep the k nearest neighbours to create edges
                for (int k = 1; k <= arg[0]; k++) {
                    if (k >= nodeList.size()) {
                        break;
                    }
                    //we check if the possible edge already exists/was already created
                    for (Edge edge : edgeArrayList) {

                        if ((edge.getNode1() == nodeList.get(nearestNodes[k].Position)) &&
                                (edge.getNode2() == nodeList.get(i))) {
                            //the edge already exists, so we don't add it
                            addEdge = false;
                            break;
                        }
                    }
                    if (addEdge) {
                        edgeArrayList.add(new Edge(nodeList.get(i), nodeList.get(nearestNodes[k].Position)));
                    } else {
                        addEdge = true;
                    }

                }

            }

            return edgeArrayList;
        }

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
class DistanceSort implements Comparator<NearestNode> {
    public int compare(NearestNode o1, NearestNode o2) {
        return Double.compare(o1.Distance, o2.Distance);
    }
}
