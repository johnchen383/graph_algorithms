package SSSP;

import java.util.HashMap;
import java.util.Map;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

/**
 * Assumes: weighted, directed, no negative weights
 */
public class Dijkstra {
    public static void run(Graph graph, Node s){
        run(graph, s, false);
    }

    public static void run(Graph graph, Node s, boolean showIterations){
        Integer numIterations = null;
        if (showIterations) numIterations = 0;

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
        if (showIterations) GraphUtil.printDistance(dist, graph);

        colour.replace(s, Colour.BLACK);

        Node u;
        while ((u = getMinWhiteNode(graph, colour, dist, showIterations)) != null){
            if (showIterations) numIterations++;
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

            if (showIterations) GraphUtil.printDistance(dist, graph);
        }

        GraphUtil.printFinalDistance(dist, s, graph);
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
        graph.addArc(0, 1, 1);
        graph.addArc(2, 1, 2);
        graph.addArc(1, 3, 2);
        graph.addArc(3, 0, 2);
        graph.addArc(0, 2, 4);
        graph.addArc(2, 3, 5);

        graph.sortWithPriority();

        Dijkstra.run(graph, graph.getNode(Integer.toString(3)), true);
    }

    public static void runExample29_10(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(0, 1, 3);
        graph.addArc(0, 2, 1);
        graph.addArc(0, 3, 5);
        graph.addArc(2, 1, 1);
        graph.addArc(1, 3, 2);
        graph.addArc(3, 1, 2);
        graph.addArc(1, 4, 2);
        graph.addArc(3, 4, 1);
        graph.addArc(2, 4, 6);

        graph.sortWithPriority();

        Dijkstra.run(graph, graph.getNode(Integer.toString(0)), true);
    }

    public static void runExample29_Letter(){
        //TODO: to be completed
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc('A', 'B', 3);
        graph.addArc('B', 'A', 3);
        graph.addArc(0, 2, 1);
        graph.addArc(0, 3, 5);
        graph.addArc(2, 1, 1);
        graph.addArc(1, 3, 2);
        graph.addArc(3, 1, 2);
        graph.addArc(1, 4, 2);
        graph.addArc(3, 4, 1);
        graph.addArc(2, 4, 6);

        graph.sortWithPriority();

        Dijkstra.run(graph, graph.getNode(Integer.toString(0)), true);
    }
}
