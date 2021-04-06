package assignments;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class SolverTest {
    public Solver solver;
    private Board fInput;
    private int fExpected;
    //final static File folder = new File("C:\\Users\\Azizam\\IdeaProjects\\EightPuzzle\\src\\ModifiedTests");
    final static File folder = new File("/home/sansari/IdeaProjects/EightPuzzle/src/ModifiedTests");
    private static String destFile ;
    final static ArrayList<Object[]> filesList = new ArrayList<>();
    private static Object[] testInst;

    @Parameterized.Parameters(name = "{index}: Number of moves for [{2}]={1}")
    public static Iterable<Object[]> data() {
        String destFile = "";
        int counter = 0;
        for (final File fileEntry : folder.listFiles()) {
            destFile=fileEntry.getName();
            counter++;
            if (counter == 144) break;
            In in = new In(fileEntry.getAbsolutePath());
            int n = in.readInt();
            int moves = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    tiles[i][j] = in.readInt();
            Board b = new Board(tiles);
            testInst = new Object[]{b, moves, destFile};
            filesList.add(testInst);
        }
        return filesList;
//        return Arrays.asList(new Object[][]{{0, 0}, {1, 1}, {2, 1},
//                {3, 2}, {4, 3}, {5, 5}, {6, 8}});
    }


    public SolverTest(Board input, int expected, String destFile ){
        fInput = input;
        fExpected = expected;
        solver = new Solver(fInput);
        String destPath = "/home/sansari/IdeaProjects/EightPuzzle/src/TestResults/";
        try {
            File resultsFile = new File(destPath+destFile);
            if (resultsFile.createNewFile()){
                //StdOut.println("File created: "+ resultsFile.getName());
            } else {
                StdOut.println("File already exists.");
            }
        } catch (IOException e) {
            StdOut.println("An error occured.");
            e.printStackTrace();
        }

            try {
                FileWriter myWriter = new FileWriter(destPath+destFile);
                myWriter.write("Solved in :"+solver.moves+" moves");
                myWriter.write("\n\r");
                for (Board board:solver.solution()) {
                    myWriter.write(String.valueOf(board) + " hamming distance: "+board.hamming() + "Manhattan " +
                            "Distance"+ board.manhattan());
                }
                myWriter.close();
            } catch (IOException e) {
                StdOut.println("An error occurred.");
                e.printStackTrace();
            }


    }

    @Test
    public void test() {
        assertEquals(fExpected, solver.moves);
    }
}
