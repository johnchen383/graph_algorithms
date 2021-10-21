package SSSP;

import java.util.HashMap;
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

        //initialise
        for (Node u: graph.getNodeSet()){
            dist.put(u, Integer.MAX_VALUE);
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
                    }
                }
            }

            GraphUtil.printDistance(dist, graph);
        }
        
        GraphUtil.printFinalDistance(dist, s, graph);
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
}
