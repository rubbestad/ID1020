package Fundamentals;

/*
 *	---------------------------------------------------------
 *	Implement a generic iterable FIFO-queue based on a
 *	double linked circular list. You should print the content
 *	of the list after each insertion/deletion of an element.
 *	---------------------------------------------------------
 */

public class L1A3 {

	public static void main(String[] args) {
		//Set up queue
		Queue<Character> queue = new Queue<Character>();
				
		//Enqueue and dequeue for testing
		queue.enqueue('0');
		queue.enqueue('1');
		queue.enqueue('3');
		queue.enqueue('5');
		queue.dequeue();
		queue.enqueue('7');
		queue.dequeue();
		queue.dequeue();
		queue.enqueue('9');
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
				
		System.out.println("End");
	}
			

	public static class Queue <Item> {
		
		public class Node {
			Item data;
			Node next;
			Node prev;
		}
		
		//Declaring first and last
		Node first;
		Node last;

		//Add node in queue	
		public void enqueue(Item data) {
			//Set first entry to first position in queue
			if(isEmpty()) {
				Node firstEntry = new Node();
				firstEntry.data = data;
				first = firstEntry;
				last = firstEntry;
				//Print queue
				showQueue();
				return;
			}
			
			//Add new data to queue in next position
			Node newEntry = new Node();
			newEntry.data = data;
			
			//Temporary variable
			Node temporary = new Node();
			//Hold node in last
			temporary = last;
			last.next = newEntry;
			last = newEntry;
			//Set last.prev to the old last node
			last.prev = temporary;
			temporary = null;

			//Print queue
			System.out.println("\nEnqueued '" + data + "'\n");
			showQueue();
		}
		
		//Remove node from queue
		public void dequeue() {
			if(!isEmpty()) {
				Node thisNode = new Node();
				thisNode = first;
				System.out.println("\nDequeued " + "'" + first.data + "'\n");
				first = null;
				first = thisNode.next;
				thisNode = null;
				showQueue();
				return;
			}
			//Message if queue is empty
			System.out.println("Queue is empty\n");
		}

		public boolean isEmpty() {
			return first==null;
		}

		public void showQueue() {
			Node thisNode = first;
			//If queue is empty
			if (thisNode == null) {
				System.out.println("Queue is empty\n");
				return;
			}
			
			System.out.print("Queue - ");
			
			//Until we get to the last node
			while(thisNode.next!=null) {
				//Print the node data
				System.out.print("[" + thisNode.data + "]-");
				//Set node to next node and repeat
				thisNode = thisNode.next;
			}
			//Print last node
			System.out.print("[" + thisNode.data + "]\n");
		}
	}
}