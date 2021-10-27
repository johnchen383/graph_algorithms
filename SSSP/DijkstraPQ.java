package SSSP;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import comparators.NodePQComparator;
import enums.Colour;
import models.Graph;
import models.Node;

/**
 * Assumes: weighted, directed, no negative weights
 */
public class DijkstraPQ {
    public static void run(Graph graph, Node s){
        Map<Node, Colour> colour = new HashMap<Node, Colour>(graph.getOrder());
        Map<Node, Integer> dist = new HashMap<Node, Integer>(graph.getOrder());
        PriorityQueue<Node> pq = new PriorityQueue<Node>(graph.getOrder(), new NodePQComparator(dist));

        //initialise
        for (Node u: graph.getNodeSet()){
            colour.put(u, Colour.WHITE);
        }
        colour.replace(s, Colour.GREY);
        pq.add(s);

        while (!pq.isEmpty()){
            // Node u = pq.peek();
            // int t1 = pq.
            //TODO: incomplete
        }
    }
}
