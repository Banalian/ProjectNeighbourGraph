package main.projectneighbourgraph.graphdata;

/**
 * Class to store an Edge, with is a line between two nodes.
 */
public class Edge {

    private Node node1;
    private Node node2;

    static private int edgeTotal = 0;

    /**
     * The number of the edge, to differentiate it from the other edges.
     */
    private int edgeNb;

    public Edge(Node node1, Node node2){
        this.node1 = node1;
        this.node2 = node2;
        edgeNb = edgeTotal++;
    }


    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public static int getEdgeTotal() {
        return edgeTotal;
    }
}
