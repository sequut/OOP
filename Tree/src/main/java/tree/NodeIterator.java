package tree;

import java.util.Iterator;

public class NodeIterator<Type>{
    private final Node<Type> treeNode;

    public NodeIterator(Node<Type> node){
        this.treeNode = node;
    }

    //@Override
    //public boolean hasNext(){
    //    return (treeNode.getChildren().size() > 0);
    //}
}