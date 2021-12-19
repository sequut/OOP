import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.Node;
import tree.Tree;

public class TreeTest {
    @Test
    public void addTest() throws Exception{
        Tree<Integer> tree = new Tree<>();
        Node<Integer> root = tree.addNode(1);
        Node<Integer> A = tree.addNode(1);
        Node<Integer> B = tree.addNode(2);

        Throwable thrown = Assertions.assertThrows(Exception.class, () -> tree.addNode(1));
        Assertions.assertEquals(thrown.getMessage(), "Node already exists");

        tree.addNode("1 2", 1);

        Throwable thrown_1 = Assertions.assertThrows(Exception.class,
                () -> tree.addNode("1 2", 1));
        Assertions.assertEquals(thrown_1.getMessage(), "Node already exists");

        tree.addNode("1 2 1", 1);

        Throwable thrown_2 = Assertions.assertThrows(Exception.class,
                () -> tree.addNode("1 2 35", 1));
        Assertions.assertEquals(thrown_2.getMessage(), "Wrong path");
    }

    /*
    when you delete you should not count root
    type your path without root
     */
    @Test
    public void deleteTest() throws Exception{
        Tree<Integer> tree = new Tree<>();
        Node<Integer> root = tree.addNode(1);
        Node<Integer> A = tree.addNode(1);
        Node<Integer> B = tree.addNode(2);

        tree.addNode("1 2", 1);
        tree.deleteWithPath("2 1");

        tree.deleteWithPath("1");
        Throwable thrown_1 = Assertions.assertThrows(Exception.class,
                () -> tree.deleteWithPath("1"));
        Assertions.assertEquals(thrown_1.getMessage(), "Wrong path");

        tree.deleteWithPath("");
        Throwable thrown_2 = Assertions.assertThrows(Exception.class,
                () -> tree.deleteWithPath(""));
        Assertions.assertEquals(thrown_2.getMessage(), "there is no root");
    }
}