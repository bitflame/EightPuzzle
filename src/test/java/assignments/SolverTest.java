package assignments;

import junit.framework.TestCase;

public class SolverTest extends TestCase {
    char[][] testTiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    char[][] testTilesCopy = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    char[][] goalTiles = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    char[][] goalTilesCopy = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    char[][] twinTest = {{0, 1}, {2, 3}};
    char[][] twinResponse = {{0, 3}, {2, 1}};
    Board testBoard = new Board(testTiles);
    Board testBoardCopy = new Board(testTilesCopy);
    Board goalBoard = new Board(goalTiles);
    Solver s = new Solver(testBoard);

    public void testIsSolvable() {
    }

    public void testMoves() {
    }

    public void testSolution() {
    }

    public void testMain() {
    }
}
