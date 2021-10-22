package APSP;

import java.util.HashMap;
import java.util.Map;

import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;
import utils.GraphUtil;

public class Floyd {
    public static void run(Graph graph){
        run(graph, false);
    }

    public static void run(Graph graph, boolean showIterations){
        graph.sortWithPriority();

        Map<Node, Integer> mapping = new HashMap<Node, Integer>();
        int[][] dist = new int[graph.getOrder()][graph.getOrder()];

        int i = 0;
        for (Node u : graph.getNodeSet()){
            mapping.put(u, i);
            i++;
        }

        for (Node u : graph.getNodeSet()){
            for (Node v : graph.getNodeSet()){
                if (u.equals(v)){
                    dist[mapping.get(u)][mapping.get(v)] = 0;
                    continue;
                }

                Arc arc = graph.getArc(u, v);
                if (arc == null){
                    dist[mapping.get(u)][mapping.get(v)] = Integer.MAX_VALUE;
                } else {
                    dist[mapping.get(u)][mapping.get(v)] = arc.getWeight();
                }
            }
        }

        int numIterations = 0;
        if (showIterations) System.out.println("Iteration " + numIterations);
        if (showIterations) GraphUtil.printDistanceMatrix(dist, mapping);

        for (Node x : graph.getNodeSet()){
            numIterations++;
            if (showIterations) System.out.println("Iteration " + numIterations);
            if (showIterations) System.out.println("x = " + x);

            for (Node u : graph.getNodeSet()){
                for (Node v : graph.getNodeSet()){
                    if (dist[mapping.get(u)][mapping.get(x)] == Integer.MAX_VALUE || dist[mapping.get(x)][mapping.get(v)] == Integer.MAX_VALUE){
                        continue;
                    }

                    int calcDist = dist[mapping.get(u)][mapping.get(x)] + dist[mapping.get(x)][mapping.get(v)];

                    if (calcDist < dist[mapping.get(u)][mapping.get(v)]){
                        dist[mapping.get(u)][mapping.get(v)] = calcDist;
                    }
                }
            }
            if (showIterations) GraphUtil.printDistanceMatrix(dist);
        }

        if (!showIterations) GraphUtil.printDistanceMatrix(dist);
    }

    public static void runExampleDiagram(){
        Graph graph = new Graph();
        graph.addArc(0, 1, 3);
        graph.addArc(1, 0, 2);
        graph.addArc(1, 3, 2);
        graph.addArc(3, 1, -2);
        graph.addArc(3, 4, -3);
        graph.addArc(2, 4, 6);
        graph.addArc(0, 2, -1);
        graph.addArc(2, 1, 1);
        graph.addArc(2, 3, 4);
        graph.addArc(3, 2, 2);

        Floyd.run(graph, true);
    }
}
