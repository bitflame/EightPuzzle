package assignments;

import edu.princeton.cs.algs4.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class SolverTest {
    public Solver solver;
    private Board fInput;
    private int fExpected;
    //final static File folder = new File("C:\\Users\\Azizam\\IdeaProjects\\EightPuzzle\\src\\ModifiedTests");
    final static File folder = new File("/home/sansari/IdeaProjects/EightPuzzle/src/ModifiedTests");
    final static String destFolder = "/home/sansari/IdeaProjects/EightPuzzle/src/testresults";
    final static ArrayList<Object[]> filesList = new ArrayList<>();
    private static Object[] testInst;

    @Parameterized.Parameters(name = "{index}: Number of moves for [{0}]={1}")
    public static Iterable<Object[]> data() {
        String path = "";
        int counter = 0;
        for (final File fileEntry : folder.listFiles()) {
            //System.out.println("processing file: " + fileEntry.getName())
            counter++;
            if (counter == 20) break;
            path = destFolder + fileEntry;
            In in = new In(fileEntry.getAbsolutePath());
            int n = in.readInt();
            int moves = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    tiles[i][j] = in.readInt();
            Board b = new Board(tiles);
            testInst = new Object[]{b, moves};
            filesList.add(testInst);
        }
        return filesList;
//        return Arrays.asList(new Object[][]{{0, 0}, {1, 1}, {2, 1},
//                {3, 2}, {4, 3}, {5, 5}, {6, 8}});
    }


    public SolverTest(Board input, int expected) {
        fInput = input;
        fExpected = expected;
        solver = new Solver(fInput);
    }

    @Test
    public void test() {
        assertEquals(fExpected, solver.moves);
    }
}
