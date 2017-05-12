package com.rberec.weekFour;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/**
 * Created by rberec on 5/12/2017.
 */
public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {

    }

    // is the initial board solvable?
    public boolean isSolvable()
    {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return 42;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        return new ArrayList<Board>();
    }


    public static void main(String[] args)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        StdOut.println(initial);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
