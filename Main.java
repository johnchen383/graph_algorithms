import APSP.DikstraAPSP;
import APSP.Floyd;
import SSSP.BellmanFord;
import SSSP.Dijkstra;
import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;

public class Main{
    private static Graph graph;

    public static void main(String[] args) {
        // Dijkstra1.runExample29_10();
        // BellmanFord.runExample31_2();
        // DikstraAPSP.runExample29_11();
        // constructGraph();
        Floyd.runExampleStats();
        // graph.printGraph();
        // graph.getNodeSetWithPriority();
        // graph.printGraph();
    }

    private static void constructGraph(){
        graph = new Graph();
        graph.addArc(2, 4, 6);
        graph.addArc(0, 1, 3);
        graph.addArc(0, 2, 1);
        graph.addArc(0, 3, 5);
        graph.addArc(2, 1, 1);
        graph.addArc(1, 4, 2);
        graph.addArc(3, -4, 1);
    }
}