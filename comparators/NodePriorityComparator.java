package comparators;

import java.util.Comparator;

import models.nodes.Node;

public class NodePriorityComparator implements Comparator<Node>{
    @Override
    public int compare(Node node1, Node node2) {
        return node1.getPriority() - node2.getPriority();
    }
}
