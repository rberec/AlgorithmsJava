package com.rberec.weekThree;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rober on 5/6/2017.
 */
public class FastCollinearPoints
{
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)
    {
        // check if array or any of the elements is null
        if (points == null) throw new NullPointerException();

        for (Point p1 :points)
            if (p1 == null) throw new NullPointerException();

        // create a local copy to avoid mutability
        Point[] pnts = points.clone();

        Arrays.sort(pnts);
        checkDuplicates(pnts);

        ArrayList<LineSegment> tmp = new ArrayList<LineSegment>();

        Point[] pntsCopy = pnts.clone();

        for (Point point : pntsCopy)
        {
            Arrays.sort(pnts, point.slopeOrder());
            double[] slopes = new double[pnts.length];
            for (int i = 0; i < pnts.length; ++i) slopes[i] = point.slopeTo(pnts[i]);

            int count = 1;
            for (int i = pnts.length - 2; i >= 0; --i)
            {
                if (Double.compare(slopes[i], slopes[i+1]) == 0)
                    ++count;
                else
                {
                    if (count >= 3)
                    {
                        Arrays.sort(pnts, i + 1, i + count + 1);
                        Point minPoint = (point.compareTo(pnts[i + 1]) < 0) ? point : pnts[i + 1];
                        Point maxPoint = (point.compareTo(pnts[i + count]) > 0) ? point : pnts[i + count];
                        if (point.compareTo(minPoint) == 0)
                            tmp.add(new LineSegment(minPoint, maxPoint));
                    }
                    count = 1;
                }
            }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

}
