package tree;

import java.util.Iterator;

public interface iterator {
    Iterator iterator() throws Exception;

    Iterator DFS() throws Exception;

    Iterator BFS();
}
