package SSSP;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import enums.Colour;
import models.graphs.Graph;
import models.nodes.Node;

public class Dijkstra {
    public static void run(Graph graph, Node s){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Integer> dist = new HashMap<Node, Integer>(graph.getOrder());

        //initialise
        for (Node u: graph.getNodeSet()){
            colour.put(u, Colour.WHITE);
        }
        colour.replace(s, Colour.GREY);
        // pq.add(s, 0);

        while (!pq.isEmpty()){
            Node u = pq.peek();
            // int t1 = pq.
        }
    }
}
