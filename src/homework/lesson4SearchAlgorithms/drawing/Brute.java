package homework.lesson4SearchAlgorithms.drawing;

import princeton.lib.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * brute force algorithm to draw an image by points
 * into txt format from png format
 */
public class Brute {

    public Line[] getLines() {
        return lines;
    }

    private final Line[] lines;

    public Brute(Point[] points) {
        Point[] copy = new Point[points.length];
        System.arraycopy(points, 0, copy, 0, points.length);
        int numOfPoints = points.length;

        List<Line> lines = new LinkedList<>();
        Arrays.sort(points);

        for (int p = 0; p < numOfPoints; p++) {
            for (int q = p + 1; q < numOfPoints; q++) {
                for (int r = q + 1; r < numOfPoints; r++) {
                    for (int s = r + 1; s < numOfPoints; s++) {
                        double a = points[p].slopeTo(points[q]);
                        double b = points[p].slopeTo(points[r]);
                        double c = points[p].slopeTo(points[s]);
                        if (a == b && b == c) {
                            lines.add(new Line(points[p], points[s]));
                        }
                    }
                }
            }
        }
        this.lines = lines.toArray(new Line[0]);

    }

    public static void main(String[] args) {
        In in = new In("src/homework/lesson4/txt/test.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        Brute brute = new Brute(points);
        Stopwatch sw = new Stopwatch();
        for (Line line : brute.getLines()) {
            StdOut.println(line);
            line.draw();
        }
        System.out.println("time taken: "+sw.elapsedTime());
        StdDraw.show();
    }
}
