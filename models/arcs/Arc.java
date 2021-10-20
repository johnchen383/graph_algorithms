package models.arcs;

import models.nodes.Node;

public class Arc{
    private Node head;
    private Node tail;
    private int weight;

    public Arc(Node head, Node tail){
        this.head = head;
        this.tail = tail;
    }

    public Arc(Node head, Node tail, int weight){
        this.head = head;
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "(" + head + "," + tail + ")";
    }

    public String toStringWithWeights(){
        return "(" + head + "," + tail + ") [" + weight +"]";
    }

    public Node getHead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }
}