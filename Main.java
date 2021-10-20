import models.arcs.Arc;
import models.graphs.Graph;
import models.nodes.Node;

public class Main{
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addArc(new Arc(new Node(1), new Node(2)));
        graph.addArc(new Arc(new Node(2), new Node(2)));
        graph.addArc(new Arc(new Node(3), new Node(2)));
        graph.addArc(new Arc(new Node(2), new Node(2)));
        graph.addArc(new Arc(new Node(4), new Node(2)));
        graph.printGraph();
    }
}