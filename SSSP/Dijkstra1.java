package SSSP;

import java.util.HashMap;
import java.util.Map;

import enums.Colour;
import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;

/**
 * Assumes: weighted, directed, no negative weights
 */
public class Dijkstra1 {
    public static void run(Graph graph, Node s){
        run(graph, s, false);
    }

    public static void run(Graph graph, Node s, boolean showIterations){
        Integer numIterations;

        if (showIterations){
            numIterations = 0;
        } else {
            numIterations = null;
        }

        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Integer> dist = new HashMap<Node, Integer>(graph.getOrder());

        //initialise
        for (Node u: graph.getNodeSet()){
            colour.put(u, Colour.WHITE);

            Arc arc = graph.getArc(s, u);
            if (arc == null){
                dist.put(u, Integer.MAX_VALUE);
            } else {
                dist.put(u, arc.getWeight());
            }
        }

        dist.replace(s, 0);

        if (showIterations) System.out.println("Iteration " + numIterations);
        if (showIterations) System.out.println("Next Black: " + s);
        printDistance(dist, graph);

        colour.replace(s, Colour.BLACK);

        Node u;
        while ((u = getMinWhiteNode(graph, colour, dist, showIterations)) != null){
            numIterations++;
            if (showIterations) System.out.println("Iteration " + numIterations);
            if (showIterations) System.out.println("Next Black: " + u);

            colour.replace(u, Colour.BLACK);

            for (Node x : graph.getNodeSet()){
                if (colour.get(x) == Colour.WHITE){
                    int calDist;

                    Arc arc = graph.getArc(u, x);
                    if (arc == null){
                        calDist = Integer.MAX_VALUE;
                    } else {
                        if (dist.get(u).equals(Integer.MAX_VALUE)) continue;
                        
                        calDist = dist.get(u) + arc.getWeight();
                    }
                    
                    if (calDist < dist.get(x)){
                        dist.replace(x, calDist);
                    }
                }
            }

            printDistance(dist, graph);
        }

        printFinalDistance(dist, s, graph);
    }
    
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

    public static Node getMinWhiteNode(Graph graph, Map<Node, Colour> colour, Map<Node, Integer> dist, boolean showIterations) {
        Node selectedNode = null;

        for (Node node : graph.getNodeSet()) {
            if (colour.get(node) == Colour.WHITE) {
                if (selectedNode == null) {
                    selectedNode = node;
                } else {
                    if (dist.get(node) < dist.get(selectedNode)) {
                        selectedNode = node;
                    } else if (dist.get(node) == dist.get(selectedNode)
                            && (node.getPriority() < selectedNode.getPriority())) {
                        selectedNode = node;
                    }
                }
            }
        }

        return selectedNode;
    }

    public static void runExample29_11(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(new Arc(new Node(0), new Node(1), 1));
        graph.addArc(new Arc(new Node(2), new Node(1), 2));
        graph.addArc(new Arc(new Node(1), new Node(3), 2));
        graph.addArc(new Arc(new Node(3), new Node(0), 2));
        graph.addArc(new Arc(new Node(0), new Node(2), 4));
        graph.addArc(new Arc(new Node(2), new Node(3), 5));

        Dijkstra1.run(graph, graph.getNode(Integer.toString(3)), true);
    }

    public static void runExample29_10(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(new Arc(new Node(0), new Node(1), 3));
        graph.addArc(new Arc(new Node(0), new Node(2), 1));
        graph.addArc(new Arc(new Node(0), new Node(3), 5));
        graph.addArc(new Arc(new Node(2), new Node(1), 1));
        graph.addEdge(new Arc(new Node(1), new Node(3), 2));
        graph.addArc(new Arc(new Node(1), new Node(4), 2));
        graph.addArc(new Arc(new Node(3), new Node(4), 1));
        graph.addArc(new Arc(new Node(2), new Node(4), 6));

        Dijkstra1.run(graph, graph.getNode(Integer.toString(3)), true);
    }
}
