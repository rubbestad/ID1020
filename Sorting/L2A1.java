package Sorting;

import java.util.Scanner;

/*
 *  ----------------------------------------------------------
 *  Implement insertion sort. Augment the sorting process
 *  so that all the content of the array that is being sorted
 *  is printed after each inner loop iteration. Write a unit
 *  test in main() which allows the user to define the size
 *  of the input (N) and then input (N) integers from stdin
 *  which is to be sorted.
 *  ----------------------------------------------------------
 */

public class L2A1 {

    public static void sort(int[] a) {
        //Array length
        int N = a.length;

        //Exchange a[i] with smallest entry in a[i+1...N]
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
            //Print every sorting iteration
            System.out.print("Sort #" + i + ": ");

            for (int k = 0; k < a.length; k++) {
                System.out.print("[" + a[k] + "], ");
            }

            System.out.print("\n");
        }
    }
    //Compare two values
    public static boolean less (int v, int w) {
        return v < w;
    }
    //Swap two values
    public static void exch (int[] a, int i, int j) {
       int t = a[i];
       a[i] = a[j];
       a[j] = t;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Size of the array: ");
        int arrayLength = input.nextInt();

        int[] theArray = new int[arrayLength];

        System.out.print("Enter integers: ");

        for (int k = 0; k < arrayLength; k++) {
            theArray[k] = input.nextInt();
        }

        System.out.println("\nSorting...");
        sort(theArray);
        System.out.println("\nDone");
    }
}
