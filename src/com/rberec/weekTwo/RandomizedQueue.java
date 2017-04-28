package com.rberec.weekTwo;

/**
 * Created by rberec on 4/28/2017.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;     // top of deque

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // construct an empty randomized queue
    public RandomizedQueue()
    {

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
        return first.item;
    }

    // return (but do not remove) a random item
    public Item sample()
    {
        return first.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args)
    {

    }
}
