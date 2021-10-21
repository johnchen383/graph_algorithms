package utils;

import java.util.Map;

import models.graphs.Graph;
import models.nodes.Node;

public class GraphUtil {
    public static void printFinalDistance(Map<Node, Integer> dist, Node s, Graph graph){
        System.out.println("Result");
        System.out.println("Distance from node " + s);
        printDistance(dist, graph);
    }

    public static void printDistance(Map<Node, Integer> dist, Graph graph){
        for (Node n: graph.getNodeSet()){
            if (dist.get(n) == Integer.MAX_VALUE){
                System.out.print(n + ": INF \t");
            } else {
                System.out.print(n + ": " + Integer.toString(dist.get(n))+ "\t");
            }
            
        }
        System.out.println();
        System.out.println();
    }
}
