package Traversal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

import comparators.NodePQComparator;
import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

public class PFS {
    private static void configurePriorities(Map<Node, Integer> priorities, Graph graph) {
        //configure
    }

    public static void run(Graph graph, boolean withIterations) {
        Map<Node, Integer> priorities = new HashMap<Node, Integer>();
        configurePriorities(priorities, graph);


        PriorityQueue<Node> pq = new PriorityQueue<Node>(graph.getOrder(), new NodePQComparator(priorities));
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Node> pred = new HashMap<Node, Node>(graph.getOrder());

        graph.sortWithPriority();
        for (Node u : graph.getNodeSet()) {
            colour.put(u, Colour.WHITE);
            pred.put(u, null);
        }

        int forestNum = 1;
        HashSet<Node> visitInPrevForest = new HashSet<Node>();
        for (Node s : graph.getNodeSet()) {
            if (colour.get(s) == Colour.WHITE) {
                visit(s, pq, colour, pred, graph, withIterations);

                // forest
                if (withIterations) {
                    System.out.println("Forest " + Integer.toString(forestNum));
                    GraphUtil.printForest(graph, pred, visitInPrevForest);
                    forestNum++;
                }

            }
        }

        if (!withIterations)
            print(pq, colour, pred, graph);

    }

    

    private static void visit(Node s, PriorityQueue<Node> pq, Map<Node, Colour> colour, Map<Node, Node> pred,
            Graph graph, boolean withIterations) {
        colour.replace(s, Colour.GREY);
        pq.add(s);

        int iterationNum = 1;

        while (!pq.isEmpty()) {
            Node u = pq.peek();

            Arc foundArc = null;
            for (Node v : graph.getNodeSet()) {
                Arc arc = graph.getArc(u, v);
                if (arc != null && colour.get(v) == Colour.WHITE) {
                    foundArc = arc;
                    break;
                }
            }

            if (foundArc != null) {
                Node v = graph.getNode(foundArc.getTail().getValue());
                colour.replace(v, Colour.GREY);
                pq.add(v);
            } else {
                colour.replace(u, Colour.BLACK);
                pq.remove();
            }

            if (withIterations) {
                System.out.println("Iteration " + Integer.toString(iterationNum));
                System.out.println("Visiting " + u);
                print(pq, colour, pred, graph);
                System.out.println();
                iterationNum++;
            }
        }
    }

    private static void print(PriorityQueue<Node> pq, Map<Node, Colour> colour, Map<Node, Node> pred,
            Graph graph) {
        System.out.println("Priority Queue: " + pq);
        GraphUtil.printColour(colour, graph);
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

        PFS.run(graph, true);
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

        PFS.run(graph, true);
    }
}
