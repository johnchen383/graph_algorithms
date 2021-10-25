package models.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.ArcWeightComparator;
import comparators.NodePriorityComparator;
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

    public void addArc(int head, int tail, int weight){
        Arc arc = new Arc(new Node(head), new Node(tail), weight);
        addArc(arc);
    }

    public void addEdge(int head, int tail, int weight){
        addArc(head, tail, weight);
        addArc(tail, head, weight);
    }

    public void addEdge(char head, char tail, int weight){
        addArc(head, tail, weight);
        addArc(tail, head, weight);
    }

    public void addArc(char head, char tail, int weight){
        Arc arc = new Arc(new Node(String.valueOf(head), (int) head), new Node(String.valueOf(tail), (int) tail), weight);
        addArc(arc);
    }

    public void addArc(String head, int headPriority, String tail, int tailPriority, int weight){
        Arc arc = new Arc(new Node(head, headPriority), new Node(tail, tailPriority), weight);
        addArc(arc);
    }

    public void addEdge(Arc arc){
        addArc(arc);
        addArc(new Arc(arc.getTail(), arc.getHead(), arc.getWeight()));
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

    public int getOrder(){
        return nodeset.size();
    }

    public int getSize(){
        return arcset.size();
    }

    public List<Node> getNodeSet(){
        return nodeset;
    }

    public void sortWithPriority(){
        Collections.sort(nodeset, new NodePriorityComparator());
    }

    public void sortWithWeight(){
        Collections.sort(arcset, new ArcWeightComparator());
    }

    public List<Arc> getArcSet(){
        return arcset;
    }

    public Arc getArc(Node h, Node t){
        for (Arc arc : arcset){
            if (arc.getHead().equals(h) && arc.getTail().equals(t)){
                return arc;
            }
        }

        return null;
    }

    public Node getNode(String val){
        for (Node node : nodeset){
            if (node.getValue().equals(val)){
                return node;
            }
        }

        return null;
    }
}