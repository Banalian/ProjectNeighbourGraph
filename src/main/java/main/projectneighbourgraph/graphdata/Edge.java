package main.projectneighbourgraph.graphdata;

/**
 * Class to store an Edge, with is a line between two nodes.
 * You could use them as directed or undirected(using node1 as the start node for a directed edge).
 */
public class Edge {

    /**
     * The first node of the edge.
     */
    private Node node1;
    /**
     * The second node of the edge.
     */
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

    /**
     * get the total number of edges created since the start of the program
     * @return the total number of edges
     */
    public static int getEdgeTotal() {
        return edgeTotal;
    }


    /**
     * Automatic function : checks if two edges are the same
     * @param o the object to check
     * @return true if the object is the same edge, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (edgeNb != edge.edgeNb) return false;
        if (getNode1() != null ? !getNode1().equals(edge.getNode1()) : edge.getNode1() != null) return false;
        return getNode2() != null ? getNode2().equals(edge.getNode2()) : edge.getNode2() == null;
    }


}
