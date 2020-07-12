package assignments;

import edu.princeton.cs.algs4.In;
import junit.framework.TestCase;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class SolverTest extends TestCase {
//    int[][] startingTiles = {{1, 2, 3, 4}, {8, 7, 6, 5}, {9, 10, 11, 12}, {15, 14, 13, 0}};
//    Board b = new Board(startingTiles);
//    Solver s = new Solver(b);
//    private Object Solver;
//    int[][] cycles = {{9, 14, 16, 13}, {10, 12}};  //two demensional jagged array

    public void generatePermutationsWorks() {
//        char[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
//        Board e = new Board(expected);
//        Solver s = new Solver(e);
//        assertEquals(s.GeneratePermutations(b), e);
    }

    public void testIsSolvable() {
    }

    public void testMoves() {
    }

    @BeforeAll
    static void beforeAllInit() {
        final File folder = new File("C:\\Users\\Azizam\\IdeaProjects\\EightPuzzle\\src\\ModifiedTests");
        final String destFolder = "C:\\Users\\Azizam\\IdeaProjects\\EightPuzzle\\src\\testresults";
        for (final File fileEntry : folder.listFiles()) {
            System.out.println("processing file: " + fileEntry.getName());
        }
    }

    @BeforeEach
    void beforeEach(File fileEntry) {
        In in = new In(fileEntry.getAbsolutePath());
        int n = in.readInt();
        int moves = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board b = new Board(tiles);
        Solver s = new Solver(b);
        testSolution(s, moves);
    }

    @AfterEach
    void saveTheSolution(String destFolder, String fileEntry) {
        String path = destFolder + fileEntry;
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @RepeatedTest(10)

    static void testSolution(Solver s, int moves) {
        int expected = moves;
        int actual = s.moves;
        assertEquals(s.moves(), moves);
    }

    public void testMain() {
    }

    @Test
    @Disabled
    public void canCretePermutationsOfBoard() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        int[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
//        b = new Board(expected);
//        Method method = Solver.class.getDeclaredMethod("applyCycles", Array.class, Array.class);
//        method.setAccessible(true);
        //int[][] actual = (int[][]) method.invoke(s, expected, cycles);
        //assertEquals(expected, actual);
    }

    @Test
    @Disabled
    public void applyCycleShouldWork() {
//        int[][] expected = {{1, 2, 3, 4}, {8, 7, 6, 5}, {0, 14, 12, 13}, {10, 15, 11, 9,}};
//        int[][] startingTiles = {{1, 2, 3, 4}, {8, 7, 6, 5}, {9, 10, 11, 12}, {15, 14, 13, 0}};
//        int[][] cycles = {{9, 14, 16, 13}, {10, 12}};  //two demensional jagged array
        //assertSame(expected, s.applyCycles(startingTiles, cycles));
    }
}
