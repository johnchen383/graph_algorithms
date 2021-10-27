package comparators;

import java.util.Comparator;
import java.util.Map;

import models.Node;

public class NodePQComparator implements Comparator<Node>{
    Map<Node, Integer> priorities;

    public NodePQComparator(Map<Node, Integer> priorities){
        this.priorities = priorities;
    }

    @Override
    public int compare(Node n1, Node n2) {
        return priorities.get(n1) - priorities.get(n2);
    }
    
}
