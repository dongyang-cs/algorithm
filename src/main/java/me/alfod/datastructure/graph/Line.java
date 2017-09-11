package me.alfod.datastructure.graph;

/**
 * Created by Arvin Alfod on 2017/7/15.
 */
public class Line implements Comparable<Line> {
    private int weight;
    private Node node;

    public Line(Node node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Line{");
        sb.append("weight=").append(weight);
        sb.append(", node=").append(node);
        sb.append('}');
        return sb.toString();
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

    @Override
    public int compareTo(Line o) {
        if (this.weight > o.getWeight()) {
            return 1;
        } else if (this.weight == o.getWeight()) {
            return 0;
        } else {
            return -1;
        }
    }
}
