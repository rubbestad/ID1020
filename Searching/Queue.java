package Searching;

import java.util.Iterator;

public class Queue implements Iterable {

    private Node first;		// link to least recently added node
    private Node last;		// link to most recently added node
    private int N;      	// number of items on the queue
    private static class Node {	// nested class to define nodes
        String 	item;
        Node 	next,
                previous;
    }

    public boolean isEmpty() {
        return first == null;
    }  // Or: N == 0.

    public int size() {
        return N;
    }

    public void enqueue(String item) {  // Add item to the end of the list.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    public String dequeue() {  // Remove item from the beginning of the list.
        if(isEmpty()) {
            last = null;
            return null;
        }
        String item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    public Iterator<String> iterator() {
        return new ListIterator();
    }

    class ListIterator implements Iterator<String>
    {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String toString() {
            return current.item.toString();
        }

        public void remove() { }

        public String next() {
            String item = current.item;
            current = current.next;
            return item;
        }
    }

}
