package tree;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Tree<Type> {
    private Node<Type> root;

    public Tree(){
        root = null;
    }
    public Tree(Node<Type> node){
        root = node;
    }

    public Node<Type> getRoot(){
        return root;
    }

    public Node<Type> addNode(Type value){
        if (root == null)
            root = new Node<>(value);
        else
            root.addChild(value);

        return root;
    }

    public void addNode(String path, Type value) throws Exception{
        Node<Type> node = new Node<>(value);
        String[] allpath = getPath(path);

        if (root == null && allpath.length > 0)
            throw new Exception("Wrong path");
        else if (root == null)
            root = new Node<>(value);
        else
            addWithPath(allpath, node, root);
    }

    private void addWithPath(String[] path, Node<Type> node, Node<Type> parent) throws Exception{
        if (parent.getLevel() == path.length - 1){
            if (parent.getChildren() == null)
                throw new Exception("Node already exists");
            else if (parent.getChildren().contains(node))
                throw new Exception("Node already exists");
            else{
                parent.addChild(node.getValue());
                return;
            }
        }

        ArrayList<Node<Type>> children = parent.getChildren();
        for (Node<Type> child : children) {
            if (child.getValue().toString().equals(path[parent.getLevel()]))
                addWithPath(path, node, child);
        }
        throw new Exception("Wrong path");
    }

    private String[] getPath(String path){
        return path.length() == 0 ? new String[0] : path.split(" ");
    }
}