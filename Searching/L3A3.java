package Searching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

/*
 *  --------------------------------------------------------------------------
 *  Write a program that shows how evenly the built-in hashcode() function for
 *  strings in Java distributes the hashcodes for the words found in the text
 *  --------------------------------------------------------------------------
 */

public class L3A3 {

    public static void main(String[] args) throws FileNotFoundException {

        //Input
        Scanner stdin = new Scanner(System.in);
        System.out.println("Number of words to go through: ");
        int N = stdin.nextInt(); // how many words to compare and scan for
        stdin.nextLine();

        System.out.println("Minimum word length: ");
        int minlen = stdin.nextInt();
        stdin.nextLine();

        //Opening the text file to search for word of this length
        File file = new File("gutenberg.txt");
        Scanner text = new Scanner(file);

        //To obtain the words (Frequency counter)
        Hashtable<Integer, String> hash = new Hashtable<Integer, String>();

        int collisions = 0;
        int lim = 0;
        while (text.hasNext() && lim < N)
        {
            for (String word : text.next().split("\\s+")) //To split words to array
            {
                if(word.length() > 0) //To not count empty elements
                    lim++;
                if(word.length() >= minlen)
                    if(hash.get(word.hashCode() & 0x7fffffff % N) == null)
                        hash.put(word.hashCode() & 0x7fffffff % N,word);
                    else
                        collisions++;
            }
        }

        int consecutiveWords = 0;
        int maxConWords = 0;
        int consecutiveNulls = 0;
        int maxConNulls = 0;
        boolean lastWasWord = (hash.get(0) != null);

        for (int i=1; i<lim; i++)
        {
            if(hash.get(i) == null)
                if (lastWasWord) { //Break word streak
                    if (maxConWords < consecutiveWords)
                        maxConWords = consecutiveWords;
                    consecutiveNulls = 0;
                } else //Continue null streak
                    consecutiveNulls++;
            else
            if (lastWasWord) //Continue word streak
                consecutiveWords++;
            else { //Break null streak
                if (maxConNulls < consecutiveNulls)
                    maxConNulls = consecutiveNulls;
                consecutiveWords = 0;
            }
            lastWasWord = (hash.get(i) != null);
        }

        //Print results:
        System.out.println("\nLongest word streak: "+maxConWords);
        System.out.println("Longest null streak: "+maxConNulls);
        System.out.println("Out of "+lim+" indexes in total");
        System.out.println("\nNumber of collisions:\n"+collisions);
        System.out.println("Out of "+lim+" indexes in total");
    }
}

