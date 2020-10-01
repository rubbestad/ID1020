package Fundamentals;

/*
 *	---------------------------------------------------------------------
 * 	Implement the previous program in Java (both iterative and recursive)
 * 	using one of the ADTs suggested in Algorithms ch. 1.3
 * 	for the iterative version.
 *	---------------------------------------------------------------------
 */

import java.util.Scanner;

public class L1A2 {

	public static void main(String[] args) {
		Stack stack = new Stack();
		Scanner input = new Scanner(System.in);
		int index = 0;
		
		System.out.println("Enter your input:\n");
		String userInput = input.nextLine();
		
		//Don't run if no input
		if (userInput.length() == 0) {
			System.out.println("No input");
			return;
		}
		
		while(index < (userInput.length())) {
			stack.push((userInput.charAt(index++)));
		}
		//Print
		stack.showIterative();
		stack.showRecursive();
		System.out.println("\nDone!");
	}
}
