package main.projectneighbourgraph.distanceStrategy;

import main.projectneighbourgraph.graphdata.Edge;
import main.projectneighbourgraph.graphdata.Node;

import java.util.ArrayList;

public interface DistanceStrategy {
    int dist(Node a, Node b);
}

