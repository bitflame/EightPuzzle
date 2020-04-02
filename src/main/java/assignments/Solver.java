package assignments;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private boolean solvable;
    private Board solution;
    private int moves;

    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("The Board object is empty.");
        }
        //if (this.isSolvable()) { // How to figure out if the board is solvable or not?
        SearchNode initial = new SearchNode(initialBoard, 0, null);
        //StdOut.println("This table is in solver: " + initialBoard);

        MinPQ<SearchNode> currentPriorityQueue = new MinPQ<>();
        moves = 0;
        currentPriorityQueue.insert(initial);
        GameTree gameTree = new GameTree();
        gameTree.put(initial, null);
        StdOut.println("Adding " + initial.GetCurrentBoard().toString() + "with hamming: " + initial.GetCurrentBoard().hamming() +
                "with manhattan: " + initial.GetCurrentBoard().manhattan());
        SearchNode minSearchNode = currentPriorityQueue.delMin();
        SearchNode prevSearchNode = initial;
        SearchNode currentSearchNode;
        // Need one more condition below. If it is not solvable, it should not cause an infinite loop
        while (!minSearchNode.GetCurrentBoard().isGoal() && minSearchNode.GetCurrentBoard().neighbors().iterator().hasNext()) {
            moves++;
            for (Board b : minSearchNode.GetCurrentBoard().neighbors()) {
                //currentSearchNode = new SearchNode(b, moves, minSearchNode);
                // add search node to priority queue and GameTree only if it is not already in the GameTree
                if (gameTree.get(new SearchNode(b, moves, minSearchNode)) == null) {
                    StdOut.println("Adding " + b.toString() + "with hamming: " + b.hamming() + "with manhattan: " + b.manhattan());
                    currentPriorityQueue.insert(new SearchNode(b, moves, minSearchNode));
                    gameTree.put(new SearchNode(b, moves, minSearchNode), null);
                }
            }
            minSearchNode = currentPriorityQueue.delMin();
            StdOut.println("Taking " + minSearchNode.GetCurrentBoard().toString() + "out of the search node." + "with hamming: " +
                    minSearchNode.GetCurrentBoard().hamming() + "with manhattan: " + minSearchNode.GetCurrentBoard().manhattan());

        }
        //moves = gameTree.rank(minSearchNode);
        if (minSearchNode.GetCurrentBoard().isGoal()) {
            solvable = true;
            solution = minSearchNode.GetCurrentBoard();
        }
        //}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return this.moves;
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
