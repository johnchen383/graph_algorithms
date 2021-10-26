package utils;

import java.util.List;
import java.util.Map;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;

public class GraphUtil {
    public static void printFinalDistance(Map<Node, Integer> dist, Node s, Graph graph){
        System.out.println("Result");
        System.out.println("Distance from node " + s);
        printDistance(dist, graph);
    }

    public static void printDistance(Map<Node, Integer> dist, Graph graph){
        for (Node n: graph.getNodeSet()){
            System.out.print(n + ": " + InfInteger.toString(dist.get(n))+ "\t");
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
                System.out.print(InfInteger.toString(dist[i][j])+ "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printSetOfArcs(List<Arc> arcs){
        System.out.println(arcs);
    }

    public static void printMSTWeight(List<Arc> arcs){
        int weight = 0;
        for (Arc arc: arcs){
            weight += arc.getWeight();
        }
        System.out.println("Minimum weight is " + Integer.toString(weight));
    }

    public static void printColour(Map<Node, Colour> map, Graph graph){
        System.out.print("Colour: \t");
        for (Node n : graph.getNodeSet()){
            System.out.print(n + ": " + map.get(n).toString() + "\t");
        }
        System.out.println();
    }

    public static void printPred(Map<Node, Node> map, Graph graph){
        System.out.print("Predecessor: \t");
        for (Node n : graph.getNodeSet()){
            System.out.print(n + ": " + map.get(n) + "\t");
        }
        System.out.println();
    }

    public static void printDist(Map<Node, Integer> map, Graph graph){
        System.out.print("Distance: \t");
        for (Node n : graph.getNodeSet()){
            if (map.get(n) == null){
                System.out.print(n + ": null\t");
                continue;
            }
            System.out.print(n + ": " + Integer.toString(map.get(n)) + "\t");
        }
        System.out.println();
    }
}
