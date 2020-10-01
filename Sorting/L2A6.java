package Sorting;

import java.util.Random;

/*
 *  ------------------------------------------------------------------
 *  Experiment with the cut-off to insertion sort in merge. How is the
 *  execution time affected by different values for the cut-off?
 *  A suitable range for cut-off values to test with could be [0-30].
 *  Upload code, tests and a graphs
 *  ------------------------------------------------------------------
 */

public class L2A6 {

    private static final int CUTOFF = 12;

    private static void merge(int[] src, int[] dst, int lo, int mid, int hi) {

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];
            else                           dst[k] = src[i++];
        }
    }

    private static void sort(int[] src, int[] dst, int lo, int hi) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        if (!less(src[mid+1], src[mid])) {
            for (int i = lo; i <= hi; i++) dst[i] = src[i];
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    // Merge sort
    public static void sort(int[] a) {
        int[] aux = a.clone();
        sort(aux, a, 0, a.length-1);

    }

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // is a[i] < a[j]
    private static boolean less(int a, int b) {
        return a < b;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int arraySize = 1000;
        int[] theArray = new int[arraySize];

        for (int i = 0; i < theArray.length; i++) {
            theArray[i] = random.nextInt();
        }

        double sortingTimer = (double) System.nanoTime();
        sort(theArray);
        sortingTimer = (double) System.nanoTime() - sortingTimer;
        System.out.println("Time to mergesort " + theArray.length + " elements\nwith cutoff value "
                + CUTOFF + ": " + sortingTimer + " ns");

    }
}
