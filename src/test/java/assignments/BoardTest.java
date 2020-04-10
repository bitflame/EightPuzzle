package assignments;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
    char[][] testTiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    char[][] testTilesCopy = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    char[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    char[][] goalTilesCopy = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    char[][] twinTest = {{0, 1}, {2, 3}};
    char[][] twinResponse = {{0, 3}, {2, 1}};
    Board testBoard = new Board(testTiles);
    Board testBoardCopy = new Board(testTilesCopy);
    Board goalBoard = new Board(goalTiles);

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
        assertEquals(5, testBoard.hamming());
    }

    public void testManhattan() {
        assertEquals(10, testBoard.manhattan());
    }

    public void testIsGoal() {
        //assertFalse(testBoard.isGoal());
        assertTrue(goalBoard.isGoal());
    }

    public void testTestEquals() {
        assertEquals(testBoard, testBoardCopy);
    }

    public void testNeighbors() {
    }

    public void testTwin() {
        Board newBoard = new Board(twinTest);
        Board newBoardTwin = new Board(twinResponse);
        assertEquals(newBoardTwin, newBoard.twin());
    }

    public void testIterator() {
    }
}
