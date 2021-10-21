import SSSP.Dijkstra1;
import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;

public class Main{
    private static Graph graph;

    public static void main(String[] args) {
        // Dijkstra1.runExample29_10();
        constructGraph();
        graph.printGraph();
        graph.getNodeSetWithPriority();
        graph.printGraph();
    }

    private static void constructGraph(){
        graph = new Graph();
        graph.addArc(new Arc(new Node(2), new Node(4), 6));
        graph.addArc(new Arc(new Node(0), new Node(1), 3));
        graph.addArc(new Arc(new Node(0), new Node(2), 1));
        graph.addArc(new Arc(new Node(0), new Node(3), 5));
        graph.addArc(new Arc(new Node(2), new Node(1), 1));
        graph.addArc(new Arc(new Node(1), new Node(4), 2));
        graph.addArc(new Arc(new Node(3), new Node(4), 1));
    }
}