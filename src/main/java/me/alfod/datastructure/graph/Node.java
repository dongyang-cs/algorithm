package me.alfod.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arvin Alfod on 2017/7/15.
 */
public class Node {
    //node name
    private String name;
    //distance from start node to this node
    private int distance;
    private int inDegree;
    //lines from this node to other node
    private List<Line> lineList;

    public Node(String name) {
        this.name = name;
        lineList = new ArrayList<>();
    }

    public Node() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("name='").append(name).append('\'');
        sb.append(", distance=").append(distance);
        sb.append('}');
        return sb.toString();
    }

    public int riseInDegree() {
        return this.inDegree += 1;
    }

    public int fallInDegree() {

        this.inDegree -= 1;
        if (this.inDegree < 0) {
            this.inDegree = 0;
        }
        return this.inDegree;
    }

    public int getIndegree() {
        return inDegree;
    }

    public void setIndegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public void link(Node node, int weight) {
        this.add(new Line(node, weight));
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void add(Line line) {
        lineList.add(line);
    }

    public void add(Node node, int weight) {
        lineList.add(new Line(node, weight));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }
}
