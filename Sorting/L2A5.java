package Sorting;

import java.util.Random;

/*
 *  -------------------------------------------------------------------------------------
 *  Compare the execution times for sorting large arrays of integers with
 *  insertion sort and merge sort. When should one select merge sort over insertion sort?
 *  Upload code, tests and a graphs depicting the execution times as a function of input
 *  -------------------------------------------------------------------------------------
 */

public class L2A5 {

    private static boolean less(int v, int w) {
        return v < w;
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    public static void mergeSort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    //Insertion sort
    public static void insertionSort(int[] a) {
        //Array length
        int N = a.length;

        //Exchange a[i] with smallest entry in a[i+1...N]
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void exch (int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {

        Random random = new Random();
        int arraySize = 1000;
        int[] insertionArray = new int[arraySize];
        int[] mergeArray = new int[arraySize];

        //Create array
        for (int i = 0; i < insertionArray.length; i++) {
            insertionArray[i] = random.nextInt();
        }

        for (int i = 0; i < mergeArray.length; i++) {
            mergeArray[i] = random.nextInt();
        }

        //Insertion sort time
        double sortingTimer = (double) System.currentTimeMillis();
        insertionSort(insertionArray);
        sortingTimer = (double) System.currentTimeMillis() - sortingTimer;
        System.out.println("Time to insertion sort " + insertionArray.length + " elements: " + sortingTimer + "ms");

        //Merge sort time
        sortingTimer = (double) System.currentTimeMillis();
        mergeSort(mergeArray);
        sortingTimer = (double) System.currentTimeMillis() - sortingTimer;
        System.out.println("Time to merge sort " + mergeArray.length + " elements: " + sortingTimer + "ms");
    }
}
