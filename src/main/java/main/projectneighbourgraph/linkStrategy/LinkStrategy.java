package main.projectneighbourgraph.linkStrategy;

import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public interface LinkStrategy {
    //public void link(ArrayList nodeList) ;
    ArrayList<Edge> link(ArrayList<Node> nodeList, int arg) ;//for kNN and egraph algorithms
}
