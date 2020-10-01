#include <stdio.h>

/*
 *  -----------------------------------------------------------------
 *  Implement a function in C which takes an array of integers
 *  (both positive and negative) and orders the elements in
 *  the array so that all negative elements come before the positive.
 *  You are not allowed to sort the array - only collect all negative
 *  values first. The algorithm should only use O(1) extra memory
 *  ------------------------------------------------------------------
 */

int main() {
    printf("Number of elements: ");

    //Take input for array length
    int arrayLength;
    scanf("%d", &arrayLength);
    int theArray[arrayLength];

    int input;
    int counter = 0;
    int start = 0;
    int end = arrayLength - 1;

    //Take input for values
    printf("Input values: ");

    while (counter < arrayLength) {
        scanf("%d", &input);

        //If the input is negative, put it at the start
        if (input < 0) {
            theArray[start] = input;
            start++;
        }
        else {
            theArray[end] = input;
            end--;
        }
        counter++;
    }

    //Print array with separated negative and positive numbers
    for (int i = 0; i < arrayLength; i++) {
        printf("[");
        printf("%d", theArray[i]);
        printf("] ");
    }
    printf("\nDone");
}