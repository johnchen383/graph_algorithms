package models.arcs;

import models.nodes.Node;

public class Arc{
    private Node head;
    private Node tail;
    private int weight;

    public Arc(Node head, Node tail){
        this.head = head;
        this.tail = tail;
        this.weight = 1;
    }

    public Arc(Node head, Node tail, int weight){
        this.head = head;
        this.tail = tail;
        this.weight = weight;
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

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) return false;

        Arc otherObj = (Arc) obj;

        return this.head.equals(otherObj.getHead()) && this.tail.equals(otherObj.getTail());
    }

    public Integer getWeight(){
        return weight;
    }
    
}