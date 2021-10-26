package Traversal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

public class BFS {
    public static void run(Graph graph, boolean withIterations) {
        Queue<Node> q = new LinkedList<Node>();
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Integer> dist = new HashMap<Node, Integer>(graph.getOrder());
        Map<Node, Node> pred = new HashMap<Node, Node>(graph.getOrder());

        graph.sortWithPriority();
        for (Node u : graph.getNodeSet()) {
            colour.put(u, Colour.WHITE);
            pred.put(u, null);
            dist.put(u, null);
        }

        int forestNum = 1;
        HashSet<Node> visitInPrevForest = new HashSet<Node>();
        for (Node s : graph.getNodeSet()) {
            if (colour.get(s) == Colour.WHITE) {
                visit(s, q, colour, dist, pred, graph, withIterations);

                // forest
                if (withIterations) {
                    System.out.println("Forest " + Integer.toString(forestNum));
                    GraphUtil.printForest(graph, pred, visitInPrevForest);
                    forestNum++;
                }

            }
        }

        if (!withIterations)
            print(q, colour, dist, pred, graph);

    }

    private static void visit(Node s, Queue<Node> q, Map<Node, Colour> colour, Map<Node, Integer> dist,
            Map<Node, Node> pred, Graph graph, boolean withIterations) {
        colour.replace(s, Colour.GREY);
        dist.replace(s, 0);
        q.add(s);

        int iterationNum = 1;

        while (!q.isEmpty()) {
            Node u = q.peek();

            for (Node v : graph.getNodeSet()) {
                Arc arc = graph.getArc(u, v);
                if (arc != null) {
                    // exists
                    if (colour.get(v) == Colour.WHITE) {
                        colour.replace(v, Colour.GREY);
                        pred.replace(v, u);
                        dist.replace(v, dist.get(u) + 1);
                        q.add(v);
                    }
                }
            }

            q.remove();
            colour.replace(u, Colour.BLACK);

            if (withIterations) {
                System.out.println("Iteration " + Integer.toString(iterationNum));
                System.out.println("Visiting " + u);
                print(q, colour, dist, pred, graph);
                System.out.println();
                iterationNum++;
            }
        }
    }

    private static void print(Queue<Node> q, Map<Node, Colour> colour, Map<Node, Integer> dist, Map<Node, Node> pred,
            Graph graph) {
        System.out.println("Queue: " + q);
        GraphUtil.printColour(colour, graph);
        GraphUtil.printDist(dist, graph);
        GraphUtil.printPred(pred, graph);
    }

    public static void runExampleMultiForest() {
        Graph graph = new Graph();
        graph.addArc(0, 5);
        graph.addArc(0, 6);
        graph.addArc(6, 7);
        graph.addArc(1, 2);
        graph.addArc(2, 1);
        graph.addArc(4, 2);
        graph.addArc(3, 4);

        BFS.run(graph, true);
    }

    public static void runExample25_4() {
        Graph graph = new Graph();
        graph.addArc(0, 3);
        graph.addArc(0, 1);
        graph.addArc(1, 3);
        graph.addArc(1, 2);
        graph.addArc(2, 1);
        graph.addArc(4, 2);
        graph.addArc(3, 4);

        BFS.run(graph, true);
    }
}
