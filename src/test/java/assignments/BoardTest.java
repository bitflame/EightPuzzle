package assignments;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
    int[][] testTiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    int[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    Board testBoard = new Board(testTiles);

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testForEach() {
    }

    public void testSpliterator() {
    }

    public void testTestToString() {
    }

    public void testDimension() {
    }

    public void testHamming() {
    }

    public void testManhattan() {
        assertEquals(10, testBoard.manhattan());
    }

    public void testIsGoal() {
    }

    public void testTestEquals() {
    }

    public void testNeighbors() {
    }

    public void testTwin() {
    }

    public void testIterator() {
    }
}
