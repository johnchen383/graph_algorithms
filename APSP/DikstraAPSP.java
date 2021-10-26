package APSP;

import SSSP.Dijkstra;
import models.Graph;
import models.Node;

/**
 * Assumes: weighted, directed, no negative weights
 */
public class DikstraAPSP {
    public static void run(Graph graph){
        run(graph, false);
    }

    public static void run(Graph graph, boolean showIterations){
        graph.sortWithPriority();
        for (Node s : graph.getNodeSet()){
            Dijkstra.run(graph, s, showIterations);
        }
    }

    public static void runExample29_11(){
        //all shown for iterations
        Graph graph = new Graph();
        graph.addArc(0, 1, 1);
        graph.addArc(2, 1, 2);
        graph.addArc(1, 3, 2);
        graph.addArc(3, 0, 2);
        graph.addArc(0, 2, 4);
        graph.addArc(2, 3, 5);

        DikstraAPSP.run(graph);
    }
}
