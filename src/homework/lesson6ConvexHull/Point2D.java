package homework.lesson6ConvexHull;

import princeton.lib.StdDraw;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    public static final Comparator<Point2D> Y_ORDER = new YOrder();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area > 0) return 1;
        if (area < 0) return -1;
        else return 0;
    }

    public boolean equals(Point2D other) {
        return (this.x == other.x && this.y == other.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public int compareTo(Point2D that) {
        if (this.y > that.y) return 1;
        if (this.y < that.y) return -1;
        if (this.x > that.x) return 1;
        if (this.x < that.x) return -1;
        return 0;
    }

    private class PolarOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            double dy1 = o1.y - y;
            double dy2 = o2.y - y;
            double dx1 = o1.x - x;
            double dx2 = o2.x - x;

            if (dy1 == 0 && dy2 == 0) {
                if (dx1 >= 0 && dx2 < 0) return -1;
                if (dx1 < 0 && dx2 >= 0) return 1;
                return 0;
            } else if (dy1 >= 0 && dy2 < 0) return -1;
            else if (dy1 < 0 && dy2 >= 0) return 1;
            else return -ccw(Point2D.this, o1, o2);
        }
    }

    private static class YOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            if(o1.y == o2.y ) {
                if(o1.x > o2.x) return 1;
                if(o1.x < o2.x) return -1;
            };
            if(o1.y > o2.y) return 1;
            if(o1.y < o2.y) return -1;

            return 0;
        }
    }
}
