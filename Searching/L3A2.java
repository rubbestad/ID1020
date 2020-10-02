package Searching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 *  ---------------------------------------------------------------------
 *  Use the first N (N in the order of hundred words) words from the text
 *  to compare the running times of the ordered array ST (also known as
 *  binary search symbol table, to the Binary Search Tree algorithm
 *  Use the FrequencyCounter from page 372 as test program
 *  Show tables or graphs of your measurements.
 *  ---------------------------------------------------------------------
 */

public class L3A2 {

    private static int N = 0;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        System.out.println("Number of words: ");
        N = input.nextInt();

        System.out.println("Minimum word length: ");
        int minlen = input.nextInt();


        File file = new File("gutenberg.txt");
        Scanner text = new Scanner(file);

        BinarySearchST<String, Integer> BSST = new BinarySearchST<String, Integer>(minlen);
        BST<String, Integer> BST = new BST<String, Integer>();

        String bstAnswer = "";
        String bsstAnswer ="";
        long bstTime = 0;
        long bsstTime = 0;


        //Testing BSST
        bsstTime = System.currentTimeMillis();
        bsstAnswer = BSSTrunner(BSST, text, minlen);
        bsstTime = System.currentTimeMillis() - bsstTime;

        //Reset scanner
        text = new Scanner(file);

        //testing BST
        bstTime = System.currentTimeMillis();
        bstAnswer = BSTrunner(BST, text, minlen);
        bstTime = System.currentTimeMillis() - bstTime;


        //Results
        System.out.println("\nBST: \nThe word [" + bstAnswer + "] was found " + BST.get(bstAnswer) + " times");
        System.out.println("BST time: " + bstTime + " ms");
        System.out.println("\nBSST: \nThe word [" + bsstAnswer + "] was found " + BSST.get(bsstAnswer) + " times");
        System.out.println("BSST time: " + bsstTime + " ms");
    }

    static String BSTrunner(BST<String, Integer> BST, Scanner text, int minlen) {
        //Adding elements
        int lim = 0;
        while (text.hasNext() && lim < N) {
            //Split words to array
            for (String word : text.next().split("\\s+")) {
                if (word.length() > 0)
                    lim++;
                //If the word is longer than the minimum length (Frequency counter)
                if (word.length() >= minlen) {
                    if (!BST.contains(word))
                        BST.put(word, 1);
                    else
                        BST.put(word, BST.get(word) + 1);
                }
            }
        }
        //Finding the element
        String max = "";
        BST.put(max, 0);
        //Most common word
        String[] list = BST.keys();
        for (String word : list) {
            if (word != null && BST.get(word) > BST.get(max))
                max = word;
        }
        return max;
    }

    static String BSSTrunner(BinarySearchST<String, Integer> BSST, Scanner text, int minlen) {
        //Adding elements
        int lim = 0;
        while (text.hasNext() && lim < N) {
            //Split words to array
            for (String word : text.next().split("\\s+")) {
                if (word.length() > 0)
                    lim++;
                //If the word is longer than the minimum length (Frequency counter)
                if (word.length() >= minlen) {
                    if (!BSST.contains(word))
                        BSST.put(word, 1);
                    else
                        BSST.put(word, BSST.get(word) + 1);
                }
            }
        }
        //Finding the element
        String max = "";
        BSST.put(max, 0);
        //Most common word
        String[] list = BSST.keys();
        for (String word : list) {
            if (word != null && BSST.get(word) > BSST.get(max))
                max = word;
        }
        return max;
    }
}
