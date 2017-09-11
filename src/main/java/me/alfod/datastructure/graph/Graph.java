package me.alfod.datastructure.graph;

import java.util.*;

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

    public static Set<Node> dijkstra(Node start) {
        Set<Node> knownSets = new HashSet<>();
        start.setDistance(0);
        knownSets.add(start);
        calculateDistance(start, knownSets);
        return knownSets;
    }

    private static void calculateDistance(Node node, Set<Node> knowSet) {
        if (node == null
                || node.getLineList() == null
                || node.getLineList().isEmpty()) {
            return;
        }


        Collections.sort(node.getLineList());

        int distance, sonDistance;
        Node next = null;
        for (Line line : node.getLineList()) {
            if (!knowSet.contains(line.getNode())) {
                if (next == null) {
                    next = line.getNode();
                }
                System.out.println(node.getLineList());
                distance = node.getDistance() + line.getWeight();
                if (line.getNode().getDistance() == 0 || line.getNode().getDistance() > distance) {
                    line.getNode().setDistance(distance);
                }
                knowSet.add(line.getNode());
                for (Line sonLine : line.getNode().getLineList()) {
                    sonDistance = line.getNode().getDistance() + sonLine.getWeight();
                    if (!knowSet.contains(sonLine.getNode())
                            && (sonLine.getNode().getDistance() == 0 || sonLine.getNode().getDistance() > sonDistance)) {
                        sonLine.getNode().setDistance(sonDistance);
                    }
                }
            }
        }
        if (next != null) {
            calculateDistance(next, knowSet);
        }
    }

}
