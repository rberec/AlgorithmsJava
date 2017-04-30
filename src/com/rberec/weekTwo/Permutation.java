package com.rberec.weekTwo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by rberec on 4/28/2017.
 */
public class Permutation {

    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty())
        {
            rq.enqueue(StdIn.readString());
        }

        int n = 0;
        for (String s: rq)
        {
            if (n >= k) break;
            StdOut.println(s);
            ++n;
        }

    }
}
