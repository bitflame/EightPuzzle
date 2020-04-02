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

    public int GetPriority() {
        return currentBoard.manhattan() + numOfMoves;
    }

    @Override
    public int compareTo(SearchNode o) {
        if (o.GetCurrentBoard().manhattan() < this.GetCurrentBoard().manhattan()) return 1;
        if (o.GetCurrentBoard().manhattan() > this.GetCurrentBoard().manhattan()) return -1;
        return 0;

    }
}
