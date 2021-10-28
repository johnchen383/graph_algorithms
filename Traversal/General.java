package Traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

public class General {
    public static void run(Graph graph, boolean withIterations) {
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        List<Node> seenNodes = new ArrayList<Node>();
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
                visit(s, colour, seenNodes, pred, graph, withIterations);

                // forest
                if (withIterations) {
                    System.out.println("Forest " + Integer.toString(forestNum));
                    GraphUtil.printForest(graph, pred, visitInPrevForest);
                    forestNum++;
                }
            }
        }

        // if (!withIterations) print(q, colour, dist, pred, graph);
    }

    private static void visit(Node s, Map<Node, Colour> colour, List<Node> seenNodes, Map<Node, Node> pred,
            Graph graph, boolean withIterations) {
        colour.replace(s, Colour.GREY);
        seenNodes.add(s);

        int iterationNum = 1;
        while (hasGreys(colour)) {
            Node u = null;

            for (Node v : graph.getNodeSet()){
                if (colour.get(v).equals(Colour.GREY)){
                    u = v;
                    break;
                }
            }

            if (u == null){
                System.err.println("Error occurred");
            }

            Arc foundArc = null;
            for (Node v : graph.getNodeSet()) {
                Arc arc = graph.getArc(u, v);
                if (arc != null && colour.get(v) == Colour.WHITE) {
                    // exists
                    foundArc = arc;
                    break;
                }
            }

            if (foundArc != null) {
                Node v = graph.getNode(foundArc.getTail().getValue());
                colour.replace(v, Colour.GREY);
                pred.replace(v, u);
                seenNodes.add(v);
            } else {
                colour.replace(u, Colour.BLACK);
            }

            if (withIterations) {
                System.out.println("Iteration " + Integer.toString(iterationNum));
                System.out.println("Visiting " + u);
                print(seenNodes, colour, pred, graph);
                System.out.println();
                iterationNum++;
            }
        }
    }

    private static void print(List<Node> seenNodes, Map<Node, Colour> colour, Map<Node, Node> pred, Graph graph) {
        System.out.println("Seen Nodes: " + seenNodes);
        GraphUtil.printColour(colour, graph);
        GraphUtil.printPred(pred, graph);
    }

    private static boolean hasGreys(Map<Node, Colour> colour){
        for (Node n : colour.keySet()){
            if (colour.get(n).equals(Colour.GREY)){
                return true;
            }
        }

        return false;
    }

    public static void runT7Example(){
        Graph graph = new Graph();
        graph.addArc(0, 1);
        graph.addArc(0, 2);
        graph.addArc(0, 3);
        graph.addArc(2, 3);
        graph.addArc(2, 6);
        graph.addArc(2, 7);
        graph.addArc(2, 8);
        graph.addArc(2, 9);
        graph.addArc(3, 4);
        graph.addArc(3, 5);
        graph.addArc(5, 0);
        graph.addArc(6, 10);
        graph.addArc(6, 9);

        General.run(graph, true);
    }
}
