package me.alfod.datastructure.graph;

import java.util.List;

/**
 * Created by Arvin Alfod on 2017/7/15.
 */
public class GraphNode {
    private String name;
    private int dist;
    private List<Line> lineList;

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void add(Line line) {
        lineList.add(line);
    }

    public void add(GraphNode graphNode, int weight) {
        lineList.add(new Line(graphNode, weight));
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
