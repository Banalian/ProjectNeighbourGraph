package main.projectneighbourgraph.graphdata;

public class Node {
    private double xPos;
    private double yPos;
    private int id;
    //private colorClass colorClass;

    public Node(double xPos, double yPos, int id){
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Node{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", id=" + id +
                '}';
    }
}
