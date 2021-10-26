package MST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;
import utils.GraphUtil;

public class Kruskal {
    public static void run(Graph graph) {
        run(graph, false);
    }

    public static void run(Graph graph, boolean showIterations) {
        graph.sortWithWeight();
        // HashMap<Node, ArrayList<Node>> sets = new HashMap<Node, ArrayList<Node>>();
        HashSet<HashSet<Node>> sets = new HashSet<HashSet<Node>>();

        List<Arc> E = new ArrayList<Arc>();

        for (Node n : graph.getNodeSet()) {
            // ArrayList<Node> set = new ArrayList<Node>();
            // set.add(n);
            // sets.put(n, set);
            HashSet<Node> set = new HashSet<Node>();
            set.add(n);
            sets.add(set);
        }

        int iterationNum = 0;

        if (showIterations) {
            System.out.println("Iteration " + iterationNum);
            GraphUtil.printSetOfArcs(E);
            System.out.println("Sets: " + sets);
            System.out.println();
        }

        for (Arc arc : graph.getArcSet()) {
            Node head = graph.getNode(arc.getHead().getValue());
            Node tail = graph.getNode(arc.getTail().getValue());

            if (!inSameSet(head, tail, sets)) {
                E.add(arc);

                if (showIterations) {
                    iterationNum++;
                    System.out.println("Iteration " + iterationNum);
                    GraphUtil.printSetOfArcs(E);
                    System.out.println("Sets: " + sets);
                    System.out.println();
                }
            }
        }

        if (!showIterations)
            GraphUtil.printSetOfArcs(E);

        GraphUtil.printMSTWeight(E);
    }

    public static void runKruskalDiagram(){
        Graph graph = new Graph();
        graph.addEdge('a', 'b', 1);
        graph.addEdge('a', 'd', 8);
        graph.addEdge('a', 'h', 2);
        graph.addEdge('d', 'h', 5);
        graph.addEdge('d', 'b', 10);
        graph.addEdge('h', 'i', 9);
        graph.addEdge('d', 'i', 1);
        graph.addEdge('d', 'f', 8);
        graph.addEdge('f', 'b', 5);
        graph.addEdge('i', 'f', 6);
        graph.addEdge('c', 'b', 13);
        graph.addEdge('c', 'f', 7);
        graph.addEdge('g', 'c', 4);
        graph.addEdge('f', 'g', 13);
        graph.addEdge('g', 'j', 2);
        graph.addEdge('f', 'j', 15);
        graph.addEdge('i', 'j', 12);

        Kruskal.run(graph, true);
    }

    private static boolean inSameSet(Node head, Node tail, HashSet<HashSet<Node>> sets){
        for (HashSet<Node> set : sets){
            if (set.contains(head) && set.contains(tail)){
                return true;
            }
        }

        //not in the same set

        //get tail set
        HashSet<Node> tailSet = new HashSet<Node>();
        for (HashSet<Node> set : sets){
            if (set.contains(tail)){
                tailSet.addAll(set);
                set.removeAll(set);
                break;
                // System.out.println(set);
            }
        }

        //add to headset
        for (HashSet<Node> set : sets){
            if (set.contains(head)){
                set.addAll(tailSet);
                break;
            }
        }

        return false;
    }

}
