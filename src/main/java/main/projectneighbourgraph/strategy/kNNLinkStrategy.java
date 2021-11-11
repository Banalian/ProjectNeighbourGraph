package main.projectneighbourgraph.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;
import main.projectneighbourgraph.math.MathGraph;

public class kNNLinkStrategy implements LinkStrategy {

    /**
     * Uses the kNN algorithm to make a list of edges corresponding to the k(arg) nearest neighbours
     * @param nodeList the list of nodes to link
     * @param arg the number of nearest neighbours to choose
     */
    public ArrayList<Edge> link(ArrayList<Node> nodeList, int arg){
        //TODO : write the KNN alogrithm
        if(arg < 1)
            throw new IllegalArgumentException("Error : argument is too low, must be at least one");
        if(arg > nodeList.size())
            throw new IllegalArgumentException("Error : argument is too high, must be less than the numbers of nodes");



        double[][] distanceMatrix = MathGraph.getDistanceMatrix(nodeList);
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        int nodeNb = nodeList.size();
        for(int i=0; i<nodeNb;i++){
            double[] distanceToINode = distanceMatrix[i];

            int curI=0;
            NearestNode[] nearestNodeArrays = new NearestNode[nodeNb-curI];
            for(int j = curI+1; i<nodeNb;i++){
                nearestNodeArrays[curI] = new NearestNode(j,distanceToINode[j]);
            }

            NearestNode.SortDistance(nearestNodeArrays);
            //from here, there's the array with the distance sorted, and the node position in nodeArraylist attached

            for(int k = 0; k < arg; k++){
                NearestNode curNear = nearestNodeArrays[k];
                //find the 2 nodes
                Node node1 = nodeList.get(i);
                Node node2 = nodeList.get(curNear.Position);
                edgeArrayList.add(new Edge(node1,node2));
            }
        }


        return edgeArrayList;
    }

}


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

class DistanceSort implements Comparator<NearestNode>
{
    public int compare(NearestNode o1, NearestNode o2)
    {
        return Double.compare(o1.Distance, o2.Distance);
    }
}
