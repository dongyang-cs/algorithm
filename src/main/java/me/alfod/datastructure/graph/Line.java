package me.alfod.datastructure.graph;

/**
 * Created by Arvin Alfod on 2017/7/15.
 */
public class Line {
    private int weight;
    private Node node;

    public Line(Node node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
