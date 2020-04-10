package assignments;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Iterable<Character>, Comparable<Board> {
    private char[][] tiles;
    private int N;
    private char blankRow;
    private char blankCol;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(char[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("The board you are submitting is empty.");
        }
        this.N = tiles[0].length;
        if (this.N < 2 || this.N > 128) {
            throw new IllegalArgumentException("The value of n is more than expected.");
        }
        this.tiles = new char[N][N];
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (this.tiles[i][j] == 0) {
                    this.blankRow = i;
                    this.blankCol = j;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Dimensions: " + N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int) tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return this.N;
    }

    // number of tiles out of place
    public int hamming() {
        int distanceHamming = 0;
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
                if (tiles[i][j] == 0) continue;
                if (tiles[i][j] != (((i * tiles.length) + 1) + j)) {
                    distanceHamming++;
                }
            }
        }
        return distanceHamming;
    }

    // sum of Manhattan distances between tiles and goal
    // Here is where I got this from:
    //  https://www.coursera.org/learn/algorithms-part1/programming/iqOQi/8-puzzle/discussions/threads/2Fon3sA7EeevSwpBtQ053g
    public int manhattan() {
        int manhattan = 0;
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
                if (tiles[i][j] != 0) { //
                    int targetX = (tiles[i][j] - 1) / N;
                    int targetY = (tiles[i][j] - 1) % N;
                    int dx = i - targetX;
                    int dy = j - targetY;
                    manhattan += Math.abs(dx) + Math.abs(dy);
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] == 0) continue;
                if (this.tiles[i][j] != (N * i) + (j + 1)) return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        char[][] neighbor = CopyBoard(this.tiles);
        int index = N - 1;
        if (blankRow < index) {// zero is less than N
            neighbor[blankRow + 1][blankCol] = 0;
            neighbor[blankRow][blankCol] = this.tiles[blankRow + 1][blankCol];
            neighbors.add(new Board(neighbor));
        }
        if (blankRow > 0) {
            neighbor = CopyBoard(this.tiles);
            neighbor[blankRow - 1][blankCol] = 0;
            neighbor[blankRow][blankCol] = this.tiles[blankRow - 1][blankCol];
            neighbors.add(new Board(neighbor));
        }
        if (blankCol < index) {
            neighbor = CopyBoard(this.tiles);
            neighbor[blankRow][blankCol + 1] = 0;
            neighbor[blankRow][blankCol] = this.tiles[blankRow][blankCol + 1];
            neighbors.add(new Board(neighbor));
        }
        if (blankCol > 0) {
            neighbor = CopyBoard(this.tiles);
            neighbor[blankRow][blankCol - 1] = 0;
            neighbor[blankRow][blankCol] = this.tiles[blankRow][blankCol - 1];
            neighbors.add(new Board(neighbor));
        }
        ArrayList<Board> neiCopy = new ArrayList<Board>(neighbors);
        return neiCopy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        char[][] tempTiles = new char[N][N];
        tempTiles = CopyBoard(tiles);
        if (blankRow < N - 1 && blankCol < N - 1) {
            char temp = tiles[blankRow + 1][blankCol + 1];
            char temp2 = tiles[blankRow][blankCol + 1];
            tempTiles[blankRow + 1][blankCol + 1] = temp2;
            tempTiles[blankRow][blankCol + 1] = temp;
        } else if (blankRow > 0 && blankCol > 0) {
            char temp = tiles[blankRow - 1][blankCol - 1];
            char temp2 = tiles[blankRow][blankCol - 1];
            tempTiles[blankRow - 1][blankCol - 1] = temp2;
            tempTiles[blankRow][blankCol - 1] = temp;
        } else if (blankRow < N - 1 && blankRow > 0) {
            char temp = tiles[blankRow + 1][blankCol];
            char temp2 = tiles[blankRow - 1][blankCol];
            tempTiles[blankRow + 1][blankCol] = temp2;
            tempTiles[blankRow - 1][blankCol] = temp;
        }
        Board retBoard = new Board(tempTiles);
        return retBoard;
    }

    // Do not really need the following method. Delete it tomorrow
    private int[][] TilesConvert(char[][] tiles) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = (int) tiles[i][j];
            }
        }
        return temp;
    }

    public static char[][] CopyBoard(char[][] b) {
        char[][] temp = new char[b.length][b.length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                temp[i][j] = b[i][j];
            }
        }
        return temp;
    }

    @Override
    public Iterator<Character> iterator() {
        return new ListIterator();
    }

    @Override
    public int compareTo(Board o) {
        return this.manhattan() - o.manhattan();
    }

    private class ListIterator implements Iterator<Character> {
        char currentRow = 0;
        char currentCol = 0;

        @Override
        public boolean hasNext() {
            return (currentRow < N && currentCol < N);
        }

        @Override
        public Character next() {
            if (currentRow == N && currentCol == N) return null;
            if (currentCol == N) {
                currentRow++;
                currentCol = 0;
            }
            return tiles[currentRow][currentCol + 1];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        char[][] testTiles = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}};
        Board tb = new Board(testTiles);
        //char[][] goalTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        StdOut.println("Original table: ");
        StdOut.println(tb);
        StdOut.println("Here are the Neighbors: ");
        for (Board b : tb.neighbors()) {
            StdOut.println(b);
            StdOut.println(b.compareTo(tb));
        }
        StdOut.println("The dimension is: " + tb.dimension());
    }
}
