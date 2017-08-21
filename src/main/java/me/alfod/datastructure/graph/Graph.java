package me.alfod.datastructure.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Arvin Alfod on 2017/8/20.
 */
public class Graph {
    public static List<Node> topology(List<Node> inputNodeList) {
        //calculated in-degree of the list
        for (Node node : inputNodeList) {
            for (Line line : node.getLineList()) {
                line.getNode().riseInDegree();
            }
        }
        List<Node> topology = new LinkedList<>();
        //save the value of the old topology list to check loop
        int oldSize = inputNodeList.size();
        //hash set is very quick to check whether node contains
        Set<Node> containedNodeSet = new HashSet<>();
        while (inputNodeList.size() != topology.size()) {
            //if above is true, means  some node still dissociate from topology list
            if (oldSize == topology.size()) {
                //if above is true, means topology list has not increase after a traversal of inputNodeList
                throw new RuntimeException("loop node error");
            }
            //save the topology list size
            oldSize = topology.size();
            for (Node node : inputNodeList) {
                if (!containedNodeSet.contains(node) && node.getIndegree() == 0) {
                    checkInDegree(node, topology, containedNodeSet);
                }
            }
        }
        return topology;
    }

    private static void checkInDegree(Node node, List<Node> nodeList, Set<Node> containedSet) {
        if (containedSet.contains(node)) {
            throw new RuntimeException("loop node error");
        }
        if (node.fallInDegree() == 0) {
            nodeList.add(node);
            containedSet.add(node);
            for (Line line : node.getLineList()) {
                checkInDegree(line.getNode(), nodeList, containedSet);
            }
        }
    }
}
