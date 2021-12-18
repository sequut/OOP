import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.Node;
import tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {
    @Test
    public void Test() throws Exception{
        Tree<Integer> tree = new Tree<>();
        Node<Integer> root = tree.addNode(1);
        Node<Integer> hz = tree.addNode(1);
        tree.addNode("1 1", 2);
        //Assertions.assertThrows(Exception.class, () -> tree.addNode("1", 2));
    }

}