package Fundamentals;

import java.util.Scanner;

/*
 *  ---------------------------------------------------------------------------
 *  Implement an ordered queue based on one of the implementations above.
 *  The elements stored in the queue should be integer values.
 *  The elements should be ordered at insertion so that all elements are stored
 *  in ascending order starting from when you insert the first element and in
 *  all following insertions. You should print the content of the list after
 *  each insertion/deletion of an element.
 *  ---------------------------------------------------------------------------
 */

public class L1A6 {
	
    private Node first;
    private Node last;
    private int n;
    
    //New list
    private L1A6() {
        first = null;
        last = null;
        n = 0;
    }
    
    //Node class
    private static class Node {
        private int item;
        private Node next = null;
    }


    //Adds to front of list in ascending order
    public void enqueue(int item) {
        Node input = new Node();
        input.item = item;

        //If list is empty
        if (n == 0) {
            first = last = input;
            n++;
            return;
        }

        //We will iterate our list with this
        Node current = first;

        //Checks if input should be inserted at front
        if (input.item < first.item) {
            first = input;
            first.next = current;
            n++;
            return;
        }

        //Iterate until current is at right position in list
        while ((int) input.item > (int) current.item) {

            /*
             * Means this is the last iteration, so we insert
             * current at last position and update pointers
             * */
            if (current == last) {
                last = input;
                current.next = last;
                n++;
                return;
            }

            //Checking if we should continue iterating or not
            if (current.next.item < input.item) {
                //Iterating ->
                current = current.next;
            } 
            else {
              
            	//If we find a larger, break and insert it
                break;
            }
        }

        //Inserting
        input.next = current.next;
        current.next = input;
        n++;
    }

    public void dequeueFront() {
        if (n == 1) {
            first = last = null;
            n = 0;
            return;
        }
        first = first.next;
        n--;
    }

    public void dequeueBack() {
        if (n == 1) {
            first = last = null;
            n--;
            return;
        }

        //Create two pointers to keep track of when we are at last element in list
        Node pointerLast = first.next;
        Node pointerSecondLast = first;

        //Iterate until pointerLast (points at last element) points to the first element
        while (pointerLast != last) {
            pointerSecondLast = pointerLast;
            pointerLast = pointerLast.next;
        }

        //Set the second last element to be the new last element, and update pointers accordingly
        last = pointerSecondLast;
        last.next = null;
        n--;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        Node current = first;
        System.out.println("");

        if (n == 1) {
            sb.append("[" + current.item + "]");
            return sb.toString();
        }

        while (current.next != null) {
            sb.append("[" + current.item + "], ");
            current = current.next;
        }
        sb.append("[" + current.item + "]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        L1A6 list = new L1A6();
        boolean running = true;
        while (running) {

            System.out.println("1: Add");
            System.out.println("2: Dequeue front");
            System.out.println("3: Dequeue back");
            System.out.println("Everything else: exit");
            System.out.print("\nEnter Action: ");

            int i = in.nextInt();
            switch (i) {

                case (1):
                    System.out.print("Enter number: ");
                    list.enqueue(in.nextInt());
                    break;

                case (2):
                    if (list.n == 0) {
                        break;
                    }
                    list.dequeueFront();
                    break;

                case (3):
                    if (list.n == 0) {
                        break;
                    }
                    list.dequeueBack();
                    break;

                default:
                    running = false;
            }

            if (list.n > 0) {
                System.out.println(list.print());
            } else {
                System.out.println("Empty List!\n");
            }
        }
        System.out.println("End");
    }
}