package assignments;

import junit.framework.TestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SolverTest extends TestCase {
    int[][] startingTiles = {{1, 2, 3, 4}, {8, 7, 6, 5}, {9, 10, 11, 12}, {15, 14, 13, 0}};
    Board b = new Board(startingTiles);
    Solver s = new Solver();
    private Object Solver;
    int[][] cycles = {{9, 14, 16, 13}, {10, 12}};  //two demensional jagged array

    public void generatePermutationsWorks() {
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

    @Test
    @Disabled
    public void canCretePermutationsOfBoard() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        int[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
        b = new Board(expected);
        Method method = Solver.class.getDeclaredMethod("applyCycles", Array.class, Array.class);
        method.setAccessible(true);
        int[][] actual = (int[][]) method.invoke(s, expected, cycles);
        assertEquals(expected, actual);
    }

    @Test
    @Disabled
    public void applyCycleShouldWork() {
        int[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
        int[][] startingTiles = {{1, 2, 3, 4}, {8, 7, 6, 5}, {9, 10, 11, 12}, {15, 14, 13, 0}};
        int[][] cycles = {{9, 14, 16, 13}, {10, 12}};  //two demensional jagged array
        //assertSame(expected, s.applyCycles(startingTiles, cycles));
    }
}
