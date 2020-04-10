package assignments;

// make this immutable
public class SearchNode implements Comparable<SearchNode> {
    private final Board currentBoard;
    private final int numOfMoves;
    private final SearchNode prevSearchNode;

    public SearchNode(Board b, int m, SearchNode prev) {
        currentBoard = b;
        numOfMoves = m;
        prevSearchNode = prev;
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
        return currentBoard.manhattan() + numOfMoves;
    }

    @Override
    public int compareTo(SearchNode o) {
        if ((this.GetCurrentBoard().manhattan()) > (o.GetCurrentBoard().manhattan()))
            return 1;
        if ((this.GetCurrentBoard().manhattan()) < (o.GetCurrentBoard().manhattan()))
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
