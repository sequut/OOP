import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.Node;
import tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TreeTest{
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

    @Test
    public void bfs() throws Exception{
        Tree<Integer> tree = new Tree<>();
        Node<Integer> root = tree.addNode(45);
        Node<Integer> A = tree.addNode(1);
        Node<Integer> B = tree.addNode(2);
        Node<Integer> C = tree.addNode(3);

        tree.addNode("45 1", 4);

        Iterator<Integer> iterator = tree.BFS();

        ArrayList<Integer> check = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(45, 1, 2, 3, 4));

        iterator.forEachRemaining(check::add);

        Assertions.assertEquals(answer, check);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void dfs() throws Exception{
        Tree<Integer> tree = new Tree<>();
        Node<Integer> root = tree.addNode(57);
        Node<Integer> A = tree.addNode(1);
        Node<Integer> B = tree.addNode(2);

        tree.addNode("57 2", 4);

        ArrayList<Integer> check = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(57, 2, 4, 1));

        Iterator<Integer> iterator = tree.DFS();
        iterator.forEachRemaining(check::add);

        Assertions.assertEquals(answer, check);
        Assertions.assertFalse(iterator.hasNext());
    }
}