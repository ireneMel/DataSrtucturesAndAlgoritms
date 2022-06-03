package homework.lesson1Percolation;

import princeton.lib.*;

public class PercolationStatus {

    private final int T;
    private final double[] s2;

    public PercolationStatus(int N, int T) {
        this.T = T;
        s2 = new double[T];

        for (int a = 0; a < T; a++) {
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                boolean open = true;
                int i = 0, j = 0;
                while (open) {
                    i = StdRandom.uniform(0, N);
                    j = StdRandom.uniform(0, N);
                    open = p.isOpened(i, j);
                }
                p.open(i, j);

            }
            s2[a] = (double) p.getOpenedCount() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(s2);
    }

    public double stddev() {
        return StdStats.stddev(s2);
    }

    public double bound1() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    public double bound2() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }

    public static void main(String[] args) {
        PercolationStatus ps;
        while (!StdIn.isEmpty()) {
            int N = StdIn.readInt();
            int T = StdIn.readInt();
            Stopwatch sw = new Stopwatch();
            ps = new PercolationStatus(N, T);
            StdOut.println("mean = " + ps.mean());
            StdOut.println("stddev = " + ps.stddev());
            StdOut.println("95% confidence interval: [" + ps.bound1() + ", " + ps.bound2() + "]");
            StdOut.println("Time: " + sw.elapsedTime());
        }
    }
}
