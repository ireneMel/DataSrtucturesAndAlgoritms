package homework.lesson98Puzzle;

import java.util.Arrays;
import java.util.Stack;

public final class Board {
    public final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = clone2D(blocks);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbours = new Stack<>();
        int position = -1;

        //find empty position
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] == 0) {
                    position = j + i * dimension();
                    break;
                }
            }
        }

        int i = position / dimension();
        int j = position % dimension();
        if (i > 0) neighbours.push(new Board(exchange(i, j, i - 1, j)));
        if (j > 0) neighbours.push(new Board(exchange(i, j, i, j - 1)));
        if (i < blocks.length - 1) neighbours.push(new Board(exchange(i, j, i + 1, j)));
        if (j < blocks.length - 1) neighbours.push(new Board(exchange(i, j, i, j + 1)));

        return neighbours;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public int dimension() {
        return blocks.length;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) return false;
        if (that == this) return true;
        if (that.getClass() != this.getClass()) return false;
        return Arrays.deepEquals(this.blocks, ((Board) that).blocks);
    }

    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks.length; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != j + i * dimension() + 1) {
                    int pos = blocks[i][j];
                    int h = Math.abs(--pos % dimension() - j);
                    int v = Math.abs(pos / dimension() - i);
                    cnt += h + v;
                }
        return cnt;
    }

    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < dimension(); j++)
                if (blocks[i][j] != 0 && blocks[i][j] != j + i * dimension() + 1) cnt++;
        return cnt;
    }

    private int[][] clone2D(int[][] array) {
        int n = array.length;
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, n);
        }
        return clone;
    }

    private int[][] exchange(int x, int y, int a, int b) {
        int[][] clone = clone2D(blocks);
        int tmp = clone[x][y];
        clone[x][y] = clone[a][b];
        clone[a][b] = tmp;
        return clone;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = dimension();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(blocks[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}