package homework.lesson4SearchAlgorithms.drawing;

import princeton.lib.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * fast brute force algorithm to draw an image by points
 * into txt format from png format
 */
public class Fast {

    private final Point[] points;

    public Line[] getLines() {
        return lines;
    }

    private final Line[] lines;
    private final int numOfPoints;
    private final List<Line> linesList = new LinkedList<>();

    public Fast(Point[] points) {
        this.points = points;
        Point[] copy = points.clone();
        numOfPoints = points.length;
        drawLines(copy);
        lines = linesList.toArray(new Line[0]);
    }

    private void drawLines(Point[] copy) {
        for (Point p : points) {
            Arrays.sort(copy, 0, numOfPoints, p.SLOPE_ORDER);
            int numberOfCollinearPoints = 1,
                    firstSuitablePoint = 1;
            boolean a = false;
            for (int i = firstSuitablePoint; i < numOfPoints - 1; i++) {
                if (p.slopeTo(copy[i]) == p.slopeTo(copy[i + 1])) {
                    numberOfCollinearPoints++;
                    if (!a) {
                        a = true;
                        firstSuitablePoint = i;
                    }
                } else if (a)
                    break;
            }

            if (numberOfCollinearPoints >= 3)
                ifColl(numberOfCollinearPoints, firstSuitablePoint, p ,copy);
        }
    }

    private void ifColl(int numberOfCollinearPoints, int firstSuitablePoint, Point p, Point[] copy) {
        Point[] collinearPoints = new Point[numberOfCollinearPoints + 1];
        collinearPoints[0] = p;
        System.arraycopy(copy, firstSuitablePoint, collinearPoints, 1, numberOfCollinearPoints);
        Arrays.sort(collinearPoints, 0, numberOfCollinearPoints + 1);
        linesList.add(new Line(collinearPoints[0], collinearPoints[numberOfCollinearPoints]));
    }

    public static void main(String[] args) {
        In in = new In("src/homework/lesson4/txt/rs1423.txt");
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

        Fast fast = new Fast(points);
        Stopwatch sw = new Stopwatch();
        for (Line line : fast.getLines()) {
            StdOut.println(line);
            line.draw();
        }
        System.out.println("time taken: "+sw.elapsedTime());
        StdDraw.show();
    }
}
