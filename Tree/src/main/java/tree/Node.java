package tree;

import java.util.Iterator;
import java.util.List;

public class Node<Type> implements Iterable<Node<Type>>{

    private Node<Type> parent;
    private Type value;
    private List<Node<Type>> children;

    public Node(Type value){
        this.value = value;
    }

    public Node<Type> addChild(Type child){
        Node<Type> nodeChild = new Node<>(child);
        nodeChild.parent = this;
        this.children.add(nodeChild);
        return nodeChild;
    }

    public Node<Type> getParent(){
        return parent;
    }

    public List<Node<Type>> getChildren(){
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


    @Override
    public Iterator<Node<Type>> iterator() {
        NodeIterator<Type> iterable = new NodeIterator<Type>(this);
        return iterable;
    }
}
