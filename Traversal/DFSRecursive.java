package Traversal;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import enums.Colour;
import models.Arc;
import models.Graph;
import models.Node;
import utils.GraphUtil;

/**
 * Note: coming down from the recursion stack is delayed by one for some reason
 */
public class DFSRecursive {
    public static int time = 0;

    public static void run(Graph graph, boolean withIterations) {
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Integer> seen = new HashMap<Node, Integer>(graph.getOrder());
        Map<Node, Integer> done = new HashMap<Node, Integer>(graph.getOrder());
        Map<Node, Node> pred = new HashMap<Node, Node>(graph.getOrder());
        Stack<Node> stack = new Stack<Node>();

        graph.sortWithPriority();
        for (Node u : graph.getNodeSet()) {
            colour.put(u, Colour.WHITE);
            pred.put(u, null);
            seen.put(u, null);
            done.put(u, null);
        }

        int forestNum = 1;
        for (Node s : graph.getNodeSet()) {
            if (colour.get(s) == Colour.WHITE) {
                if (withIterations)
                    System.out.println("Forest " + Integer.toString(forestNum));
                stack.add(s);
                visit(s, stack, colour, seen, done, pred, graph, withIterations);
            }
        }

        // if (!withIterations) print(q, colour, dist, pred, graph);
    }

    private static void visit(Node s, Stack<Node> stack, Map<Node, Colour> colour, Map<Node, Integer> seen,
            Map<Node, Integer> done, Map<Node, Node> pred, Graph graph, boolean withIterations) {
        colour.replace(s, Colour.GREY);
        seen.replace(s, time);
        time++;

        if (withIterations) {
            System.out.println("Iteration at time " + Integer.toString(time));
            System.out.println("Recursive visit " + s);
            print(stack, colour, seen, done, pred, graph);
            System.out.println();
        }

        for (Node v : graph.getNodeSet()) {
            Arc arc = graph.getArc(s, v);
            if (arc != null) {
                // exists
                if (colour.get(v) == Colour.WHITE) {
                    colour.replace(v, Colour.GREY);
                    stack.add(v);
                    visit(v, stack, colour, seen, done, pred, graph, withIterations);
                }
            }
        }

        stack.remove(s);
        colour.replace(s, Colour.BLACK);
        done.replace(s, time);
        time++;

        if (withIterations) {
            System.out.println("Iteration at time " + Integer.toString(time));
            System.out.println("Recursive visit " + s);
            print(stack, colour, seen, done, pred, graph);
            System.out.println();
        }
    }

    private static void print(Stack<Node> stack, Map<Node, Colour> colour, Map<Node, Integer> seen,
            Map<Node, Integer> done, Map<Node, Node> pred, Graph graph) {
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

        DFSRecursive.run(graph, true);
    }
}
