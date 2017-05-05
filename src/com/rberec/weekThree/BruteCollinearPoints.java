package com.rberec.weekThree;

/**
 * Created by rberec on 5/5/2017.
 */
public class BruteCollinearPoints
{
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
        if (points == null) throw new NullPointerException();
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return segments;
    }


}
