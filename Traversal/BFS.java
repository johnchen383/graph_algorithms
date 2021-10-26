package Traversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

public class BFS {
    public static void run(Graph graph) {
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

        for (Node s : graph.getNodeSet()) {
            if (colour.get(s) == Colour.WHITE) {
                visit(s, q, colour, dist, pred, graph);
            }
        }

        print(q, colour, dist, pred, graph);
    }

    private static void visit(Node s, Queue<Node> q, Map<Node, Colour> colour, Map<Node, Integer> dist,
            Map<Node, Node> pred, Graph graph) {
        colour.replace(s, Colour.GREY);
        dist.replace(s, 0);
        q.add(s);

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
        }
    }

    private static void print(Queue<Node> q, Map<Node, Colour> colour, Map<Node, Integer> dist, Map<Node, Node> pred, Graph graph) {
        System.out.println("Queue: " + q);
        GraphUtil.printColour(colour, graph);
        GraphUtil.printDist(dist, graph);
        GraphUtil.printPred(pred, graph);
    }

    public static void runExample25_4(){
        Graph graph = new Graph();
        graph.addArc(0, 3);
        graph.addArc(0, 1);
        graph.addArc(1, 3);
        graph.addArc(1, 2);
        graph.addArc(2, 1);
        graph.addArc(4, 2);
        graph.addArc(3, 4);

        BFS.run(graph);
    }
}
