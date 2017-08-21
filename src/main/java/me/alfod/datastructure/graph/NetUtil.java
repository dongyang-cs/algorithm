package me.alfod.datastructure.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arvin Alfod on 2017/8/17.
 */
public class NetUtil {

    public static String boundary = ":";
    public static String separator = ",";

    public static void main(String[] args) {
        gainNet("");
    }

    public static List<Node> gainNet(String relativePath) {
        String filePath = System.getProperty("user.dir") + relativePath;
        List<Node> nodeList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line, name, weight;
            String[] nodesStr;
            Node node, linkedNode;
            Map<String, Node> graphNodeMap = new HashMap<>();
            Pattern pattern = Pattern.compile("(\\d+)(\\w+)");
            Matcher matcher;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) {
                    continue;
                }
                name = line.substring(0, line.indexOf(boundary));
                if (graphNodeMap.get(name) != null) {
                    node = graphNodeMap.get(name);
                } else {
                    node = new Node(name);
                    graphNodeMap.put(name, node);
                }
                nodeList.add(node);

                nodesStr = line.substring(line.indexOf(boundary) + 1).split(separator);
                if (nodesStr.length != 0) {
                    for (String linkLine : nodesStr) {
                        linkLine.trim();
                        matcher = pattern.matcher(linkLine);
                        while (matcher.find()) {
                            weight = matcher.group(1);
                            name = matcher.group(2);
                            if (graphNodeMap.get(name) == null) {
                                linkedNode = new Node(name);
                                graphNodeMap.put(name, linkedNode);
                            } else {
                                linkedNode = graphNodeMap.get(name);
                            }
                            node.link(linkedNode, Integer.valueOf(weight));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeList;
    }
}
