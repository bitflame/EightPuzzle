package assignments;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private boolean solvable;
    private Board solution;
    private int moves;
    private int value;

    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("The Board object is empty.");
        }
        //if (this.isSolvable()) { // How to figure out if the board is solvable or not?
        SearchNode initial = new SearchNode(initialBoard, 0, null);
        //StdOut.println("This table is in solver: " + initialBoard);
//FIXME: Work on the following tomorrow: 1) My understanding is that we are to implement two priority queues side by side.
// The first queue is to attempt to solve the puzzle with the input board. The other queue is to attempt to solve the
// puzzle with a twin of the input board. Run through the steps of the A* algorithm on each of the two priority queues
// in step until one of them results in a goal board. If the "twin" queue finds a solution, then the original puzzle is
// unsolvable.
// here is someone else's ccomment: 2) Agree with the answers by LJ. Just want to add that you need to implement some sort
// of pointer to track the parent of each search node. When you find a solution, you can follow the parents all the way
// back to the beginning. The parent pointers form sort of a reversed tree structure (no child connections, but a parent
// connection), and are independent of the priority queue structure.
// One more; 3) I am using a counter right now for the number of moves needed to solve the puzzle, while I need to walk bakc
// once I find a solution. I may run into an issue with how I am doing it right now. Here is how others have done it: In
// my solution, I didn't create a stack until the puzzle was solved. At that point, you can take the current search node
// and walk backwards through each node's "previous" pointer.
// Here is the site with more information on this:
// https://www.coursera.org/learn/algorithms-part1/programming/iqOQi/8-puzzle/discussions/threads/mFCOD541EeaURQ7PXsSdbA
// 4) Also make sure to only use the approved apis even in the main()
// from: https://www.coursera.org/learn/algorithms-part1/programming/iqOQi/8-puzzle/discussions/threads/2KU36p3VEeaJsxK88fW92A
// 5) Also this one:  i am only using Manhattan distance, not priority which should include the number of moves up to here
// also.
        MinPQ<SearchNode> currentPriorityQueue = new MinPQ<>();
        moves = 0;

        currentPriorityQueue.insert(initial);
        GameTree gameTree = new GameTree();
        gameTree.put(initial, ++value);
        StdOut.println("Adding " + initial.GetCurrentBoard().toString() + "with hamming: " + initial.GetCurrentBoard().hamming() +
                "with manhattan: " + initial.GetCurrentBoard().manhattan());
        SearchNode minSearchNode = currentPriorityQueue.delMin();
        SearchNode prevSearchNode = initial;
        SearchNode currentSearchNode;
        // Need one more condition below. If it is not solvable, it should not cause an infinite loop
        while (!minSearchNode.GetCurrentBoard().isGoal() && minSearchNode.GetCurrentBoard().neighbors().iterator().hasNext()
                && moves < (initialBoard.dimension() * initialBoard.dimension())) {
            moves++;
            for (Board b : minSearchNode.GetCurrentBoard().neighbors()) {
                // add search node to priority queue and GameTree only if it is not already in the GameTree
                // In the text you update the value. Wonder if I should do it here also
                if (gameTree.get(new SearchNode(b, moves, minSearchNode)) == null) {
                    StdOut.println("Adding " + b.toString() + "with hamming: " + b.hamming() + "with manhattan: " + b.manhattan());
                    currentPriorityQueue.insert(new SearchNode(b, moves, minSearchNode));
                    gameTree.put(new SearchNode(b, moves, minSearchNode), ++value);
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
        } else {
            solvable = false;
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
        char[][] testTiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        char[][] testTilesCopy = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        char[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        char[][] goalTilesCopy = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        char[][] testTiles2 = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        GameTree testTree = new GameTree();
        Board b1 = new Board(testTiles);
        Board b2 = new Board(testTilesCopy);
        Board b3 = new Board(testTiles2);
        Board b4 = new Board(goalTiles);
        SearchNode testNode = new SearchNode(b1, 0, null);
        SearchNode testNode2 = new SearchNode(b2, 0, testNode);
        SearchNode testNode3 = new SearchNode(b3, 0, testNode2);
        SearchNode testNode4 = new SearchNode(b4, 0, testNode3);
        testTree.put(testNode, 1);
        testTree.put(testNode2, 2);
        testTree.put(testNode4, 4);

        if (testTree.get(testNode3) == null) {
            testTree.put(testNode3, 3);
        }
        for (int i = 0; i < testTree.size(); i++) {
            SearchNode s = (SearchNode) testTree.select(i);
            StdOut.println(s.GetCurrentBoard());
        }
    }
}
