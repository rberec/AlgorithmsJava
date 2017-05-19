package com.rberec.weekFour;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/**
 * Created by rberec on 5/12/2017.
 */
public class Solver {

    private Stack<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        solution = new Stack<Board>();

        Candidate start = new Candidate(0, initial, null);
        MinPQ<Candidate> candidates = new MinPQ<Candidate>();
        candidates.insert(start);

        Candidate startAlter = new Candidate(0, initial.twin(), null);
        MinPQ<Candidate> candidatesAlter = new MinPQ<Candidate>();
        candidatesAlter.insert(startAlter);


        while (!(candidates.min().board.isGoal() || candidatesAlter.min().board.isGoal()))
        {
            Candidate tmp = candidates.delMin();

            for (Board neighbour : tmp.board.neighbors())
            {
                if (tmp.previousCandidate == null || !neighbour.equals(tmp.previousCandidate.board))
                {
                    candidates.insert(new Candidate(tmp.moves + 1, neighbour, tmp));
                }
            }

            Candidate tmpAlter = candidatesAlter.delMin();

            for (Board neighbour : tmpAlter.board.neighbors())
            {
                if (tmpAlter.previousCandidate == null || !neighbour.equals(tmpAlter.previousCandidate.board))
                {
                    candidatesAlter.insert(new Candidate(tmpAlter.moves + 1, neighbour, tmpAlter));
                }
            }
        }
        if (candidates.min().board.isGoal())
        {
            Candidate tmp = candidates.delMin();
            while (tmp != null)
            {
                solution.push(tmp.board);
                tmp = tmp.previousCandidate;
            }
        } else
        {
            solution = null;
        }
    }

    // is the initial board solvable?
    public boolean isSolvable()
    {
        return solution != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return (isSolvable()) ? solution.size() - 1 : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        return solution;
    }

    private class Candidate implements Comparable<Candidate>
    {
        private Board board;
        private Candidate previousCandidate;
        private int moves;
        private int manhattan;
        private int priority;

        public Candidate(int moves, Board board, Candidate previousCandidate)
        {
            this.moves = moves;
            this.board = board;
            this.previousCandidate = previousCandidate;
            this.manhattan = this.board.manhattan();
            this.priority = this.moves + this.manhattan;
        }

        public int compareTo(Candidate that)
        {
            return this.priority - that.priority;
        }
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
