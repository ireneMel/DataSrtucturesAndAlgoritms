package homework.lesson1Percolation;

import lections.lection1.QuickFindUF;

/**
 * Percolation is an abstract model for many physical systems.
 * Given an n-by-n grid of sites,
 * each site is open with probability p,
 * the system percolates if and only if any open site in the top row in the grid
 * is connected to any open site in the bottom row by open sites.
 */
public class Percolation {

    private final boolean[] cells;
    private final QuickFindUF qf;
    private final int N;
    private final int top;
    private final int bottom;
    private int openedCells;

    public Percolation(int N) {
        this.N = N;
        qf = new QuickFindUF(N * N + 2); //includes top and bottom
        top = N * N;
        bottom = N * N + 1;
        cells = new boolean[N * N];
    }

    public void open(int i, int j) {
        if (!check(i, j)) throw new IllegalArgumentException();
        if (isOpened(i, j)) return;
        int id = convertId(i, j);
        cells[id] = true;

        if (i == 0) qf.union(top, id);
        if (i == N - 1) qf.union(bottom, id);

        if (check(i + 1, j) && isOpened(i + 1, j)) qf.union(convertId(i + 1, j), id);
        if (check(i - 1, j) && isOpened(i - 1, j)) qf.union(convertId(i - 1, j), id);
        if (check(i, j + 1) && isOpened(i, j + 1)) qf.union(convertId(i, j + 1), id);
        if (check(i, j - 1) && isOpened(i, j - 1)) qf.union(convertId(i, j - 1), id);

        openedCells++;
    }

    public boolean isOpened(int i, int j) {
        if (!check(i, j)) throw new IllegalArgumentException();
        return cells[convertId(i, j)];
    }

    public int getOpenedCount() {
        return openedCells;
    }

    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    private boolean check(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N;
    }

    private int convertId(int i, int j) {
        if (!check(i, j)) throw new IllegalArgumentException();
        return N * i + j;
    }

}
