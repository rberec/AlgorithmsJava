package com.rberec.weekOne;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * Created by rberec on 4/21/2017.
 */
public class Percolation {

    private int nn;
    private boolean[] grid;
    private WeightedQuickUnionUF uf;

    public Percolation(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException();

        nn = n;
        grid = new boolean[nn * nn];

        for (int i = 0; i < nn; ++i)
            grid[i] = false;

        uf = new WeightedQuickUnionUF(nn * nn + 2);
    }

    public boolean isOpen(int row, int col)
    {
        checkInput(row, col);

        return grid[flatten(row, col)];
    }

    public boolean isFull(int row, int col)
    {
        checkInput(row, col);

        return (grid[flatten(row, col)] && uf.connected(0, flatten(row, col)+1));
    }

    public int numberOfOpenSites()
    {
        int sum = 0;
        for (boolean i : grid)
            if (i) sum += 1;

        return sum;
    }

    public void open(int row, int col)
    {
        checkInput(row, col);

        grid[flatten(row, col)] = true;

        if (row == 1) uf.union(0, flatten(row, col)+1);
        if (row == nn) uf.union(nn * nn + 1, flatten(row, col)+1);

        if ((row > 1) && isOpen(row-1, col))
            uf.union(flatten(row, col)+1, flatten(row-1, col)+1);

        if ((row < nn) && isOpen(row+1, col))
            uf.union(flatten(row, col)+1, flatten(row+1, col)+1);

        if ((col > 1) && isOpen(row, col-1))
            uf.union(flatten(row, col)+1, flatten(row, col-1)+1);

        if ((col < nn) && isOpen(row, col+1))
            uf.union(flatten(row, col)+1, flatten(row, col+1)+1);
    }

    public boolean percolates()
    {
        return uf.connected(0, nn * nn + 1);
    }

    private int flatten(int i, int j)
    {
        i -= 1;
        j -= 1;
        return (i * nn + j);
    }

    private void checkInput(int row, int col)
    {
        if (row > nn || row < 1 || col > nn || col < 1)
            throw new IndexOutOfBoundsException();
    }


}
