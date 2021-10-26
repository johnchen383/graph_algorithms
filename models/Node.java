package models;

public class Node{
    private String value;
    private int priority;

    public Node(String value, int priority){
        this.value = value;
        this.priority = priority;
    }

    public Node(int value){
        this.value = Integer.toString(value);
        this.priority = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String toStringWithPriority(){
        return value + " [" + Integer.toString(priority) + "]";
    }

    public String getValue(){
        return value;
    }

    public int getPriority(){
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) return false;

        Node otherObj = (Node) obj;

        return this.value.equals(otherObj.getValue());
    }
}