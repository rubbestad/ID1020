package Searching;

import java.util.Scanner;
import java.io.*;

/*
 *  ------------------------------------------------------------
 *  Write an "index"-program which allows the user to ask
 *  questions "on which positions in the text  you find
 *  the word X". The program should list the position of
 *  all occurrences of X as answer to a query.
 *  This program answers queries in linear time and not O(lg(n))
 *  ------------------------------------------------------------
 */

public class L3A4 {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter search:");
        String word = input.nextLine();

        BufferedReader theText = new BufferedReader(new FileReader("gutenberg.txt"));

        int lineCounter = 0;
        String line;

        System.out.println("Searching for word '" + word + "'...");

        while((line = theText.readLine()) != null) {

            int indexFound = line.indexOf(word);
            lineCounter++;

            if(indexFound > -1) {
                System.out.println("Word was found at line " + lineCounter + " and position: " + indexFound);
            }

            int secondIndexFound = line.indexOf(word, indexFound + 1);
            if(secondIndexFound > indexFound + 1) {
                System.out.println("Word was found again at position: " + secondIndexFound);
            }
        }
        theText.close();
    }
}
