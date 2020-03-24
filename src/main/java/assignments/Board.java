package assignments;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Iterable<Character>, Comparable<Board> {
    private char[][] tiles;
    private int N;
    private char blankRow;
    private char blankCol;
    private int HammingDistance;
    private int ManhattanDistance;

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
        hamming();
        manhattan();
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
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
    public char hamming() {
        char distanceHamming = 0;
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
                if (tiles[i][j] != ((i + 1) + j)) {
                    distanceHamming++;
                }
            }
        }
        HammingDistance = distanceHamming;
        return distanceHamming;
    }

    // sum of Manhattan distances between tiles and goal
    // Here is where I got this from:
    //  https://www.coursera.org/learn/algorithms-part1/programming/iqOQi/8-puzzle/discussions/threads/2Fon3sA7EeevSwpBtQ053g
    public char manhattan() {
        char manhattan = 0;
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
        ManhattanDistance = manhattan;
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
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
        char[][] neighbor = this.tiles;
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
        char temp1 = tiles[a][b];
        int c = StdRandom.uniform(0, N);
        int d = StdRandom.uniform(0, N);
        char temp2 = tiles[c][d];
        char[][] copy = CopyBoard(this.tiles);
        copy[a][b] = temp2;
        copy[c][d] = temp1;
        return new Board(copy);
    }

    private char[][] TilesConvert(char[][] tiles) {
        char[][] temp = new char[N][N];
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
                temp[i][j] = tiles[i][j];
            }
        }
        return temp;
    }

    private char[][] CopyBoard(char[][] b) {
        char[][] temp = new char[10][10];
        for (char i = 0; i < N; i++) {
            for (char j = 0; j < N; j++) {
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
        return this.ManhattanDistance - o.ManhattanDistance;
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

    }


}
