package assignments;

public class SearchNode implements Comparable<SearchNode> {
    Board currentBoard;
    int numOfMoves;
    SearchNode prevSearchNode;

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
