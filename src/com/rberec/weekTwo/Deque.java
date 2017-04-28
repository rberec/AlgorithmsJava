package com.rberec.weekTwo;

/********************************************************************************
*  Created by rberec on 4/27/2017.
*  Compilation:  javac LinkedStack.java
*  Execution:    java LinkedStack < input.txt
*  Dependencies: StdIn.java StdOut.java
*  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
*
*  A generic stack, implemented using a linked list. Each stack
*  element is of type Item.
*
*  % more tobe.txt
*  to be or not to - be - - that - - - is
*
*  % java LinkedStack < tobe.txt
*  to be not that or be (2 left on stack)
*
******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code Deque} class represents a double-ended queue or deque (pronounced "deck") of
 *  generic items.
 *  It supports the usual <em>addFirst</em>, <em>addLast</em> and <em>removeFirst</em> and <em>removeLast</em>
 *  operations, along with methods for peeking at the top item, testing if the deque is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a doubly-linked list with a non-static nested class for
 *  linked-list nodes.
 *  The <em>addFirst</em>, <em>addLast</em>, <em>removeFirst</em>, <em>removeLast</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *
 *  @author rberec
 */
public class Deque<Item> implements Iterable<Item> {
    private int n;          // size of the deque
    private Node first;     // top of deque
    private Node last;      // bottom of deque

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    /**
     * Initializes an empty deque.
     */
    public Deque() {
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    /**
     * Is this deque empty?
     * @return true if this deque is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the deque.
     * @return the number of items in the deque
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to the top of this deque.
     * @param item the item to add
     */
    public void addFirst(Item item) {

        if (item == null)
            throw new NullPointerException();

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (oldfirst != null)
            oldfirst.previous = first;

        if (isEmpty())
            last = first;

        n++;
        assert check();
    }

    /**
     * Adds the item to the bottom of this deque.
     * @param item the item to add
     */
    public void addLast(Item item) {

        if (item == null)
            throw new NullPointerException();

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        last.next = null;
        if (oldlast != null)
            oldlast.next = last;

        if (isEmpty())
            first = last;

        n++;
        assert check();
    }

    /**
     * Removes and returns the top item in deque.
     * @return the item from top of deque
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        // first.previous = null;
        n--;
        assert check();
        return item;                   // return the saved item
    }

    /**
     * Removes and returns the bottom item in deque.
     * @return the item from bottom of deque
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;        // save item to return
        last = last.previous;            // delete last node
        // last.next = null;
        n--;
        assert check();
        return item;                   // return the saved item
    }

    /**
     * Returns an iterator to this deque that iterates through the items in FIFO order.
     * @return an iterator to this deque that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
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

    // check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
            if (last != null)  return false;
        }
        else if (n == 1) {
            if (first == null)      return false;
            if (last == null)       return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null)      return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }

    /**
     * Unit tests the {@code Deque} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.removeFirst();
        StdOut.println(deque.size());

        Iterator<Integer> it = deque.iterator();
        it.next();
        boolean x = it.hasNext();
        it.next();

        /*
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") && !item.equals("*"))
                deque.addFirst(item);
            else if (!deque.isEmpty())
            {
                if (item.equals("-"))
                    StdOut.print(deque.removeFirst() + " ");
                if (item.equals("*"))
                    StdOut.print(deque.removeLast() + " ");
            }
        }

        StdOut.println("(" + deque.size() + " left on stack)");
        */
    }
}

