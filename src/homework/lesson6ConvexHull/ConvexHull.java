package homework.lesson6ConvexHull;

import princeton.lib.In;
import princeton.lib.StdDraw;

import java.util.Arrays;
import java.util.Stack;

public class ConvexHull {

    Stack<Point2D> aux = new Stack<>();

    public ConvexHull(Point2D[] points) {
        Arrays.sort(points, Point2D.Y_ORDER);
        Arrays.sort(points, points[0].POLAR_ORDER);

        aux.push(points[0]);

        for (int i = 1; i < points.length; i++) {
            Point2D first = aux.pop();
            while (!aux.isEmpty() && Point2D.ccw(aux.peek(), first, points[i]) <= 0) {
                first = aux.pop();
            }
            aux.push(first);
            aux.push(points[i]);
        }

    }

    public static void main(String[] args) {

        In sc = new In("src/homework/lesson6/data/rs1423.txt");
        //input56  horizontal100 grid6x6 input6 rs1423
        int T = sc.readInt();
        Point2D[] points = new Point2D[T];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.008);
        for (int i = 0; i < T; i++) {
            int x = sc.readInt();
            int y = sc.readInt();
            points[i] = new Point2D(x, y);
            points[i].draw();
            // System.out.println(points[i]);
        }

        ConvexHull cv = new ConvexHull(points);

        for (int i = 0; i < cv.aux.size() - 1; i++) {
            cv.aux.get(i).drawTo(cv.aux.get(i + 1));
            System.out.println(cv.aux.get(i) + " -> " + cv.aux.get(i + 1));

        }
        cv.aux.get(cv.aux.size() - 1).drawTo(cv.aux.get(0));
        System.out.println(cv.aux.get(cv.aux.size() - 1) + " -> " + cv.aux.get(0));

        StdDraw.show();
    }
}
