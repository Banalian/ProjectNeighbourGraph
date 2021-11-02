package main.projectneighbourgraph.graphdata;

/**
 * Data class for a node, containing its canvas position and relative position for our range
 */
public class Node {
    private double xPos;
    private double yPos;
    private double unitxPos;
    private double unityPos;
    private int id;
    //private colorClass colorClass;

    public Node(double xPos, double yPos, double unitxPos, double unityPos, int id){
        this.xPos = xPos;
        this.yPos = yPos;
        this.unitxPos = unitxPos;
        this.unityPos = unityPos;
        this.id = id;
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

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Node{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", unitxPos=" + unitxPos +
                ", unityPos=" + unityPos +
                ", id=" + id +
                '}';
    }

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
