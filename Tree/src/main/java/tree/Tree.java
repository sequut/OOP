package tree;

import javax.print.attribute.standard.MediaSize;
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

    public Node<Type> addNode(Type value) throws Exception{
        if (root == null){
            root = new Node<>(value);
            return root;
        }

        ArrayList<Node<Type>> children = root.getChildren();
        for (Node<Type> child : children) {
            if (child.getValue().equals(value))
                throw new Exception("Node already exists");
        }
        root.addChild(value);

        return root.getChildren().get(root.getChildren().size() - 1);
    }

    public void addNode(String path, Type value) throws Exception{
        String[] allpath = getPath(path);

        if (root == null && allpath.length > 0)
            throw new Exception("Wrong path");
        else if (root == null)
            root = new Node<>(value);
        else
            addWithPath(allpath, value, root);
    }

    private void addWithPath(String[] path, Type node, Node<Type> parent) throws Exception{
        if (parent.getLevel() == path.length - 1){
            if (parent.getChildren().size() == 0){
                parent.addChild(node);
                return;
            }

            ArrayList<Node<Type>> children = parent.getChildren();
            for (Node<Type> child : children) {
                if (child.getValue().equals(node))
                    throw new Exception("Node already exists");
            }
            parent.addChild(node);
        }
        else{
            int founded = 0;
            ArrayList<Node<Type>> children = parent.getChildren();
            for (Node<Type> child : children) {
                if (child.getValue().toString().equals(path[child.getLevel()])){
                    founded = 1;
                    addWithPath(path, node, child);
                }
            }
            if (founded == 0)
                throw new Exception("Wrong path");
        }
    }

    private String[] getPath(String path){
        return path.length() == 0 ? new String[0] : path.split(" ");
    }

    public void deleteWithPath(String path) throws Exception {
        String[] allpath = getPath(path);
        if (allpath.length == 0){
            if (root == null)
                throw new Exception("there is no root");
            root = null;
            return;
        }
        delete(allpath, root);
    }

    private void delete(String[] path, Node<Type> parent) throws Exception{
        if (parent.getLevel() == path.length - 1){
            ArrayList<Node<Type>> children = parent.getChildren();
            for (int i = 0; i < children.size(); i++)
                if (children.get(i).getValue().toString().equals(path[path.length - 1])){
                    parent.deleteChild(i);
                    return;
                }
            throw new Exception("Wrong path");
        }
        else{
            int founded = 0;
            ArrayList<Node<Type>> children = parent.getChildren();
            for (Node<Type> child : children) {
                if (child.getValue().toString().equals(path[parent.getLevel()])){
                    founded = 1;
                    delete(path, child);
                }
            }
            if (founded == 0)
                throw new Exception("Wrong path");
        }
    }
}