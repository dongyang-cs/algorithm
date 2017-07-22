package me.alfod.datastructure.graph;

/**
 * Created by Arvin Alfod on 2017/7/15.
 */
public class Line {
    private int weight;
    private GraphNode graphNode;

    public Line(GraphNode graphNode, int weight) {
        this.graphNode = graphNode;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GraphNode getGraphNode() {
        return graphNode;
    }

    public void setGraphNode(GraphNode graphNode) {
        this.graphNode = graphNode;
    }
}
