package models.graphs;

import java.util.ArrayList;

import models.arcs.Arc;
import models.nodes.Node;

public class Graph{
    ArrayList<Arc> arcset;
    ArrayList<Node> nodeset;

    public Graph(){
        arcset = new ArrayList<Arc>();
        nodeset = new ArrayList<Node>();
    }

    public void addArc(Arc arc){
        arcset.add(arc);

        if (!nodeset.contains(arc.getHead())){
            nodeset.add(arc.getHead());
        }

        if (!nodeset.contains(arc.getTail())){
            nodeset.add(arc.getTail());
        }
    }

    public void addEdge(Arc arc){
        addArc(arc);
        addArc(new Arc(arc.getTail(), arc.getHead()));
    }

    public void addNode(Node node){
        nodeset.add(node);
    }

    public void printArcSet(){
        printArcSet(false);
    }

    public void printArcSet(boolean hasWeight){
        for (Arc arc : arcset){
            if (!hasWeight){
                System.out.print(arc + " ");
            } else {
                System.out.print(arc.toStringWithWeights() + " ");
            }
        }
        System.out.println();
    }

    public void printNodeSet(){
        printNodeSet(false);
    }

    public void printNodeSet(boolean hasPriority){
        for (Node node : nodeset){
            if (!hasPriority){
                System.out.print(node + " ");
            } else {
                System.out.print(node.toStringWithPriority() + " ");
            }
        }
        System.out.println();
    }

    public void printGraph(){
        System.out.println("Node Set:");
        printNodeSet();
        System.out.println("Arc Set:");
        printArcSet();
    }
}