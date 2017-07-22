package me.alfod.datastructure.graph;

import java.util.List;

/**
 * Created by Arvin Alfod on 2017/7/16.
 */
public class GraphList {
    private List<GraphNode> graphNodeList;

    public void add(GraphNode graphNode) {
        graphNodeList.add(graphNode);
    }
}
