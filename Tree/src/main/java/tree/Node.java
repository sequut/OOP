package tree;

import java.util.ArrayList;

public class Node <Type>{
    private Node<Type> parent;
    private Type value;
    private ArrayList<Node<Type>> children = new ArrayList<>();

    public Node(Type value){
        this.value = value;
    }

    public Node<Type> getParent(){
        return parent;
    }

    public void addChild(Type child){
        Node<Type> nodeChild = new Node<>(child);
        nodeChild.parent = this;
        this.children.add(nodeChild);
    }

    public ArrayList<Node<Type>> getChildren(){
        return children;
    }

    public Type getValue(){
        return value;
    }

    public boolean isRoot(){
        return parent == null;
    }

    public boolean isLeaf(){
        return children.isEmpty();
    }

    public int getLevel(){
        return this.isRoot() ? 0 : (parent.getLevel() + 1);
    }

}