package SSSP;

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

        
        
    }
}
