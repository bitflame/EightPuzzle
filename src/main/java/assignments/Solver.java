package assignments;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private boolean solvable;
    private Board solution;

    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("The Board object is empty.");
        }
        if (this.isSolvable()) { // How to figure out if the board is solvable or not?
            SearchNode initial = new SearchNode(initialBoard, 0, null);
            StdOut.println("This table is in solver: " + initialBoard);
            MinPQ<SearchNode> currentPriorityQueue = new MinPQ<>();
            int value = 0;
            currentPriorityQueue.insert(initial);
            GameTree gameTree = new GameTree();
            gameTree.put(initial, value++);
            gameTree.print(initial);
            SearchNode minSearchNode = currentPriorityQueue.delMin();
            SearchNode currentSearchNode;
            // Need one more condition below. If it is not solvable, it should not cause an infinite loop
            while (!minSearchNode.GetCurrentBoard().isGoal()) {
                for (Board b : minSearchNode.GetCurrentBoard().neighbors()) {
                    currentSearchNode = new SearchNode(b, minSearchNode.GetMovesCount() + 1, minSearchNode);
                    currentPriorityQueue.insert(currentSearchNode);
                    gameTree.put(currentSearchNode, value++);
                }
                minSearchNode = currentPriorityQueue.delMin();
            }
            if (minSearchNode.GetCurrentBoard().isGoal()) {
                solvable = true;
                solution = minSearchNode.GetCurrentBoard();
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {

        ArrayList<Board> tempArray = new ArrayList<>();
        tempArray.add(solution);
        return tempArray;
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}
