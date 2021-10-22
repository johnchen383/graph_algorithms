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

    public static void printDistanceMatrix(int[][] dist, Map<Node, Integer> mapping){
        System.out.println("Mapping");
        for (Node u : mapping.keySet()){
            System.out.print(u + "->" + Integer.toString(mapping.get(u)) + "\t");
        }
        System.out.println();

        printDistanceMatrix(dist);
    }

    public static void printDistanceMatrix(int[][] dist){
        System.out.println("Distance Matrix");
        for (int i = 0; i < dist.length; i++){
            for (int j = 0; j < dist[0].length; j++){
                if (dist[i][j] == Integer.MAX_VALUE){
                    System.out.print("INF \t");
                } else {
                    System.out.print(Integer.toString(dist[i][j])+ "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
