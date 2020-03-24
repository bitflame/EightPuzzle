package assignments;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {
    // find a solution to the initial board (using the A* algorithm)
    private MinPQ<Board> currentPriorityQueue = new MinPQ<>();

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("The Board object is empty.");
        }
        currentPriorityQueue.insert(initial);
        Board minSearchNode = currentPriorityQueue.delMin();
        while (!minSearchNode.isGoal()) {
            for (Board b : minSearchNode.neighbors()) {
                currentPriorityQueue.insert(b);
            }
            minSearchNode = currentPriorityQueue.delMin();
        }
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

    private class SearchNode {
        Board currentBoard;
        int movesCount;
        Board prevBoard;

        public SearchNode(Board currentBoard, int movesCount, Board prevBoard) {
            this.currentBoard = currentBoard;
            this.movesCount = movesCount;
            this.prevBoard = prevBoard;
        }
    }
}
