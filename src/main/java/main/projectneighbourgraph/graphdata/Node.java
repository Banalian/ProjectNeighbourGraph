package main.projectneighbourgraph.graphdata;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * Data class for a node, containing its canvas position and relative position for our range
 */
public class Node {

    /**
     * The canvas x position of the node
     */
    private double xPos;
    /**
     * The canvas y position of the node
     */
    private double yPos;
    /**
     * The relative x position of the node (between 0 and 1)
     */
    private double unitxPos;
    /**
     * The relative y position of the node (between 0 and 1)
     */
    private double unityPos;
    /**
     * The node's id
     */
    private int id;

    /**
        The node's color
     */
    private Color nodeColor;


    /**
     * Normal Constructor, no color needed
     * @param xPos the x position of the node
     * @param yPos the y position of the node
     * @param unitxPos the relative x position of the node (between 0 and 1)
     * @param unityPos the relative y position of the node (between 0 and 1)
     * @param id the node's id
     */
    public Node(double xPos, double yPos, double unitxPos, double unityPos, int id){
        this.xPos = xPos;
        this.yPos = yPos;
        this.unitxPos = unitxPos;
        this.unityPos = unityPos;
        this.id = id;
        this.nodeColor = null;
    }

    /**
     * Constructor with color
     * @param xPos the x position of the node
     * @param yPos the y position of the node
     * @param unitxPos the relative x position of the node (between 0 and 1)
     * @param unityPos the relative y position of the node (between 0 and 1)
     * @param id the node's id
     * @param nodeColor the node's color
     */
    public Node(double xPos, double yPos, double unitxPos, double unityPos, int id, Color nodeColor) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.unitxPos = unitxPos;
        this.unityPos = unityPos;
        this.id = id;
        this.nodeColor = nodeColor;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setxPos(double xPos){
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }


    public double getUnitxPos() {
        return unitxPos;
    }

    public double getUnityPos() {
        return unityPos;
    }

    public void setUnitxPos(double unitxPos) {
        this.unitxPos = unitxPos;
    }

    public void setUnityPos(double unityPos) {
        this.unityPos = unityPos;
    }

    public Color getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(Color nodeColor) {
        this.nodeColor = nodeColor;
    }

    public int getId() {
        return id;
    }


    /**
     * Automatic function : create a string representation of the node
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return "Node{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", unitxPos=" + unitxPos +
                ", unityPos=" + unityPos +
                ", color=" + nodeColor +
                ", id=" + id +
                '}';
    }

    /**
     * Automatic function : checks if two nodes are the same
     * @param o the object to check
     * @return true if the object is the same node, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.xPos, xPos) != 0) return false;
        if (Double.compare(node.yPos, yPos) != 0) return false;
        if (Double.compare(node.unitxPos, unitxPos) != 0) return false;
        if (Double.compare(node.unityPos, unityPos) != 0) return false;
        return id == node.id;
    }

}
