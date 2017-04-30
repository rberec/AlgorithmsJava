package com.rberec.weekTwo;

/**
 * Created by rberec on 4/28/2017.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;         // array of items
    private int n;            // number of elements on stack

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        items = (Item[]) new Object[2];
        n = 0;
    }

    // is the queue empty?
    public boolean isEmpty()
    {
        return n == 0;
    }

    // return the number of items on the queue
    public int size()
    {
        return n;
    }

    /**
     * Adds the item to this RandomizedQueue.
     * @param item the item to add
     */
    public void enqueue(Item item)
    {
        if (item == null) throw new NullPointerException();
        if (n == items.length) resize(2*items.length);    // double size of array if necessary
        items[n++] = item;                                // add item

    }

    /**
     * Removes and returns the item at random.
     * @return the item picked at random
     * @throws java.util.NoSuchElementException if this RandomizedQueue is empty
     */
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");

        // pick element at random from the RandomizeQueue
        int randomIndex = StdRandom.uniform(n);
        Item item = items[randomIndex];

        if (randomIndex != n-1)
            items[randomIndex] = items[n-1];

        items[n-1] = null;

        n--;
        shrink();
        return item;
    }

    // return (but do not remove) items random item
    public Item sample()
    {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");

        // pick element at random from the RandomizeQueue
        return items[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int[] randomIndices;
        private int i;

        public RandomArrayIterator() {
            randomIndices = StdRandom.permutation(n);
            i = 0;
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[randomIndices[i++]];
        }

    }

    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;

        // alternative implementation
        // items = java.util.Arrays.copyOf(items, capacity);
    }

    private void shrink()
    {
        // shrink size of array if necessary
        if (n > 0 && n == items.length/4) resize(items.length/2);
    }


    // unit testing (optional)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(5);
        rq.enqueue(10);
        rq.enqueue(321);
        rq.enqueue(543);
        rq.enqueue(90);
        rq.enqueue(7);

        for (int i: rq)
        {
            StdOut.print(i + " ");
        }
        StdOut.println();

        int n = rq.size();
        for (int i = 0; i < n; ++i)
        {
            int tmp = rq.dequeue();
            StdOut.print(tmp + " ");
        }
    }
}
