package assignments;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Iterable<Integer> {
    private int[][] tiles;
    private int N;
    private int blankRow;
    private int blankCol;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("The board you are submitting is empty.");
        }
        this.N = tiles[0].length;
        if (this.N < 2 || this.N > 128) {
            throw new IllegalArgumentException("The value of n is more than expected.");
        }
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", this.tiles[i][j]));
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != ((i + 1) + j)) {
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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
                if (this.tiles[i][j] != (i + j + 1)) return false;
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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
        int[][] neighbor = this.tiles;
        if (blankRow < N) {
            neighbor[blankRow][blankCol] = neighbor[blankRow + 1][blankCol];
            neighbor[blankRow + 1][blankCol] = 0;
            neighbors.add(new Board(neighbor));
        }
        if (blankRow > 0) {
            neighbor[blankRow][blankCol] = neighbor[blankRow - 1][blankCol];
            neighbor[blankRow - 1][blankCol] = 0;
            neighbors.add(new Board(neighbor));
        }
        if (blankCol < N) {
            neighbor[blankRow][blankCol] = neighbor[blankRow][blankCol + 1];
            neighbor[blankRow][blankCol + 1] = 0;
            neighbors.add(new Board(neighbor));
        }
        if (blankCol > 0) {
            neighbor[blankRow][blankCol] = neighbor[blankRow][blankCol - 1];
            neighbor[blankRow][blankCol - 1] = 0;
            neighbors.add(new Board(neighbor));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int a = StdRandom.uniform(0, N);
        int b = StdRandom.uniform(0, N);
        int temp1 = tiles[a][b];
        int c = StdRandom.uniform(0, N);
        int d = StdRandom.uniform(0, N);
        int temp2 = tiles[c][d];
        int[][] copy = CopyBoard(this.tiles);
        copy[a][b] = temp2;
        copy[c][d] = temp1;
        Board tilesTwin = new Board(copy);
        return tilesTwin;
    }

    private int[][] TilesConvert(char[][] tiles) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = tiles[i][j];
            }
        }
        return temp;
    }

    private int[][] CopyBoard(int[][] b) {
        int[][] temp = new int[10][10];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = b[i][j];
            }
        }
        return temp;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Integer> {
        int currentRow = 0;
        int currentCol = 0;

        @Override
        public boolean hasNext() {
            return (currentRow < N && currentCol < N);
        }

        @Override
        public Integer next() {
            if (currentRow == N && currentCol == N) return -1;
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

    }


}
