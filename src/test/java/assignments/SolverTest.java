package assignments;

import junit.framework.TestCase;

public class SolverTest extends TestCase {
    char[][] startingTiles = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    Board b = new Board(startingTiles);

    public void GeneratePermutationsWorks() {
        //char[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
        //Board e = new Board(expected);
        //Solver s = new Solver(e);
        //assertEquals(s.GeneratePermutations(b), e);
    }

    public void testIsSolvable() {
    }

    public void testMoves() {
    }

    public void testSolution() {
    }

    public void testMain() {
    }

    public void CanFindObjectInGameTree() {
        char[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
        b = new Board(expected);

    }
}
