package main.projectneighbourgraph.strategy;

import main.projectneighbourgraph.strategy.LinkStrategy;

import java.util.ArrayList;

public class Context {
    private LinkStrategy strategy;

    void setStrategy(LinkStrategy strategy) {
        this.strategy = strategy;
    }
    void executeStrategy(ArrayList nodeList, int arg){
        strategy.link(nodeList, arg);
    }
}
