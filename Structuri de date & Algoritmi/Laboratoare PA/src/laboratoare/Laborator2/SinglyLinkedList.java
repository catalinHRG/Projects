package laboratoare.Laborator2;

public class SinglyLinkedList {

	Node head;
	Node sentinel;
	int size = 0;

	// Problema 5

	public void csNoSentinel(SinglyLinkedList list2) {

		// Concatenate lists

		Node bucket = list2.head;

		while (true) {

			if (bucket.next.equals(list2.sentinel)) {

				bucket.next = this.head;
				this.sentinel.next = list2.head;
				list2.sentinel = this.sentinel;
				break;
			}

			bucket = bucket.next;
		}

		System.out.println(
				"Lista rezultata dupa concatenarea celor doua listaeste este : \n" + list2.toString("Normal") + "\n");

		// Sorting with bubble sort

		bubbleSort4SLL(list2);

	}

	public void bubbleSort4SLL(SinglyLinkedList list) {

		boolean sorted = true;

		Node firstPointer = list.head;
		Node secondPointer = list.head.next;

		int temp;

		while (!firstPointer.next.equals(sentinel)) {

			if (firstPointer.data > secondPointer.data) {

				temp = firstPointer.data;
				firstPointer.data = secondPointer.data;
				secondPointer.data = temp;
				sorted = false;
			}

			firstPointer = secondPointer;
			secondPointer = secondPointer.next;
		}

		if (sorted) {

			System.out.println("Lista dupa sortare este \n" + list.toString("Normal") + "\n");

		} else {

			bubbleSort4SLL(list);
		}
	}

	// Problema 6
	
	public void invertSLL() {
		
		System.out.println(this.toString("Normal"));

		// raporatat la valorile din noduri , faci swapuri intre primul nod si ultimul
		// al doilea si pen ultimul , al 3 lea si ante penultimul etc etc @ liste de marime para
		// listele de marime sunt o problema

		System.out.println("Lista inversata este : \n" + this.toString("Normal"));
	}

	
	public void removeTuple(int keyReference) {

		if (size == 0) {
			System.out.println("Lista goala");
			return;
		}

		Node bucket = head;

		while (true) {

			if (keyReference == bucket.key && bucket.equals(head)) {

				head = bucket.next;
				sentinel.next = head;
				size--;
				break;

			} else if (keyReference == bucket.next.key) {

				bucket.next = bucket.next.next;
				size--;
				break;
			}

			bucket = bucket.next;
		}

	}

	public String searchForTuple(int keyReference) {

		Node bucket = head;

		while (!bucket.equals(sentinel)) {

			if (bucket.key == keyReference) {

				return bucket.value;

			}

			bucket = bucket.next;
		}

		return "Entry not found";
	}
	
	public void appendTuple(int key, String value) {

		Node bucket = new Node(key, value, head, null);
		head = bucket;
		sentinel.next = head;
		size++;
	}

	public SinglyLinkedList() {

		sentinel = new Node(0, null, null);
		head = sentinel;
	}

	public boolean isEmpty() {

		if (size == 0) {

			return true;

		} else {

			return false;
		}

	}

	public void append(int info) {

		Node bucket = new Node(info, head, null);
		head = bucket;
		sentinel.next = head;
		size++;
	}

	public void addAfter(int info, int reference) {

		Node bucket = head;

		while (!bucket.equals(sentinel)) {

			if (bucket.data == reference) {

				Node newNode = new Node(info, bucket.next, null);
				bucket.next = newNode;
				size++;

			}

			bucket = bucket.next;
		}
	}

	public void removeAfter(int reference) {

		if (size == 0) {
			System.out.println("Lista goala");
			return;
		}

		Node bucket = head;

		while (!bucket.equals(sentinel)) {

			if (bucket.data == reference) {

				bucket.next = bucket.next.next;
				size--;
			}

			bucket = bucket.next;
		}
	}

	public void removeFirst() {

		if (size == 0) {
			System.out.println("Lista goala");
			return;
		}
		head = head.next;
		sentinel.next = head;
		size--;
	}

	public void removeLast() {

		if (size == 0) {
			System.out.println("Lista goala");
			return;
		}

		Node bucket = head;

		while (!bucket.equals(sentinel)) {

			if (bucket.next.next.equals(sentinel)) {

				System.out.println("Am eliminat elementul : " + bucket.next.data);
				bucket.next = sentinel;
				return;
			}

			bucket = bucket.next;
		}
	}

	public boolean search(int info) {

		Node bucket = head;
		boolean wasFound = false;

		while (!bucket.equals(sentinel)) {

			if (bucket.data == info) {

				wasFound = true;
				break;
			}

			bucket = bucket.next;
		}

		return wasFound;
	}

	public void printListSize() {

		System.out.println("Marimea listei este : " + size);
	}

	public String toString(String mode) {

		StringBuffer sb = new StringBuffer();
		Node bucket = head;

		if (mode == "Normal") {

			while (!bucket.equals(sentinel)) {

				sb.append(bucket.data);
				sb.append(" ");
				bucket = bucket.next;

			}

			return sb.toString();

		} else if (mode == "Dictionary") {

			while (!bucket.equals(sentinel)) {

				sb.append("[ " + bucket.key + ", " + bucket.value + " ]");
				sb.append("  ");
				bucket = bucket.next;

			}

			return sb.toString();

		} else {
			System.out.println("Proper mode not inserted");
			return null;
		}
	}

}
