package SSSP;

import java.util.HashMap;
import java.util.Map;

import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;

/**
 * Assumes: weighted, directed, no negative weight cycles
 */
public class BellmanFord {
    public static void run(Graph graph, Node s){
        run(graph, s, false);
    }

    public static void run(Graph graph, Node s, boolean showIterations){
        Integer numIterations = null;
        if (showIterations) numIterations = 0;

        Map<Node, Integer> dist = new HashMap<Node, Integer>(graph.getOrder());

        //initialise
        for (Node u: graph.getNodeSet()){
            dist.put(u, Integer.MAX_VALUE);
        }

        dist.replace(s, 0);

        for (int i = 0; i < graph.getOrder() - 1; i++){
            for (Node x : graph.getNodeSetWithPriority()){
                for (Node v : graph.getNodeSetWithPriority()){
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
        }
        
    }
}
