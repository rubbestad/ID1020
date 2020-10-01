package Fundamentals;

public class Stack {

	public static class Node {
		char data;
		Node next;
	}
	
	Node first;

	public void push(char data) {
		Node oldFirst = first;
		first = new Node();
		first.data = data;
		first.next = oldFirst;
	}
	
	public void pop() {
		if (first!=null) {
			System.out.print("[" + first.data + "] ");
			first = first.next;
			return;
		}
	}
	
	//Print iterative
	public void showIterative() {
		Node loopNode = first;
		//Until we get to the last node
		while (loopNode.next!=null) {
			//Print node data
			System.out.print("[" + loopNode.data + "] ");
			loopNode = loopNode.next;
		}
		//Print last node data
		System.out.print("[" + loopNode.data + "]\n");
	}

	//Print recursive
	public void showRecursive() {
		if (first != null) {
			pop();
			showRecursive();
		}
	}
}
