package assignments;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressFBWarnings({"UUF_UNUSED_FIELD", "UUF_UNUSED_FIELD", "UUF_UNUSED_FIELD", "UUF_UNUSED_FIELD", "UUF_UNUSED_FIELD"})
public class Solver {
    private boolean solvable;
    private Board solution;
    private int moves;
    private int twinMoves;
    private int value;
    private int twinValue;
    private ArrayList<Board> solutionBoardList = new ArrayList<>();
    private int boardDimension;
    private int inversionCount;

    //@SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
//    private List<SearchNode> GeneratePermutations(int boardDimensions, Integer[] currentCycle, SearchNode goalNode) {
//        char[][] permutedTiles = new char[boardDimensions][boardDimensions];
//        char[][] parentTiles = new char[boardDimensions][boardDimensions];
//        int[] boardArray = new int[boardDimensions * boardDimensions];
//        int[] boardArrayCopy = new int[boardDimensions * boardDimensions];
//        List<SearchNode> currentPermutations = new ArrayList<>();
    // Copy the contents of the board into a one dimensional array
    // Change this method so you start with a board with 0 at i and moving it to j. Doing this for all the cycles
    // creates all the permutations that get you to the goal
//        for (int i = 0; i < boardArray.length; i++) {
//            boardArray[i] = i + 1;
//            boardArrayCopy[i] = i + 1;
//        }
//        boardArray[currentCycle[0]] = 0;
//        boardArrayCopy[currentCycle[0]] = 0;
//        permutedTiles = ConvertOneDemensionalArrayToTwoDemensional(boardArray, boardDimensions);
//        Board tempBoard = new Board(permutedTiles);
//        // Create permutations using the current cycle currentCycle.length number of times. You should get n! permutations
//        // of the length of the cycle. Test this later; perhaps by converting the larger cycle to transpositions
//        SearchNode parentS = new SearchNode(tempBoard, 0, null);
//        currentPermutations.add(parentS);
//        for (int i = 0; i < currentCycle.length - 1; i++) {
//            int temp = boardArray[currentCycle[currentCycle.length - 1]];
//            for (int j = currentCycle.length - 1; j > 0; j--) { // shift the contents of board array at cycle address one
//                // slot over
//                boardArray[currentCycle[j]] = boardArray[currentCycle[j - 1]];
//            }
//            boardArray[currentCycle[0]] = temp;
//            // now move all the boardArray to a two dimensional board
//            int currentCounter = 0;
//            for (int g = 0; g < boardDimensions; g++) {
//                for (int h = 0; h < boardDimensions; h++) {
//                    permutedTiles[g][h] = (char) boardArray[currentCounter];
//                    parentTiles[g][h] = (char) boardArrayCopy[currentCounter];
//                    currentCounter++;
//                }
//            }
//            tempBoard = new Board(permutedTiles);
//            Board tempBoardParent = new Board(parentTiles);
//            parentS = new SearchNode(tempBoardParent, parentS.numOfMoves + 1, parentS.prevSearchNode);
//            SearchNode s = new SearchNode(tempBoard, parentS.numOfMoves + 1, parentS);
//            currentPermutations.add(s);
//            // now move all the boardArray to a two dimensional board
//            for (int g = 0; g < (boardDimensions * boardDimensions); g++) {
//                boardArrayCopy[g] = boardArray[g];
//            }
//        }
//        return currentPermutations;
//    }
//
//    private char[][] ConvertOneDemensionalArrayToTwoDemensional(int[] oneD, int twoDemLeng) {
//        int currentCounter = 0;
//        char[][] twoDemArr = new char[twoDemLeng][twoDemLeng];
//        for (int g = 0; g < twoDemLeng - 1; g++) {
//            for (int h = 0; h < twoDemLeng - 1; h++) {
//                twoDemArr[g][h] = (char) oneD[currentCounter];
//                twoDemArr[g][h] = (char) oneD[currentCounter];
//                currentCounter++;
//            }
//        }
//        return twoDemArr;
//    }

    @SuppressFBWarnings("URF_UNREAD_FIELD")
    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("The Board object is empty.");
        }

        //if (this.isSolvable()) { // How to figure out if the board is solvable or not?
        solvable = initialBoard.isSolvable();
        if (solvable == false) return;
        SearchNode initialSearchNode = new SearchNode(initialBoard, 0, null);
//        StdOut.print("Here is the first node: " + initialSearchNode.GetCurrentBoard() + " With Priority of: " +
//                initialSearchNode.GetPriority() + " Manhattan value of: " + initialSearchNode.manhattan +
//                " Hamming distance of: " + initialSearchNode.hamming + " Number of moves: " + initialSearchNode.numOfMoves);
        //Board currentTwinBoard = initialBoard.twin();
        //SearchNode initialTwinSearchNode = new SearchNode(currentTwinBoard, 0, null);
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
//        MinPQ<SearchNode> currentPriorityQueueTwin = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
//            @Override
//            public int compare(SearchNode o1, SearchNode o2) {
//                if (o1.GetPriority() > o2.GetPriority()) return 1;
//                else if (o2.GetPriority() > o1.GetPriority()) return -1;
//                else if (o1.GetPriority() == o2.GetPriority()) {
//                    if (o1.hamming > o2.hamming) return 1;
//                    else if (o2.hamming > o1.hamming) return -1;
//                }
//                return 0;
//            }
//        });
        MinPQ<SearchNode> currentPriorityQueue = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                if (o1.GetPriority() > o2.GetPriority()) return 1;
                else if (o2.GetPriority() > o1.GetPriority()) return -1;
                return 0;
            }
        });
        moves = 0;
//        twinMoves = 0;
//        StdOut.println();
//        StdOut.println(initialBoard.toString() + "Adding the first Board with hamming distance of: " + initialBoard.hamming() +
//                " and manhattan distance of: " + initialBoard.manhattan() + " To Priority Queue");
        currentPriorityQueue.insert(initialSearchNode);

        //currentPriorityQueueTwin.insert(initialTwinSearchNode);
//        StdOut.println("Adding the first Twin Board with hamming distance of: " + currentTwinBoard.hamming() +
//                " and manhattan distance of: " + currentTwinBoard.manhattan() + " To Twin Priority Queue");
//        GameTree gameTree = new GameTree();
// Create permutations for nine cycles
        // convert the address of each cycle to conventional two dimensional array addressing
//        int index = 1;
//        char[][] goal = new char[initialBoard.dimension()][initialBoard.dimension()];
//        for (int i = 0; i <= initialBoard.dimension() - 1; i++) {
//            for (int j = 0; j <= initialBoard.dimension() - 1; j++) {
//                goal[i][j] = (char) index;
//                index++;
//            }
//        }
        // I may have to remove the goal board from the BST, I do not see why I should keep it. In fact, it may cause
        // problems for me.
//        goal[initialBoard.dimension() - 1][initialBoard.dimension() - 1] = 0;
        //Board gBoard = new Board(goal);
        //SearchNode gNode = new SearchNode(gBoard, gBoard.manhattan(), null);
        //gameTree.put(gNode, gBoard.manhattan());
//        List<Integer[]> cycles = new ArrayList<>();
//        Integer[] cycleOne = {0, 1, 2, 3, 7, 6, 5};// {1, 2, 3, 4, 5, 6, 7}
//        //Integer[] cycleOne = {0, 1, 2, 3, 4, 5, 6};
//        cycles.add(cycleOne);
//        Integer[] cycleTwo = {1, 2, 3, 7, 6};// {2, 3, 4, 5, 6}
        //Integer[] cycleTwo = {1, 2, 3, 7, 6};
//        cycles.add(cycleTwo);
//        Integer[] cycleThree = {2, 3, 7};// {3, 4, 5}
        //Integer[] cycleThree = {2, 3, 4};
//        cycles.add(cycleThree);
//        Integer[] cycleFour = {7, 6, 5, 4, 8, 9, 10};// {5, 6, 7, 8, 9, 10, 11}
        //Integer[] cycleFour = {4, 5, 6, 7, 8, 9, 10};
//        cycles.add(cycleFour);
//        Integer[] cycleFive = {6, 5, 4, 8, 9}; // {6, 7, 8, 9, 10}
//        cycles.add(cycleFive);
//        Integer[] cycleSix = {5, 6, 8}; // {7, 8, 9}
//        cycles.add(cycleSix);
//        Integer[] cycleSeven = {8, 9, 10, 11, 15, 14, 13};//{9, 10, 11, 12, 13, 14, 15 }
//        cycles.add(cycleSeven);
//        Integer[] cycleEight = {9, 10, 11, 15, 14};//{10, 11 ,12 ,13 ,14}
//        cycles.add(cycleEight);
//        Integer[] cycleNine = {10, 11, 15};//{11, 12, 13}
//        cycles.add(cycleNine);
//        for (Integer[] currentCycle : cycles) {
//            for (SearchNode s : GeneratePermutations(initialBoard.dimension(), currentCycle, gNode)) {
//                gameTree.put(s, s.GetPriority());
//                for (Object o : gameTree.keys()) {
//                    SearchNode ss = (SearchNode) o;
//                    StdOut.println(ss.GetCurrentBoard());
//                }
//            }
//        }
// create a new search node - still need to figure out what the previous node would be and number of moves
        //SearchNode newSearchNode = new SearchNode(initialBoard, 1, null);
        // add the node to the GameTree
        //gameTree.put(initialSearchNode, initialSearchNode.GetPriority());
// Test to see if testBoard is in GameTree
//        char[][] expected = {{5, 4, 0, 1}, {6, 7, 3, 2}, {8, 9, 10, 11}, {12, 13, 14, 15}};
//        Board expectedB = new Board(expected);
//        for (Object s : gameTree.keys()) {
//            SearchNode temp = (SearchNode) s;
//            if (temp.GetCurrentBoard().equals(expectedB)) {
//                StdOut.println("Found the test key in Game Tree Binary Search Tree.");
//            }
//        }
//        SearchNode expectedSearchNode = new SearchNode(expectedB, expectedB.manhattan(), null);
//        if (gameTree.get(expectedSearchNode) != null) {
//            StdOut.println("Found it with get also.");
//        }
        //gameTree.put(initialSearchNode, initialSearchNode.GetPriority());
        //gameTreeTwin.put(initialTwinSearchNode, ++twinValue);
//        StdOut.println("Adding " + initialSearchNode.GetCurrentBoard().toString() + " with hamming: " + initialSearchNode.GetCurrentBoard().hamming() +
//                " with manhattan: " + initialSearchNode.GetCurrentBoard().manhattan());
        SearchNode minSearchNode = currentPriorityQueue.delMin();
        //SearchNode minSearchNodeTwin = currentPriorityQueueTwin.delMin();
//        StdOut.println("Adding " + initialTwinSearchNode.GetCurrentBoard().toString() + " with hamming: " + initialSearchNode.GetCurrentBoard().hamming() +
//                " with manhattan: " + initialSearchNode.GetCurrentBoard().manhattan() + " To Twin Priority Queue");

        int counter = 0;
        while (!(minSearchNode.GetCurrentBoard().isGoal())) {
//            if (minSearchNodeTwin.GetCurrentBoard().isGoal()) {
//                solvable = false;
//                break;
//            }
            // I tested to see if I can check hamming and manhattan distance to stop adding boards and improve performance
//            if ((minSearchNode.GetCurrentBoard().hamming() % 2) == 1) {
//                StdOut.println("Manhattan distance of board is odd");
//                break;
//            }
//            for (Board bt : minSearchNodeTwin.GetCurrentBoard().neighbors()) {
//                // add a search node to the twin priority queue and twin GameTree only if it is not already in the GameTree
//                // or the priority queue
//                // I tested to see if checking manhattan or hamming distance improves the performance b/c goal has
//                // manhattan and hamming of zero i.e. even
//
//                SearchNode temp = new SearchNode(bt, minSearchNodeTwin.GetMovesCount() + 1, minSearchNodeTwin);
//                if (minSearchNodeTwin.GetPrevSearchNode() == null) {
//                    currentPriorityQueueTwin.insert(temp);
//                    gameTreeTwin.put(temp, twinValue);
//                } else if (!temp.GetCurrentBoard().equals(minSearchNodeTwin.GetPrevSearchNode().GetCurrentBoard())) {
//                    //StdOut.println("Adding to Twin Priority Queue.");
////                    StdOut.println("Adding neighbor Board with " + bt.toString() + " and hamming distance of: " + bt.hamming() +
////                            " and manhattan distance of: " + bt.manhattan() + " To Twin Priority Queue");
////                    StdOut.println("Adding neighbor Board with hamming distance of: " + s.GetCurrentBoard().hamming() +
////                            " and manhattan distance of: " + s.GetCurrentBoard().manhattan() + " Current moves count: "
////                            + twinMoves + " To Twin Priority Queue");
//                    // if you remove the item, you can use the same index next time to get another item
//
//                    currentPriorityQueueTwin.insert(temp);
//                    // I do not think I need to know how many moves it takes for the twin to solve so just using value
//                    gameTreeTwin.put(temp, twinValue);
//                } // else StdOut.println("Twin game tree already has this node.");
//            }
            //currentPriorityQueueTwin.insert((SearchNode) gameTreeTwin.min());
            //Create goal neighbors
//            for (Object o : gameTree.keys()) {
//                SearchNode s = (SearchNode) o;
//                Board tBoard = s.GetCurrentBoard();
//                if (minSearchNode.GetCurrentBoard().equals(tBoard)) {//found a match of minSearchNode in the Game Tree
//                    SearchNode temp = new SearchNode(s.GetCurrentBoard(), minSearchNode.GetMovesCount() + 1, minSearchNode);
//                    minSearchNode = temp;
//                    while (!minSearchNode.GetCurrentBoard().isGoal()) {
//                        temp = new SearchNode(minSearchNode.prevSearchNode.GetCurrentBoard(), minSearchNode.GetMovesCount() + 1,
//                                minSearchNode);
//                        minSearchNode = temp;
//                    }
//                    break;
//                }
//            SearchNode temp;
//            if (gameTree.rank(s) == 0) {
//                for (Board nei : s.GetCurrentBoard().neighbors()) {
//                    if (s.GetPrevSearchNode() == null) {
//                        temp = new SearchNode(nei, s.GetMovesCount() + 1, s);
//                        gameTree.put(temp, value);
//                        value++;
//                    } else if (!s.GetPrevSearchNode().equals(nei)) {
//                        gameTree.put(new SearchNode(nei, s.GetMovesCount() + 1, s), value);
//                        value++;
//                    }
//                }
//            }
//        }
            // Find the neighbors of all the leaves in GameTree and add them to the GameTree
//            counter++;
//            if (counter > 500) {
//                StdOut.println("Reseting Priority Queue.");
//                currentPriorityQueue = new MinPQ<>();
//                currentPriorityQueue.insert(minSearchNode);
//            }
            // Generate 3 - cycles from the goal
//            for (int z = 0; z <= counter; z++) {
//                int d = initialBoard.dimension();
//                Integer[] currentCycle = {d, d - 1, d - 2};
//                for (SearchNode currentPerm : GeneratePermutations(gBoard.dimension(), currentCycle)) {
//                    gameTree.put(currentPerm, currentPerm.GetPriority());
//                }
//            }
            // If you find that minSearchNode is already on the tree, then set minSearchnode equal to tree node's parent
//            if (gameTree.get(minSearchNode) != null) {
//                SearchNode s = (SearchNode) gameTree.floor(minSearchNode);
//                Board t = s.GetCurrentBoard();
//                StdOut.println("There was a match in the gametree.");
//                s = new SearchNode(t, minSearchNode.GetMovesCount() + 1, minSearchNode);
//                minSearchNode = s;
//            }


//                    StdOut.println("Successful Match. !!!!! " +
//                        "Minimum Search Node: " + minSearchNode.GetCurrentBoard() + "Game Tree: " + s.GetCurrentBoard());
//            if (minSearchNode.GetPriority() > (minSearchNode.manhattan * 4)) {
//                StdOut.println("Working ");
//                Object o = gameTree.get(gameTree.select(0));
//                SearchNode s = (SearchNode) o;
//                minSearchNode = s;
//                currentPriorityQueue = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
//                    @Override
//                    public int compare(SearchNode o1, SearchNode o2) {
//                        if (o1.GetPriority() > o2.GetPriority()) return 1;
//                        else if (o2.GetPriority() > o1.GetPriority()) return -1;
//                        else if (o1.GetPriority() == o2.GetPriority()) {
//                            if (o1.hamming > o2.hamming) return 1;
//                            else if (o2.hamming > o1.hamming) return -1;
//                        }
//                        return 0;
//                    }
//                });
//                currentPriorityQueue.insert(minSearchNode);
//
//            }

            for (Board b : minSearchNode.GetCurrentBoard().neighbors()) {
                // If manhattan of all the neighbors are more than the minSearchNode, and there is another node at the same
                // level minSearchNode or higher that I have not tried with a lower manhattan, I guess I should try it.
                // are there any nodes in the tree, which have a lower manhattan than minSearchNode, and have not been
                // used yet? i.e. it is not the parent of any nodes? And they are not the immediate neighbor of the
                // current minSearchNode
                //StdOut.println(gameTree.rank(minSearchNode));
                //if (gameTree.rank(minSearchNode) > 1) {
                //minSearchNode = gameTree.select(gameTree.rank())
                //for (Object o : gameTree.keys(gameTree.min(), minSearchNode)) {
                // If any of these nodes are used before, they should be the floor or ceiling of another node
                // in the same subtree, but that is the case even if they are not used
                //}
                //}
//                StdOut.println("Current Minimum Search Node is:  " + minSearchNode.GetCurrentBoard().toString() + " its hamming distance is: " +
//                        minSearchNode.GetCurrentBoard().hamming() + " its manhattan distance is: " + minSearchNode.GetCurrentBoard().manhattan());
//                StdOut.println();
//                StdOut.println("The current neighbor being considered is : " + b.toString() +
//                        " its manhattan value is: " + b.manhattan());
                SearchNode temp1;
                if (minSearchNode.GetPrevSearchNode() == null && !b.equals(initialBoard)) {
                    temp1 = new SearchNode(b, minSearchNode.numOfMoves + 1, minSearchNode);
//                    StdOut.println();
//                    StdOut.println("Adding neighbor Board : " + b.toString() + " with hamming distance of :  " + b.hamming() + " and manhattan distance of:  " + b.manhattan() + " Number of moves: " + temp1.numOfMoves + " To priority queue and the Game Tree.");
                    //SearchNode result = (SearchNode) gameTree.get(temp1);
                    currentPriorityQueue.insert(temp1);
                    //gameTree.put(temp1, temp1.GetPriority());


                } else if (minSearchNode.GetPrevSearchNode() != null && !b.equals(minSearchNode.GetPrevSearchNode().GetCurrentBoard())) {
                    // Add: if b's manhattan is more than minSearchNode's manhattan, check for a smaller manhattan in the tree
                    temp1 = new SearchNode(b, minSearchNode.GetMovesCount() + 1, minSearchNode);
                    //StdOut.println("<<<<<<<<<<<<<<<<<<<<<<<<<<Here are all the boards in the GameTree.>>>>>>>>>>>>>>>>");
//                    for (Object o : gameTree.keys()) {
//                        SearchNode s = (SearchNode) o;
//                StdOut.println(s.GetCurrentBoard());
//  Now I have to match a node close to the answer and make sure I can get to the answer. Does minSearchNode match any
//  of nodes already in the tree? If so follow it to the goal. This is where I should pick up tomorrow. Some how iteratively
// search the tree and cut off branches that are too far. And if they are closed, to add them to my path and get to solution
//                        if (s.manhattan > (initialBoard.manhattan() * 2)) gameTree.delete(s);
//                        if (s.GetCurrentBoard().equals(temp1.GetCurrentBoard()) && !s.equals(gNode)) {
//                            minSearchNode = new SearchNode(temp1.GetCurrentBoard(), minSearchNode.numOfMoves + 1, minSearchNode);
//                            StdOut.println(" The Node that matched the neighbor: " + s.GetCurrentBoard() + " Manhatan Distance: " +
//                                    s.manhattan + " The Rank of the Node " +
//                                    "that matched the neighbor: " + gameTree.rank(s));
//                            StdOut.println(" The parent of the Node that matched the neighbor: " + s.prevSearchNode.GetCurrentBoard() + " Manhatan Distance: "
//                                    + s.prevSearchNode.GetCurrentBoard().manhattan() + " The Rank of the Node that matched the neighbor: " + gameTree.rank(s));
//                            SearchNode f = (SearchNode) gameTree.floor(s);
//                            SearchNode c = (SearchNode) gameTree.ceiling(s);
//                            StdOut.println(" Floor Node: " + f.GetCurrentBoard() + " Manhattan Distance: " + f.manhattan + " Rank of floor of s: " + gameTree.rank(f));
//                            StdOut.println(" Ceiling Node: " + c.GetCurrentBoard() + " Manhattan Distance: " + c.manhattan + " Rank of celing of s: " + gameTree.rank(c));
//                            while (!minSearchNode.GetCurrentBoard().equals(gBoard)) {
// Note: Some nodes in the GameTree do not have a previous node, so you may have to use the floor or ceiling to get
// to the goal node or you may have to look at the neighbors again
//                            while (s.prevSearchNode != null) {
//                                minSearchNode = new SearchNode(s.prevSearchNode.GetCurrentBoard(), minSearchNode.numOfMoves + 1,
//                                        minSearchNode);
//                                if (minSearchNode.GetCurrentBoard().isGoal()) {
//                                    break outerloop;
//                                }
//                            }
//                            if (minSearchNode.GetCurrentBoard().isGoal()) {
//                                break outerloop;
//                            }
//                        }
//                    }
//                    if (minSearchNode.GetCurrentBoard().isGoal()) {
//                        break outerloop;
//                    }
//                    StdOut.println("Adding to minimum priority queue.");
//                    StdOut.println("Adding neighbor Board : " + b.toString() + " with hamming distance of :  " + b.hamming() + " and manhattan distance of:  " + b.manhattan() + " Number of moves: " + temp1.numOfMoves + " To priority queue and the Game Tree.");
//                    StdOut.println("Adding neighbor Board with hamming distance of :  " + b.hamming() + " and manhattan distance of:  " + b.manhattan() + " Current moves count: " + moves + " To priority queue");

                    currentPriorityQueue.insert(temp1);
                    //gameTree.put(temp1, temp1.GetPriority());

                } //else StdOut.println("Game Tree already has this node.");
// Threshold is the minimum cost of all the values that exceeded the current threshold
            }
            //currentPriorityQueue.insert((SearchNode) gameTree.min());
//            StdOut.println("Here are all the search nodes in the Priority Queue.");
//            for (SearchNode s : currentPriorityQueue) {
//                StdOut.print(s.GetCurrentBoard() + " Priority: " + s.GetPriority() + " Manhattan: " + s.manhattan + " Hamming " + s.hamming + " Number of moves: " + s.numOfMoves);
//            }
            //Object o = gameTree.min();
            //minSearchNode = (SearchNode) o;
            if (!currentPriorityQueue.isEmpty()) {
                if (currentPriorityQueue.size() > 9000) {
                    StdOut.println(" reseting minPriorityQueue ");
                    MinPQ<SearchNode> manhattanSoloPriority = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
                        @Override
                        public int compare(SearchNode o1, SearchNode o2) {
                            if (o1.manhattan > o2.manhattan) return 1;
                            else if (o2.manhattan > o1.manhattan) return -1;
                            return 0;
                        }
                    });
                    //currentPriorityQueue.insert((SearchNode) gameTree.min());
//                    for (Object o : gameTree.keys()) {
//                        SearchNode s = (SearchNode) o;
//                        if (gameTree.rank(s) <= 2)
//                            currentPriorityQueue.insert(s);
//                    }
//                    gameTree = new GameTree();
                    MinPQ<SearchNode> hammingSoloPriority = new MinPQ<>(new Comparator<SearchNode>() {
                        @Override
                        public int compare(SearchNode o1, SearchNode o2) {
                            if (o1.hamming > o2.hamming) return 1;
                            else if (o2.hamming > o1.hamming) return -1;
                            return 0;
                        }
                    });
                    MinPQ<SearchNode> naturalOrder = new MinPQ<>();

                    MinPQ<SearchNode> numberOfMovesSolo = new MinPQ<>(new Comparator<SearchNode>() {
                        @Override
                        public int compare(SearchNode o1, SearchNode o2) {
                            if (o1.numOfMoves > o2.numOfMoves) return 1;
                            else if (o2.numOfMoves > o1.numOfMoves) return -1;
                            else return 0;
                        }
                    });
                    for (int i = 0; i < 5000; i++) {
                        manhattanSoloPriority.insert(currentPriorityQueue.delMin());
                        //hammingSoloPriority.insert(currentPriorityQueue.delMin());
                        //naturalOrder.insert(currentPriorityQueue.delMin());
                        //numberOfMovesSolo.insert(currentPriorityQueue.delMin());
                    }
                    for (int i = 0; i < 2000; i++) {
                        hammingSoloPriority.insert(manhattanSoloPriority.delMin());
                    }
                    for (int i = 0; i < 1000; i++) {
                        numberOfMovesSolo.insert(hammingSoloPriority.delMin());
                    }
                    //currentPriorityQueue = manhattanSoloPriority;
                    //currentPriorityQueue = hammingSoloPriority;
                    //currentPriorityQueue = naturalOrder;
                    currentPriorityQueue = numberOfMovesSolo;
                }
                minSearchNode = currentPriorityQueue.delMin();
//                StdOut.println();
//                StdOut.println(" Here is the current size of Priority Queue: " + currentPriorityQueue.size() + " And here is the size of the Binary Search Tree: " + gameTree.size());


//                StdOut.println("Here is the node I am removing from the Priority Queue.");
//                StdOut.print(minSearchNode.GetCurrentBoard() + " Priority: " + minSearchNode.GetPriority() + " Manhattan: " + minSearchNode.manhattan + " Hamming " + minSearchNode.hamming + " Number of moves: " + minSearchNode.numOfMoves);
            } //else StdOut.println("Priority queue is empty.");
//            StdOut.println("Here are all the search nodes in the Twin Priority Queue.");
//            for (SearchNode s : currentPriorityQueueTwin) {
//                StdOut.println(s.GetCurrentBoard());
//            }
//            if (!currentPriorityQueueTwin.isEmpty()) {
//                //prevTwinSearchNode = minSearchNode;
//                minSearchNodeTwin = currentPriorityQueueTwin.delMin();
//                StdOut.println("Taking " + minSearchNodeTwin.GetCurrentBoard().toString() + " twin minimum search node with hamming: " +
//                        minSearchNode.GetCurrentBoard().hamming() + "with manhattan: " + minSearchNode.GetCurrentBoard().manhattan() +
//                        "out of Twin Priority Queue");
//            } else StdOut.println("Twin priority queue is empty.");

        }
        //moves = gameTree.rank(minSearchNode);
        if (minSearchNode.GetCurrentBoard().isGoal()) {
            solvable = true;
            solution = minSearchNode.GetCurrentBoard();
            //moves = minSearchNode.numOfMoves;
            while (!minSearchNode.GetCurrentBoard().equals(initialBoard)) {
                solutionBoardList.add(minSearchNode.GetCurrentBoard());
                moves = Math.max(moves, minSearchNode.numOfMoves);
                minSearchNode = minSearchNode.GetPrevSearchNode();
            }
//            StdOut.println("Here is what was in the Search Tree. ");
//            for (Object o : gameTree.keys()) {
//                SearchNode s = (SearchNode) o;
//                StdOut.println("Node: " + s.currentBoard);
//            }
        } else {
            solvable = false;
        }
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

    private static class SearchNode implements Comparable<SearchNode> {
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
            //TODO Exploit the fact that the difference in Manhattan distance between a board and a neighbor is either
            // −1 or +1 ( From FAQ ) implement it
            //return manhattan + hamming + numOfMoves;Changing this to the line below fixed the issue I had with 2 extra moves.
            return ((manhattan) + (numOfMoves));
        }

        @SuppressFBWarnings("EQ_COMPARETO_USE_OBJECT_EQUALS")
        @Override
        public int compareTo(SearchNode o) {
            //TODO check for each case separately i.e. manhattan, if they are equal, then hamming, if they are still
            // equal then number of moves
            if (this.manhattan > o.manhattan) return 1;
            else if (this.manhattan < o.manhattan) return -1;
            else if (this.numOfMoves > o.numOfMoves) return 1;
            else if (o.numOfMoves > this.numOfMoves) return -1;
            else if (this.hamming > o.hamming) return 1;
            else if (o.hamming > this.hamming) return -1;
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
    @SuppressFBWarnings({"RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT", "DLS_DEAD_LOCAL_STORE"})
    public static void main(String[] args) {
        //TODO: Build tables: initial, goal, and change 1 digit at a time and see how they are printed out when you print the tree
        char[][] testTiles = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};// manhattan 4, moves 0, priority 4
        //char[][] testTilesCopy = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        //char[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        //char[][] goalTilesCopy = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        //char[][] gNeighbor1 = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        //char[][] gNeighbor2 = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        //Board bNeigbor1 = new Board(gNeighbor1);
        //Board bNeigbor2 = new Board(gNeighbor2);
        //StdOut.println(" Hamming Distance of Neighbor 1 : " + bNeigbor1.hamming() + " Manhattan Distance of Neighbor 1:" + bNeigbor1.manhattan());
        //StdOut.println(" Hamming Distance of Neighbor 2 : " + bNeigbor2.hamming() + " Manhattan Distance of neighbor 2:" + bNeigbor2.manhattan());
        char[][] testTilesNeighbor1 = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}}; // Manhattan: 3, moves: 1,priority= 4
        char[][] testTilesNeighbor2 = {{4, 1, 3}, {0, 2, 5}, {7, 8, 6}}; // Manhattan: 5, moves: 1, priority: 6
        char[][] testTilesNeighbor3 = {{4, 1, 3}, {7, 2, 5}, {0, 8, 6}}; // Manhattan: 6, moves: 2, priority: 8
        char[][] testTilesNeighbor4 = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}}; // Manhattan: 2, moves: 2, priority: 4
        char[][] testTilesNeighbor5 = {{1, 2, 3}, {4, 6, 5}, {7, 8, 0}};
//        bNeigbor1 = new Board(testTilesNeighbor1);
//        bNeigbor2 = new Board(testTilesNeighbor2);
        Board testTilesBoard = new Board(testTiles);
        Board copyOfTestTilesBoard = new Board(testTiles);
        Board testTilesNeighbor1Board = new Board(testTilesNeighbor1);
        Board testTilesNeighbor2Board = new Board(testTilesNeighbor2);
        Board testTilesNeighbor3Board = new Board(testTilesNeighbor3);
        Board testTilesNeighbor4Board = new Board(testTilesNeighbor4);
        Board testTilesNeighbor5Board = new Board(testTilesNeighbor5);
        GameTree gt = new GameTree();
        SearchNode testTilesBoardNode = new SearchNode(testTilesBoard, 0, null);
        SearchNode copyOfTestTilesBoardNode = new SearchNode(copyOfTestTilesBoard, 1, null);
        SearchNode testTilesNeighbor1BoardNode = new SearchNode(testTilesNeighbor1Board, testTilesBoardNode.numOfMoves + 1, testTilesBoardNode);
        SearchNode testTilesNeighbor2BoardNode = new SearchNode(testTilesNeighbor2Board, testTilesBoardNode.numOfMoves + 1, testTilesNeighbor1BoardNode);
        SearchNode testTilesNeighbor3BoardNode = new SearchNode(testTilesNeighbor3Board, testTilesNeighbor2BoardNode.numOfMoves + 1, testTilesNeighbor2BoardNode);
        SearchNode testTilesNeighbor4BoardNode = new SearchNode(testTilesNeighbor4Board, testTilesNeighbor1BoardNode.numOfMoves + 1, testTilesNeighbor1BoardNode);
        SearchNode testTilesNeighbor5BoardNode = new SearchNode(testTilesNeighbor5Board, testTilesNeighbor1BoardNode.numOfMoves + 1, testTilesNeighbor1BoardNode);
        gt.put(testTilesBoardNode, testTilesBoardNode.manhattan);
        gt.put(testTilesNeighbor1BoardNode, testTilesNeighbor1BoardNode.manhattan);
        gt.put(testTilesNeighbor2BoardNode, testTilesNeighbor2BoardNode.manhattan);
        gt.put(testTilesNeighbor3BoardNode, testTilesNeighbor3BoardNode.manhattan);
        gt.put(testTilesNeighbor4BoardNode, testTilesNeighbor4BoardNode.manhattan);
        gt.put(testTilesNeighbor5BoardNode, testTilesNeighbor5BoardNode.manhattan);
        int k = gt.rank(testTilesBoardNode);
        if (k > 2) {
            StdOut.println(k);
        }
        StdOut.println(gt.rank(testTilesBoardNode));
        SearchNode s = (SearchNode) gt.select(2);// In a larger tree this should g
        SearchNode fs = (SearchNode) gt.floor(gt.select(2));
        StdOut.println("Here is a node with ranking of 2 : " + s.GetCurrentBoard());
        StdOut.println("Here is the floor of it : " + fs.GetCurrentBoard());
        s = (SearchNode) gt.select(1);
        fs = (SearchNode) gt.floor(gt.select(1));
        StdOut.println("Here is a node with ranking of 1 : " + s.GetCurrentBoard());
        StdOut.println("Here is the floor of it : " + fs.GetCurrentBoard());

        s = (SearchNode) gt.select(0);
        fs = (SearchNode) gt.floor(gt.select(0));
        StdOut.println("Here is a node with ranking of 0 : " + s.GetCurrentBoard());
        StdOut.println("Here is the floor of it : " + fs.GetCurrentBoard());

        StdOut.println("Ya Ya");
        //gt.delete(gt.ceiling(testTilesBoardNode));
        gt.max();
        gt.min();
        StdOut.println(gt.min());
        //gt = new GameTree();
        MinPQ<SearchNode> currentPriorityQueue = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                if (o1.GetPriority() > o2.GetPriority()) return 1;
                else if (o2.GetPriority() > o1.GetPriority()) return -1;
                else if (o1.GetPriority() == o2.GetPriority()) {
                    if (o1.hamming > o2.hamming) return 1;
                    else if (o2.hamming > o1.hamming) return -1;
                }
                return 0;
            }
        });
        gt.put(testTilesBoardNode, testTilesBoardNode.manhattan);
        for (Object o : gt.keys(gt.min(), testTilesBoardNode)) {
            s = (SearchNode) o;
            currentPriorityQueue.insert(s);
        }
        SearchNode result = (SearchNode) gt.floor(testTilesBoardNode);
        if (gt.get(testTilesBoardNode) != null) StdOut.println("It is not empty! ");
        StdOut.println(result.GetCurrentBoard());
//        StdOut.println(" Hamming Distance of testTilesNeighbor1 : " + bNeigbor1.hamming() + " Manhattan Distance of testTilesNeighbor1:" + bNeigbor1.manhattan());
//        StdOut.println(" Hamming Distance of testTilesNeighbor2 : " + bNeigbor2.hamming() + " Manhattan Distance of testTilesNeighbor2:" + bNeigbor2.manhattan());
//        StdOut.println(" Hamming Distance of testTilesNeighbor3 : " + bNeigbor3.hamming() + " Manhattan Distance of testTilesNeighbor3:" + bNeigbor3.manhattan());
//        StdOut.println(" Hamming Distance of testTilesNeighbor4 : " + bNeigbor4.hamming() + " Manhattan Distance of testTilesNeighbor4:" + bNeigbor4.manhattan());
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
//        char[][] one = {{4, 1, 2}, {3, 0, 6}, {5, 7, 8}};  //updated this one to 3x3-12 table
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
//        GameTree testTree = new GameTree();
//        Board b1 = new Board(one);
//        Board b2 = new Board(two);
//        Board b3 = new Board(three);
//        Board b4 = new Board(four);
//        Board b5 = new Board(five);
//        Board b6 = new Board(six);
//        Board b7 = new Board(seven);
//        Board b8 = new Board(eight);
//        Board b9 = new Board(nine);
//        Board b10 = new Board(ten);
//        Board b11 = new Board(eleven);
//        Board b12 = new Board(twelve);
//        Board b13 = new Board(thirteen);
//        assert b13.isGoal();
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
//        SearchNode b1TestNode = new SearchNode(b1, 0, null);
//        SearchNode b2TestNode = new SearchNode(b2, 1, b1TestNode);
//        SearchNode b3TestNode = new SearchNode(b3, 2, b2TestNode);
//        SearchNode b4TestNode = new SearchNode(b4, 3, b3TestNode);
//        SearchNode b5TestNode = new SearchNode(b5, 4, b4TestNode);
//        SearchNode b6TestNode = new SearchNode(b6, 5, b5TestNode);
//        SearchNode b7TestNode = new SearchNode(b7, 6, b6TestNode);
//        SearchNode b8TestNode = new SearchNode(b8, 7, b7TestNode);
//        SearchNode b9TestNode = new SearchNode(b9, 8, b8TestNode);
//        SearchNode b10TestNode = new SearchNode(b10, 9, b9TestNode);
//        SearchNode b11TestNode = new SearchNode(b11, 10, b10TestNode);
//        SearchNode b12TestNode = new SearchNode(b12, 11, b11TestNode);
//        SearchNode b13TestNode = new SearchNode(b13, 12, b12TestNode);
//Print the manhattan, hamming, and priority of the search nodes
//        StdOut.println("Manhattan                 Hamming            Priority");
//        StdOut.printf("%4s %25s  %18s\n", b1.manhattan(), b1.hamming(), b1TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b1.manhattan(), b1.hamming(), b1TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b2.manhattan(), b2.hamming(), b2TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b3.manhattan(), b3.hamming(), b3TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b4.manhattan(), b4.hamming(), b4TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b5.manhattan(), b5.hamming(), b5TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b6.manhattan(), b6.hamming(), b6TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b7.manhattan(), b7.hamming(), b7TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b8.manhattan(), b8.hamming(), b8TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b9.manhattan(), b9.hamming(), b9TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b10.manhattan(), b10.hamming(), b10TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b11.manhattan(), b11.hamming(), b11TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b12.manhattan(), b12.hamming(), b12TestNode.GetPriority());
//        StdOut.printf("%4s %25s  %18s\n", b13.manhattan(), b13.hamming(), b13TestNode.GetPriority());
//        testTree.put(b1TestNode, 0);
//        testTree.put(b2TestNode, 1);
//        testTree.put(b3TestNode, 2);
//        testTree.put(b4TestNode, 3);
//        testTree.put(b5TestNode, 4);
//        testTree.put(b6TestNode, 5);
//        testTree.put(b7TestNode, 6);
//        testTree.put(b8TestNode, 7);
//        testTree.put(b9TestNode, 8);
//        testTree.put(b10TestNode, 9);
//        testTree.put(b11TestNode, 10);
//        testTree.put(b12TestNode, 11);
//        testTree.put(b13TestNode, 12);
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
//        StdOut.println("size = " + testTree.size());
//        StdOut.println("min  = " + testTree.min());
//        StdOut.println("max  = " + testTree.max(b2TestNode));
//        StdOut.println();
// get returns null if the key does not exist. The if test below actually checks the table inside the objects, and does
// not put the new object in the tree if the tables are the same which is what I want
//        if (testTree.get(b2TestNode) == null) {
//            testTree.put(b2TestNode, 3);
//        }
//        // This is how to print all the keys w/o the Iterator. Now I have the keys() and should be able to use it
//        for (int i = 0; i < testTree.size(); i++) {
//            SearchNode s = (SearchNode) testTree.select(i);
//            StdOut.println(s.GetCurrentBoard() + " Priority: " + s.GetPriority());
//        }
//        StdOut.println("Printing using foreach loop: ");
//        for (Object s : testTree.keys()) {
//            SearchNode temp = (SearchNode) s;
//            StdOut.println(temp.GetCurrentBoard() + " Priority: " + temp.GetPriority());
//        }
    }
}
