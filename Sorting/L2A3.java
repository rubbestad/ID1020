package Sorting;

import java.util.Scanner;

/*
 *  ------------------------------------------------------
 *  Add a method which counts the number of inversions
 *  in the input array and prints a list of all inversions
 *  Time complexity: O(n^2)
 *  ------------------------------------------------------
 */

public class L2A3 {

    public static int swaps = 0;

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
        //Add number of swaps
        swaps++;
    }

    static int numOfInversions (int[] a, int arrayLength) {
        int inversionCounter = 0;
        //Find the inversions
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (a[i] > a[j]) {
                    inversionCounter++;
                    System.out.print("(" + a[i] + "," + a[j] + ") ");
                }
            }
        }
        System.out.print("\n");
        return inversionCounter;
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

        int inversions = numOfInversions(theArray, arrayLength);
        System.out.println("Number of inversions: " + inversions);

        System.out.println("\nSorting...");
        sort(theArray);
        System.out.println("\nNumber of swaps: " + swaps);
        System.out.println("\nDone");
    }
}