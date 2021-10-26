package comparators;

import java.util.Comparator;
import java.util.Map;

import models.Node;

public class NodeDistComparator implements Comparator<Node>{
    Map<Node, Integer> dist;

    public NodeDistComparator(Map<Node, Integer> dist){
        this.dist = dist;
    }

    @Override
    public int compare(Node n1, Node n2) {
        return dist.get(n1) - dist.get(n2);
    }
    
}
