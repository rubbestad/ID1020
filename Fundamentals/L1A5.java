package Fundamentals;

import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 *  --------------------------------------------------------------
 *  Implement a generalized queue which allows the user to remove
 *  the kth element from the queue. Assume the most recently added
 *  element has index 1. You should print the content of the list
 *  after each insertion/deletion of an element.
 *  --------------------------------------------------------------
 */

public class L1A5<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next = null;
        private Node<Item> prev = null;
    }

    private L1A5() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        return n;
    }

    //Adds to front of list
    public void enqueue(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;

        if (isEmpty()) {
            last = first;
        } 
        else { 
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    //Dequeues the kth element
    public void dequeue(int k) {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        //Update first
        if (k == 1) {
            if (n == 1) {
                first = last = null;
                n = 0;
                return;
            }
            first = first.next;
            first.prev = null;
            n--;
            return;
            
          //Update last
        } else if (k == n) { 
            last = last.prev;
            last.next = null;
            n--;
            return;
        }

        //Initiates pointers
        Node<Item> current = first;
        Node<Item> prevToCurrent;
        Node<Item> nextToCurrent;

        //Goes until current points to the kth element
        while (k > 1) {
            current = current.next;
            k--;
        }

        //Updates pointers
        nextToCurrent = current.next;
        prevToCurrent = current.prev;
        current = null;
        nextToCurrent.prev = prevToCurrent;
        prevToCurrent.next = nextToCurrent;
        n--;

        if (isEmpty()) last = null;   // to avoid loitering
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        Node<Item> current = first;

        if (size() == 1) {
            sb.append("[" + current.item + "]");
            return sb.toString();
        }
        System.out.println("Current queue: ");
        while (current.next != null) {
            sb.append("[" + current.item + "]-");
            current = current.next;
        }
        sb.append("[" + current.item + "]");
        return sb.toString();
    }

    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        L1A5<String> list = new L1A5<String>();
        boolean running = true;
        while (running) {
            System.out.println("1: Add");
            System.out.println("2: Delete Kth");
            System.out.println("Everything else: exit");
            int i = in.nextInt();
            switch (i) {
                case (1):
                    System.out.print("Enter item to front: ");
                    list.enqueue(in.next());
                    break;
                case (2):
                    System.out.print("Enter element to remove (kth): ");
                    int input = in.nextInt();
                    if (input <= 0 || input > list.n) {
                        System.out.println("Out of bounds, try again.");
                        break;
                    }
                    list.dequeue(input);
                    break;
                default:
                    running = false;
            }
            if (list.n > 0) {
                System.out.println(list.print());
            } else {
                System.out.println("Empty List");
            }
            System.out.println();
        }
        System.out.println("End");
    }

}
