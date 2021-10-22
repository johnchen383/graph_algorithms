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

        for (Node x : graph.getNodeSet()){
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
        }

        GraphUtil.printDistanceMatrix(dist, mapping);
    }
}
