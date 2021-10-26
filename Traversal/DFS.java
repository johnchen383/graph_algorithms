package Traversal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

public class DFS {
    public static void run(Graph graph, boolean withIterations) {
        Stack<Node> stack = new Stack<Node>();
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Integer> seen = new HashMap<Node, Integer>(graph.getOrder());
        Map<Node, Integer> done = new HashMap<Node, Integer>(graph.getOrder());
        Map<Node, Node> pred = new HashMap<Node, Node>(graph.getOrder());

        graph.sortWithPriority();
        for (Node u : graph.getNodeSet()) {
            colour.put(u, Colour.WHITE);
            pred.put(u, null);
            seen.put(u, null);
            done.put(u, null);
        }

        Integer time = 0;
        int forestNum = 1;
        HashSet<Node> visitInPrevForest = new HashSet<Node>();
        
        for (Node s : graph.getNodeSet()) {
            if (colour.get(s) == Colour.WHITE) {
                visit(s, stack, colour, seen, done, pred, graph, withIterations, time);

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

    private static void visit(Node s, Stack<Node> stack, Map<Node, Colour> colour, Map<Node, Integer> seen,
            Map<Node, Integer> done, Map<Node, Node> pred, Graph graph, boolean withIterations, Integer time) {
        colour.replace(s, Colour.GREY);
        seen.replace(s, time);
        time++;
        stack.add(s);

        int iterationNum = 1;

        while (!stack.isEmpty()) {
            Node u = stack.peek();

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
                seen.replace(v, time);
                time++;
                stack.add(v);
            } else {
                stack.pop();
                colour.replace(u, Colour.BLACK);
                done.replace(u, time);
                time++;
                
            }

            if (withIterations) {
                System.out.println("Iteration " + Integer.toString(iterationNum));
                System.out.println("Visiting " + u);
                print(stack, colour, seen, done, pred, graph);
                System.out.println("Time: " + Integer.toString(time));
                System.out.println();
                iterationNum++;
            }
        }
    }

    private static void print(Stack<Node> stack, Map<Node, Colour> colour, Map<Node, Integer> seen, Map<Node, Integer> done, Map<Node, Node> pred,
            Graph graph) {
        System.out.println("Stack: " + stack);
        GraphUtil.printColour(colour, graph);
        GraphUtil.printPred(pred, graph);
        GraphUtil.printSeen(seen, graph);
        GraphUtil.printDone(done, graph);
    }

    public static void runExample24_4() {
        Graph graph = new Graph();
        graph.addArc(0, 3);
        graph.addArc(0, 1);
        graph.addArc(1, 3);
        graph.addArc(1, 2);
        graph.addArc(2, 1);
        graph.addArc(4, 2);
        graph.addArc(3, 4);

        DFS.run(graph, true);
    }
}
