package com.rberec.weekFour;


import java.util.ArrayList;

/**
 * Created by rberec on 5/12/2017.
 */
public class Board {

    private final int[][] board;
    private int size;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        board = blocks;
        size = board.length;
    }

    // board dimension n
    public int dimension()
    {
        return size;
    }

    // number of blocks out of place
    public int hamming()
    {
        return 42;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        return 42;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        return false;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        return new Board(board);
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        return new ArrayList<Board>();
    }

    // string representation of this board (in the output format specified below)
    public String toString()
    {
        String s = "";
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                s += board[i][j] + " ";
            }
            s += "\n";
        }

        return s;
    }

    public static void main(String[] args)
    {

    }
}
