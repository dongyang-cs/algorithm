package me.alfod.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arvin Alfod on 2017/7/16.
 */
public class GraphList {
    private List<Node> nodeList;

    public GraphList() {
        nodeList = new ArrayList<>();
    }

    public void add(Node node) {
        nodeList.add(node);
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
