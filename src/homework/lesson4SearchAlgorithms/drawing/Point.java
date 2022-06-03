package homework.lesson4SearchAlgorithms.drawing;

import princeton.lib.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SOrder();

    private final int x;
    private final int y;

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }


    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (this.y == that.y) return +0.0;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        return (1.0) * (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
//        if (this.y > that.y) return 1;
//        if (this.y < that.y) return -1;
//        if (this.x > that.x) return 1;
//        if (this.x < that.x) return -1;
        return 0;
    }

    private class SOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double p1 = slopeTo(p);
            double q1 = slopeTo(q);
            return Double.compare(p1,q1);
        }
    }


    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}