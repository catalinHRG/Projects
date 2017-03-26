package laboratoare.Laborator2;

class SinglyLinkedList {

	private class Node {

		int data;
		Node next;

		Node(int data , Node next) {

			this.data = data;
			this.next = next;
		}

		void printData(int index) {

			System.out.println("Elementul nr " + index + " este = " + data);

		}
	}
	
	
	// ------------------------
	
	Node head; 
	int size = 0;

	void append(int info) {

		Node bucket = new Node(info, head);
		head = bucket;
		size++;
	}

	void addAfter(int info, int reference) {

		Node bucket = head;

		while (bucket != null) {

			if (bucket.data == reference) {
				
				Node newNode = new Node(info, bucket.next);
				bucket.next = newNode;
				size++;
				break;
				
			}
			
			bucket = bucket.next;
		}
	}

	void removeAfter(int reference) {

		Node bucket = head;

		while (bucket != null) {

			if (bucket.data == reference) {
				
				bucket.next = bucket.next.next;
				size--;
			}
			
			bucket = bucket.next;
		}
	}

	void removeFirst(){
		
		head = head.next;
		size--;
	}
	
	boolean search(int info) {

		Node bucket = head;
		boolean wasFound = false;

		while (bucket != null) {

			if (bucket.data == info) {

				wasFound = true;
				break;
			}

			bucket = bucket.next;
		}

		return wasFound;
	}

	void printListSize(){
		
		System.out.println("Marimea listei este : " + size);
	}
	
	void print() {

		Node bucket = head;
		int counter = 1;
		
		while (bucket != null) {

			bucket.printData(counter);
			bucket = bucket.next;
			counter++;
		}
		
		System.out.println("-------------------------------");
	}

}
