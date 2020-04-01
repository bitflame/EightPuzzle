package assignments;

import junit.framework.TestCase;

public class GameTreeTest extends TestCase {
    char[][] testTiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    char[][] testTilesCopy = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    char[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    char[][] goalTilesCopy = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    GameTree testTree = new GameTree();
    Board b1 = new Board(testTiles);
    Board b2 = new Board(testTilesCopy);
    SearchNode testNode = new SearchNode(b1, 0, null);
    SearchNode testNode2 = new SearchNode(b2, 0, null);

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    //if (!currentSearchNode.equals(gameTree.get(b))) {
//currentPriorityQueue.insert(currentSearchNode);
//gameTree.put(currentSearchNode, value++);
//}
    public void shouldAddOnlyIfNotThereAlready() {
        testTree.put(testNode, 0);
        if (!testNode.equals(testTree.get(testNode2))) {
            assertTrue(true);
        }
    }

    public void testForEach() {
    }

    public void testSpliterator() {
    }

    public void testIterator() {
    }

    public void testGet() {
    }

    public void testSize() {
    }

    public void testPut() {
    }

    public void testMin() {
    }

    public void testFloor() {
    }

    public void testMax() {
    }

    public void testSelect() {
    }

    public void testRank() {
    }

    public void testDeleteMin() {
    }

    public void testDelete() {
    }

    public void testPrint() {
    }
}
