package main.projectneighbourgraph.graphdata;

import java.util.ArrayList;

/**
 * Class that regroups all the data for other classes to get
 */
public class Graph {

    private ArrayList<Node> nodeArrayList;
    private ArrayList<Edge> edgeArrayList;
    private static int nextGraphId = 0;
    private int graphId;

    public Graph(){
        nodeArrayList = new ArrayList<>();
        edgeArrayList = new ArrayList<>();
        graphId = nextGraphId++;
    }


    /**
     * get the list of nodes
     * @return an ArrayList of nodes
     */
    public ArrayList<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    /**
     * get the list of edges
     * @return an ArrayList of edges
     */
    public ArrayList<Edge> getEdgeArrayList() {
        return edgeArrayList;
    }

    public int getGraphId() {
        return graphId;
    }


    /**
     * checks if a node is in the current list
     * @param nodeToCheck the node to check
     * @return true if the node is in the list, false otherwise
     */
    public boolean isNodeInList(Node nodeToCheck){
        for(Node node : nodeArrayList){
            if(node.equals(nodeToCheck)){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if an edge is in the current list
     * @param edgeToCheck the edge to check
     * @return true if the edge is in the list, false otherwise
     */
    public boolean isEdgeInList(Edge edgeToCheck){
        for(Edge edge : edgeArrayList){
            if(edge.equals(edgeToCheck)){
                return true;
            }
        }
        return false;
    }

    /**
     * adds a node, only if the node isn't already in the list
     * @throws IllegalArgumentException if the node is already in the list
     * @param nodeToAdd the node to add to the list
     */
    public void addNode(Node nodeToAdd){
        if(isNodeInList(nodeToAdd)){
            throw new IllegalArgumentException("Error : the node already exist in this list");
        }
        nodeArrayList.add(nodeToAdd);
    }


    /**
     * adds an edge, only if the node isn't already in the list
     * @throws IllegalArgumentException if the edge is already in the list
     * @param edgeToAdd the edge to add to the list
     */
    public void addEdge(Edge edgeToAdd){
        if(isEdgeInList(edgeToAdd)){
            throw new IllegalArgumentException("Error : the edge already exist in this list");
        }
        edgeArrayList.add(edgeToAdd);
    }


    /**
     * removes a node from the ArrayList, if it exists
     * @throws IllegalArgumentException if the node isn't in the list
     * @param nodeToRemove the node to remove
     */
    public void removeNode(Node nodeToRemove){
        if(!isNodeInList(nodeToRemove)){
            throw new IllegalArgumentException("Error : the node "+nodeToRemove.getId()+" is not in the list");
        }
        nodeArrayList.remove(nodeToRemove);
    }

    /**
     * removes an edge from the ArrayList, if it exists
     * @throws IllegalArgumentException if the edge isn't in the list
     * @param edgeToRemove the edge to remove
     */
    public void removeEdge(Edge edgeToRemove){
        if(!isEdgeInList(edgeToRemove)){
            throw new IllegalArgumentException("Error : the edge "+edgeToRemove.getNode1().getId()+"---"+edgeToRemove.getNode2().getId()+" is not in the list");
        }
        edgeArrayList.remove(edgeToRemove);
    }

    public void setEdgeArrayList(ArrayList<Edge> result) {
        this.edgeArrayList = result;
    }
}
