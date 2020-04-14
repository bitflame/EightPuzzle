package assignments;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver {
    private boolean solvable;
    private Board solution;
    private int moves;
    private int twinMoves;
    private int value;
    private int twinValue;
    private ArrayList<Board> solutionBoardList = new ArrayList<>();

    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("The Board object is empty.");
        }
        //if (this.isSolvable()) { // How to figure out if the board is solvable or not?
        SearchNode initialSearchNode = new SearchNode(initialBoard, 0, null);

        Board currentTwinBoard = initialBoard.twin();
        SearchNode initialTwinSearchNode = new SearchNode(currentTwinBoard, 0, null);
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
// 4) Also make sure to only use the approved apis even in the main()
// 5) Also this one:  i am only using Manhattan distance, not priority which should include the number of moves up to here
// also.6) From the faqs: You will compute the priority function in Solver by calling hamming() or manhattan() and adding to
// it the number of moves.
        MinPQ<SearchNode> currentPriorityQueueTwin = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                if (o1.GetPriority() > o2.GetPriority()) return 1;
                else if (o2.GetPriority() > o1.GetPriority()) return -1;
                return 0;
            }
        });
        MinPQ<SearchNode> currentPriorityQueue = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                if (o1.GetPriority() > o2.GetPriority()) return 1;
                else if (o2.GetPriority() > o1.GetPriority()) return -1;
                else return 0;
            }
        });

        moves = 0;
        twinMoves = 0;
        currentPriorityQueue.insert(initialSearchNode);
//        StdOut.println("Adding the first Board with hamming distance of: " + initialBoard.hamming() +
//                " and manhattan distance of: " + initialBoard.manhattan() + " To Priority Queue");
        currentPriorityQueueTwin.insert(initialTwinSearchNode);
//        StdOut.println("Adding the first Twin Board with hamming distance of: " + currentTwinBoard.hamming() +
//                " and manhattan distance of: " + currentTwinBoard.manhattan() + " To Twin Priority Queue");
        GameTree gameTree = new GameTree();
        GameTree gameTreeTwin = new GameTree();
        gameTree.put(initialSearchNode, ++value);
        gameTreeTwin.put(initialTwinSearchNode, ++twinValue);
//        StdOut.println("Adding " + initialSearchNode.GetCurrentBoard().toString() + " with hamming: " + initialSearchNode.GetCurrentBoard().hamming() +
//                " with manhattan: " + initialSearchNode.GetCurrentBoard().manhattan());
        SearchNode minSearchNode = currentPriorityQueue.delMin();
        SearchNode minSearchNodeTwin = currentPriorityQueueTwin.delMin();
//        StdOut.println("Adding " + initialTwinSearchNode.GetCurrentBoard().toString() + " with hamming: " + initialSearchNode.GetCurrentBoard().hamming() +
//                " with manhattan: " + initialSearchNode.GetCurrentBoard().manhattan() + " To Twin Priority Queue");
        int i = 0;
        while (!(minSearchNode.GetCurrentBoard().isGoal())) {
            i++;
            if (minSearchNodeTwin.GetCurrentBoard().isGoal()) {
                solvable = false;
                break;
            }
            // I tested to see if I can check hamming and manhattan distance to stop adding boards and improve performance
//            if ((minSearchNode.GetCurrentBoard().hamming() % 2) == 1) {
//                StdOut.println("Manhattan distance of board is odd");
//                break;
//            }
            for (Board bt : minSearchNodeTwin.GetCurrentBoard().neighbors()) {
                // add a search node to the twin priority queue and twin GameTree only if it is not already in the GameTree
                // or the priority queue
                // I tested to see if checking manhattan or hamming distance improves the performance b/c goal has
                // manhattan and hamming of zero i.e. even

                SearchNode temp = new SearchNode(bt, minSearchNodeTwin.GetMovesCount() + 1, minSearchNodeTwin);
                if (minSearchNodeTwin.GetPrevSearchNode() == null) {
                    currentPriorityQueueTwin.insert(temp);
                    gameTreeTwin.put(temp, twinValue);
                } else if (!temp.GetCurrentBoard().equals(minSearchNodeTwin.GetPrevSearchNode().GetCurrentBoard())) {
                    //StdOut.println("Adding to Twin Priority Queue.");
//                    StdOut.println("Adding neighbor Board with " + bt.toString() + " and hamming distance of: " + bt.hamming() +
//                            " and manhattan distance of: " + bt.manhattan() + " To Twin Priority Queue");
//                    StdOut.println("Adding neighbor Board with hamming distance of: " + s.GetCurrentBoard().hamming() +
//                            " and manhattan distance of: " + s.GetCurrentBoard().manhattan() + " Current moves count: "
//                            + twinMoves + " To Twin Priority Queue");
                    // if you remove the item, you can use the same index next time to get another item

                    currentPriorityQueueTwin.insert(temp);
                    // I do not think I need to know how many moves it takes for the twin to solve so just using value
                    gameTreeTwin.put(temp, twinValue);
                } // else StdOut.println("Twin game tree already has this node.");
            }
            //currentPriorityQueueTwin.insert((SearchNode) gameTreeTwin.min());
            for (Board b : minSearchNode.GetCurrentBoard().neighbors()) {
                // I tested to see if checking manhattan or hamming distance improves the performance b/c goal has
                // manhattan and hamming of zero i.e. even
                //What if I reset the minpriority queue every time? I just need the minimum of the neighbors in each round
                // add search node to priority queue and GameTree only if it is not already in the GameTree
                // In the text you update the value. Wonder if I should do it here also
                SearchNode temp1 = new SearchNode(b, minSearchNode.GetMovesCount() + 1, minSearchNode);
                if (minSearchNode.GetPrevSearchNode() == null) {
                    currentPriorityQueue.insert(temp1);
                    gameTree.put(temp1, ++value);
                } else if (!temp1.GetCurrentBoard().equals(minSearchNode.GetPrevSearchNode().GetCurrentBoard())) {
                    //StdOut.println("Adding to minimum priority queue.");
//                    StdOut.println("Adding neighbor Board : " + b.toString() + " with hamming distance of :  " + b.hamming() +
//                            " and manhattan distance of:  " + b.manhattan() + " To priority queue");
//                    StdOut.println("Adding neighbor Board with hamming distance of :  " + b.hamming() +
//                            " and manhattan distance of:  " + b.manhattan() + " Current moves count: " + moves + " To priority queue");
                    currentPriorityQueue.insert(temp1);
                    gameTree.put(temp1, ++value);
                } //else StdOut.println("Game Tree already has this node.");
            }
            //currentPriorityQueue.insert((SearchNode) gameTree.min());
//            StdOut.println("Here are all the search nodes in the Priority Queue.");
//            for (SearchNode s : currentPriorityQueue) {
//                StdOut.println(s.GetCurrentBoard() + " Priority: " + s.GetPriority());
//            }
            if (!currentPriorityQueue.isEmpty()) {
                //prevSearchNode = minSearchNode;
                // print everything that is in the MinPQ and verify the lowest priority node is being removed
                minSearchNode = currentPriorityQueue.delMin();
//                StdOut.println("Taking " + minSearchNode.GetCurrentBoard().toString() + "out of the minimum search node with hamming: " +
//                        minSearchNode.GetCurrentBoard().hamming() + "with manhattan: " + minSearchNode.GetCurrentBoard().manhattan());
            } else StdOut.println("Priority queue is empty.");
//            StdOut.println("Here are all the search nodes in the Twin Priority Queue.");
//            for (SearchNode s : currentPriorityQueueTwin) {
//                StdOut.println(s.GetCurrentBoard());
//            }
            if (!currentPriorityQueueTwin.isEmpty()) {
                //prevTwinSearchNode = minSearchNode;
                minSearchNodeTwin = currentPriorityQueueTwin.delMin();
//                StdOut.println("Taking " + minSearchNodeTwin.GetCurrentBoard().toString() + " twin minimum search node with hamming: " +
//                        minSearchNode.GetCurrentBoard().hamming() + "with manhattan: " + minSearchNode.GetCurrentBoard().manhattan() +
//                        "out of Twin Priority Queue");
            } else StdOut.println("Twin priority queue is empty.");
        }
        //moves = gameTree.rank(minSearchNode);
        if (minSearchNode.GetCurrentBoard().isGoal()) {
            solvable = true;
            solution = minSearchNode.GetCurrentBoard();
            moves = minSearchNode.GetMovesCount();
            while (!minSearchNode.GetCurrentBoard().equals(initialBoard)) {
                solutionBoardList.add(minSearchNode.GetCurrentBoard());
                minSearchNode = minSearchNode.GetPrevSearchNode();
            }

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
        if (solvable) {
            return this.moves;
        } else return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (solvable) {
            Collections.reverse(solutionBoardList);
            ArrayList<Board> tempArray = new ArrayList<>(solutionBoardList);
            return tempArray;
        } else
            return null;
    }

    public static class SearchNode implements Comparable<SearchNode> {
        private final Board currentBoard;
        private int manhattan = 0;
        private int hamming = 0;
        private final int numOfMoves;
        private final SearchNode prevSearchNode;

        public SearchNode(Board b, int m, SearchNode prev) {
            currentBoard = b;
            numOfMoves = m;
            prevSearchNode = prev;
            manhattan = currentBoard.manhattan();
            this.manhattan = currentBoard.manhattan();
            this.hamming = currentBoard.hamming();
        }

        public Board GetCurrentBoard() {
            return currentBoard;
        }

        public int GetMovesCount() {
            return numOfMoves;
        }

        public SearchNode GetPrevSearchNode() {
            return prevSearchNode;
        }

        public int GetPriority() {
            //TODO SAME HERE check for each case separately i.e. manhattan, if they are equal, then hamming, if they are still
            // equal then number of moves
            return manhattan + hamming + numOfMoves;
        }

        @Override
        public int compareTo(SearchNode o) {
            //TODO check for each case separately i.e. manhattan, if they are equal, then hamming, if they are still
            // equal then number of moves
            if ((this.manhattan + this.hamming + this.numOfMoves) > (o.manhattan + o.hamming + o.numOfMoves))
                return 1;
            if ((this.manhattan + this.hamming + this.numOfMoves) < (o.manhattan + o.hamming + o.numOfMoves))
                return -1;
            //if (o.GetCurrentBoard().manhattan() > this.GetCurrentBoard().manhattan()) return -1;
            return 0;
        }

//    @Override
//    public int compare(SearchNode o1, SearchNode o2) {
//        if ((o1.GetCurrentBoard().manhattan() + o1.GetMovesCount()) > (o2.GetCurrentBoard().manhattan() + o2.GetMovesCount()))
//            return 1;
//        if ((o1.GetCurrentBoard().manhattan() + o1.GetMovesCount()) < (this.GetCurrentBoard().manhattan() + this.GetMovesCount()))
//            return -1;
//        //if (o.GetCurrentBoard().manhattan() > this.GetCurrentBoard().manhattan()) return -1;
//        return 0;
//    }


    }

    // test client (see below)
    public static void main(String[] args) {
        //TODO: Build tables: initial, goal, and change 1 digit at a time and see how they are printed out when you print the tree
//        char[][] testTiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//        char[][] testTilesCopy = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//        char[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
//        char[][] goalTilesCopy = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
//        char[][] testTiles2 = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        // 3x3-10 test
//        char[][] one = {{0, 4, 1}, {5, 3, 2}, {7, 8, 6}};
//        char[][] two = {{4, 0, 1}, {5, 3, 2}, {7, 8, 6}};
//        char[][] three = {{4, 1, 0}, {5, 3, 2}, {7, 8, 6}};
//        char[][] four = {{4, 1, 2}, {5, 3, 0}, {7, 8, 6}};
//        char[][] five = {{4, 1, 2}, {5, 0, 3}, {7, 8, 6}};
//        char[][] six = {{4, 1, 2}, {0, 5, 3}, {7, 8, 6}};
//        char[][] seven = {{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
//        char[][] eight = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
//        char[][] nine = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
//        char[][] ten = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
//        char[][] eleven = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
//        char[][] twelve = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
//        char[][] thirteen = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        char[][] one = {{4, 1, 2}, {3, 0, 6}, {5, 7, 8}};  //updated this one to 3x3-12 table
        char[][] two = {{4, 0, 1}, {5, 3, 2}, {7, 8, 6}};
        char[][] three = {{4, 1, 0}, {5, 3, 2}, {7, 8, 6}};
        char[][] four = {{4, 1, 2}, {5, 3, 0}, {7, 8, 6}};
        char[][] five = {{4, 1, 2}, {5, 0, 3}, {7, 8, 6}};
        char[][] six = {{4, 1, 2}, {0, 5, 3}, {7, 8, 6}};
        char[][] seven = {{0, 1, 2}, {4, 5, 3}, {7, 8, 6}};
        char[][] eight = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
        char[][] nine = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        char[][] ten = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
        char[][] eleven = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        char[][] twelve = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        char[][] thirteen = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        GameTree testTree = new GameTree();
        Board b1 = new Board(one);
        Board b2 = new Board(two);
        Board b3 = new Board(three);
        Board b4 = new Board(four);
        Board b5 = new Board(five);
        Board b6 = new Board(six);
        Board b7 = new Board(seven);
        Board b8 = new Board(eight);
        Board b9 = new Board(nine);
        Board b10 = new Board(ten);
        Board b11 = new Board(eleven);
        Board b12 = new Board(twelve);
        Board b13 = new Board(thirteen);
        assert b13.isGoal();
//        SearchNode b1TestNode = new SearchNode(b1, 0, null);
//        SearchNode b2TestNode = new SearchNode(b2, 0, b1TestNode);
//        SearchNode b3TestNode = new SearchNode(b3, 0, b2TestNode);
//        SearchNode b4TestNode = new SearchNode(b4, 0, b3TestNode);
//        SearchNode b5TestNode = new SearchNode(b5, 0, b4TestNode);
//        SearchNode b6TestNode = new SearchNode(b6, 0, b5TestNode);
//        SearchNode b7TestNode = new SearchNode(b7, 0, b6TestNode);
//        SearchNode b8TestNode = new SearchNode(b8, 0, b7TestNode);
//        SearchNode b9TestNode = new SearchNode(b9, 0, b8TestNode);
//        SearchNode b10TestNode = new SearchNode(b10, 0, b9TestNode);
//        SearchNode b11TestNode = new SearchNode(b11, 0, b10TestNode);
//        SearchNode b12TestNode = new SearchNode(b12, 0, b11TestNode);
//        SearchNode b13TestNode = new SearchNode(b13, 0, b12TestNode);
        SearchNode b1TestNode = new SearchNode(b1, 0, null);
        SearchNode b2TestNode = new SearchNode(b2, 1, b1TestNode);
        SearchNode b3TestNode = new SearchNode(b3, 2, b2TestNode);
        SearchNode b4TestNode = new SearchNode(b4, 3, b3TestNode);
        SearchNode b5TestNode = new SearchNode(b5, 4, b4TestNode);
        SearchNode b6TestNode = new SearchNode(b6, 5, b5TestNode);
        SearchNode b7TestNode = new SearchNode(b7, 6, b6TestNode);
        SearchNode b8TestNode = new SearchNode(b8, 7, b7TestNode);
        SearchNode b9TestNode = new SearchNode(b9, 8, b8TestNode);
        SearchNode b10TestNode = new SearchNode(b10, 9, b9TestNode);
        SearchNode b11TestNode = new SearchNode(b11, 10, b10TestNode);
        SearchNode b12TestNode = new SearchNode(b12, 11, b11TestNode);
        SearchNode b13TestNode = new SearchNode(b13, 12, b12TestNode);
//Print the manhattan, hamming, and priority of the search nodes
        StdOut.println("Manhattan                 Hamming            Priority");
        StdOut.printf("%4s %25s  %18s\n", b1.manhattan(), b1.hamming(), b1TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b1.manhattan(), b1.hamming(), b1TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b2.manhattan(), b2.hamming(), b2TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b3.manhattan(), b3.hamming(), b3TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b4.manhattan(), b4.hamming(), b4TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b5.manhattan(), b5.hamming(), b5TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b6.manhattan(), b6.hamming(), b6TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b7.manhattan(), b7.hamming(), b7TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b8.manhattan(), b8.hamming(), b8TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b9.manhattan(), b9.hamming(), b9TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b10.manhattan(), b10.hamming(), b10TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b11.manhattan(), b11.hamming(), b11TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b12.manhattan(), b12.hamming(), b12TestNode.GetPriority());
        StdOut.printf("%4s %25s  %18s\n", b13.manhattan(), b13.hamming(), b13TestNode.GetPriority());
        testTree.put(b1TestNode, 0);
        testTree.put(b2TestNode, 1);
        testTree.put(b3TestNode, 2);
        testTree.put(b4TestNode, 3);
        testTree.put(b5TestNode, 4);
        testTree.put(b6TestNode, 5);
        testTree.put(b7TestNode, 6);
        testTree.put(b8TestNode, 7);
        testTree.put(b9TestNode, 8);
        testTree.put(b10TestNode, 9);
        testTree.put(b11TestNode, 10);
        testTree.put(b12TestNode, 11);
        testTree.put(b13TestNode, 12);
//        testTree.put(b1TestNode, 1);
//        testTree.put(b2TestNode, 1);
//        testTree.put(b3TestNode, 1);
//        testTree.put(b4TestNode, 1);
//        testTree.put(b5TestNode, 1);
//        testTree.put(b6TestNode, 1);
//        testTree.put(b7TestNode, 1);
//        testTree.put(b8TestNode, 1);
//        testTree.put(b9TestNode, 1);
//        testTree.put(b10TestNode, 1);
//        testTree.put(b11TestNode, 1);
//        testTree.put(b12TestNode, 1);
//        testTree.put(b13TestNode, 1);
        StdOut.println("size = " + testTree.size());
        StdOut.println("min  = " + testTree.min());
        StdOut.println("max  = " + testTree.max(b2TestNode));
        StdOut.println();
// get returns null if the key does not exist. The if test below actually checks the table inside the objects, and does
// not put the new object in the tree if the tables are the same which is what I want
        if (testTree.get(b2TestNode) == null) {
            testTree.put(b2TestNode, 3);
        }
        // This is how to print all the keys w/o the Iterator. Now I have the keys() and should be able to use it
        for (int i = 0; i < testTree.size(); i++) {
            SearchNode s = (SearchNode) testTree.select(i);
            StdOut.println(s.GetCurrentBoard() + " Priority: " + s.GetPriority());
        }
        StdOut.println("Printing using foreach loop: ");
        for (Object s : testTree.keys()) {
            SearchNode temp = (SearchNode) s;
            StdOut.println(temp.GetCurrentBoard() + " Priority: " + temp.GetPriority());
        }
    }
}
