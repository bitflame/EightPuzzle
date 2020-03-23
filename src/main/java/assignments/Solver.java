package assignments;

import java.util.ArrayList;

public class Solver {
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board
    public int moves() {
        return 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        ArrayList<Board> tempArray = new ArrayList<>();
        return tempArray;
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}
