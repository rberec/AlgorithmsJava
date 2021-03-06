package com.rberec.weekThree;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rberec on 5/5/2017.
 */
public class BruteCollinearPoints
{
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {

        // check if array or any of the elements is null
        if (points == null) throw new NullPointerException();

        for (Point p1 :points)
            if (p1 == null) throw new NullPointerException();

        // create a local copy to avoid mutability
        Point[] pnts = points.clone();

        // sort the points and check if any two neighbors are identical points
        Arrays.sort(pnts);
        checkDuplicates(pnts);

        ArrayList<LineSegment> tmp = new ArrayList<LineSegment>();

        int n = pnts.length;

        for (int first = 0; first < n; ++first)
            for (int second = first + 1; second < n; ++second)
                for (int third = second + 1; third < n; ++third)
                    for (int fourth = third + 1; fourth < n; ++fourth)
                    {
                        double slope1 = pnts[first].slopeTo(pnts[second]);
                        double slope2 = pnts[first].slopeTo(pnts[third]);
                        double slope3 = pnts[first].slopeTo(pnts[fourth]);

                        if (Double.compare(slope1, slope2) == 0 && Double.compare(slope1, slope3) == 0)
                            tmp.add(new LineSegment(pnts[first], pnts[fourth]));
                    }

        segments = new LineSegment[tmp.size()];
        tmp.toArray(segments);
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return segments.clone();
    }

    private void checkDuplicates(Point[] points)
    {
        for (int i = 0; i < points.length - 1; ++i)
            if (points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY)
                throw new IllegalArgumentException();
    }

    public static void main(String[] args)
    {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
