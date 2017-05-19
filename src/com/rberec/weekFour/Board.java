package com.rberec.weekFour;


import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;


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
        if (blocks == null) throw new NullPointerException();

        size = blocks.length;

        board = new int[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                board[i][j] = blocks[i][j];
    }

    // board dimension n
    public int dimension()
    {
        return size;
    }

    // number of blocks out of place
    public int hamming()
    {
        int oop = 0;

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                if (((i != size -1) || (j != size -1)) && board[i][j] != convertDim(i, j)) ++oop;

        return oop;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        int total = 0;
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                total += mnht(i, j, board[i][j]);
        return total;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        int tmpX1 = StdRandom.uniform(size);
        int tmpY1 = StdRandom.uniform(size);
        int tmpX2 = StdRandom.uniform(size);
        int tmpY2 = StdRandom.uniform(size);

        int[][] tmp = copyBoard();

        while (tmp[tmpX1][tmpY1] == tmp[tmpX2][tmpY2]  || tmp[tmpX1][tmpY1] == 0 || tmp[tmpX2][tmpY2] == 0)
        {
            tmpX1 = StdRandom.uniform(size);
            tmpY1 = StdRandom.uniform(size);
            tmpX2 = StdRandom.uniform(size);
            tmpY2 = StdRandom.uniform(size);
        }

        tmp[tmpX1][tmpY1] = tmp[tmpX2][tmpY2];
        tmp[tmpX2][tmpY2] = board[tmpX1][tmpY1];
        return new Board(tmp);
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        if (this.size != that.size) return false;

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                if (this.board[i][j] != that.board[i][j]) return false;

        return true;

    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        Stack<Board> newBoards = new Stack<Board>();
        int i = 0;
        int j = 0;

        outerloop:
        for (i = 0; i < size; ++i)
            for (j = 0; j < size; ++j)
                if (board[i][j] == 0) break outerloop;

        if (i < size - 1)
        {
            int[][] newBoard = copyBoard();
            newBoard[i][j] = newBoard[i + 1][j];
            newBoard[i + 1][j] = 0;
            newBoards.push(new Board(newBoard));
        }
        if (j < size - 1)
        {
            int[][] newBoard = copyBoard();
            newBoard[i][j] = newBoard[i][j + 1];
            newBoard[i][j + 1] = 0;
            newBoards.push(new Board(newBoard));
        }
        if (i > 0)
        {
            int[][] newBoard = copyBoard();
            newBoard[i][j] = newBoard[i - 1][j];
            newBoard[i - 1][j] = 0;
            newBoards.push(new Board(newBoard));
        }
        if (j > 0)
        {
            int[][] newBoard = copyBoard();
            newBoard[i][j] = newBoard[i][j - 1];
            newBoard[i][j - 1] = 0;
            newBoards.push(new Board(newBoard));
        }

        return newBoards;
    }

    // string representation of this board (in the output format specified below)
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }

        return s.toString();
    }

    private int convertDim(int i, int j)
    {
        return i*size + j + 1;
    }

    private int mnht(int i, int j, int val)
    {
        if (val == 0) return 0;
        int retX = i - (val - 1)/size;
        int retY = j - (val - 1) % size;
        retX = (retX > 0) ? retX : -retX;
        retY = (retY > 0) ? retY : -retY;
        return retX + retY;
    }

    private int[][] copyBoard()
    {
        int[][] ret = new int[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                ret[i][j] = board[i][j];

        return ret;
    }

    public static void main(String[] args)
    {

    }
}
