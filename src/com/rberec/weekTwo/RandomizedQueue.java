package com.rberec.weekTwo;

/**
 * Created by rberec on 4/28/2017.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;         // array of items
    private int n;            // number of elements on stack

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the queue empty?
    public boolean isEmpty()
    {
        return false;
    }

    // return the number of items on the queue
    public int size()
    {
        return 42;
    }

    // add the item
    public void enqueue(Item item)
    {

    }

    // remove and return a random item
    public Item dequeue()
    {
        return a[0];
    }

    // return (but do not remove) a random item
    public Item sample()
    {
        return a[0];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;

        public RandomArrayIterator() {
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
            return a[i++];
        }

    }

    // unit testing (optional)
    public static void main(String[] args)
    {

    }
}
