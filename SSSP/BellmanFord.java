package SSSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;
import utils.GraphUtil;

/**
 * Assumes: weighted, directed, no negative weight cycles
 */
public class BellmanFord {
    public static void run(Graph graph, Node s){
        run(graph, s, false);
    }

    public static void run(Graph graph, Node s, boolean showIterations){
        graph.sortWithPriority();
        Integer numIterations = null;
        if (showIterations) numIterations = 0;

        Map<Node, Integer> dist = new HashMap<Node, Integer>(graph.getOrder());
        Map<Node, Node> pred = new HashMap<Node, Node>(graph.getOrder());

        //initialise
        for (Node u: graph.getNodeSet()){
            dist.put(u, Integer.MAX_VALUE);
            pred.put(u, null);
        }

        dist.replace(s, 0);

        if (showIterations) System.out.println("Iteration " + numIterations);
        GraphUtil.printDistance(dist, graph);

        for (int i = 0; i < graph.getOrder() - 1; i++){
            if (showIterations) numIterations++;
            if (showIterations) System.out.println("Iteration " + numIterations);

            for (Node x : graph.getNodeSet()){
                for (Node v : graph.getNodeSet()){
                    int calDist;

                    Arc arc = graph.getArc(x, v);
                    if (arc == null){
                        calDist = Integer.MAX_VALUE;
                    } else {
                        if (dist.get(x).equals(Integer.MAX_VALUE)) continue;
                        
                        calDist = dist.get(x) + arc.getWeight();
                    }
                    
                    if (calDist < dist.get(v)){
                        dist.replace(v, calDist);
                        pred.replace(v, x);
                    }
                }
            }

            GraphUtil.printDistance(dist, graph);
        }
        
        GraphUtil.printFinalDistance(dist, s, graph);

        detectNegativeWeightCycles(graph, s, dist, pred);

    }

    //assumes reachable from s
    private static void detectNegativeWeightCycles(Graph graph, Node s, Map<Node, Integer> dist, Map<Node, Node> pred){
        //detect negative weight cycles
        //one more iteration
        Node x = null;
        boolean found = false;
        for (Node u : graph.getNodeSet()){
            for (Node v : graph.getNodeSet()){
                int calDist;

                Arc arc = graph.getArc(u, v);
                if (arc == null){
                    calDist = Integer.MAX_VALUE;
                } else {
                    if (dist.get(u).equals(Integer.MAX_VALUE)) continue;
                    
                    calDist = dist.get(u) + arc.getWeight();
                }
                
                if (calDist < dist.get(v) && !found){
                    x = v;
                    found = true;
                    break;
                }
            }
        }

        if (x == null){
            System.out.println("No negative weight cycles reachable from s equals " + s);
            return;
        }

        List<Node> list = new ArrayList<Node>();
        list.add(x);
        Node next = pred.get(x);

        while (!list.contains(next)){
            list.add(next);
            next = pred.get(next);
        }

        list.add(next);

        Node start = list.get(list.size() - 1);
        System.out.print("Negative weight cycle: " + start + " ");
        for (int i = list.size() - 2; i >= 0; i--){
            if (list.get(i).equals(start)){
                break;
            }

            System.out.print(list.get(i) + " ");
        }

        System.out.print(start + " ");
        System.out.println();
    }

    public static void runExample31_1(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(4, 3, -2);
        graph.addArc(4, 2, 1);
        graph.addArc(4, 1, 3);
        graph.addArc(3, 2, 2);
        graph.addArc(2, 1, 2);
        graph.addArc(1, 2, 1);
        graph.addArc(2, 0, 3);
        graph.addArc(1, 0, 0);

        BellmanFord.run(graph, graph.getNode(Integer.toString(4)), true);
    }

    public static void runExample31_2(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(0, 5, 4);
        graph.addArc(0, 4, -1);
        graph.addArc(4, 5, 1);
        graph.addArc(4, 3, 5);
        graph.addArc(5, 3, 2);
        graph.addArc(3, 1, 1);
        graph.addArc(4, 2, 2);
        graph.addArc(4, 1, 7);
        graph.addArc(1, 2, 2);

        BellmanFord.run(graph, graph.getNode(Integer.toString(0)), true);
    }

    public static void runNegativeWeightCycle(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(1, 2, 3);
        graph.addArc(2, 3, 2);
        graph.addArc(3, 1, -10);
        graph.addArc(2, 4, 4);
        graph.addArc(4, 0, 1);
        graph.addArc(0, 5, 5);
        graph.addArc(5, 6, 3);
        graph.addArc(6, 0, 2);

        BellmanFord.run(graph, graph.getNode(Integer.toString(1)), true);
    }
}
