package com.rberec.weekOne;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by rober on 4/22/2017.
 */
public class PercolationStats
{
    private double mean;
    private double stddev;
    private double cLow;
    private double cHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int nn, int trials)
    {
        if (nn <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        double[] fractions = new double[trials];

        for (int t = 0; t < trials; ++t) {
            Percolation p = new Percolation(nn);
            int n = 0;
            while (!p.percolates()) {
                int i;
                int j;
                do {
                    i = StdRandom.uniform(1, nn + 1);
                    j = StdRandom.uniform(1, nn + 1);
                } while (p.isOpen(i, j));

                p.open(i, j);
                n++;
            }
            fractions[t] = ((double) n)/((double) (nn*nn));
        }

        mean = StdStats.mean(fractions);
        stddev = StdStats.stddev(fractions);
        cLow = mean - 1.96* stddev / Math.sqrt(trials);
        cHi = mean + 1.96* stddev / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return cLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return cHi;
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.print("Mean = ");
        StdOut.println(ps.mean());
        StdOut.print("stddev = ");
        StdOut.println(ps.stddev());
        StdOut.print("95% confidence interval = [");
        StdOut.print(ps.confidenceLo());
        StdOut.print(", ");
        StdOut.print(ps.confidenceHi());
        StdOut.println("]");


    }
}
