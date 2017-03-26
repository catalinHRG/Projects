package laboratoare.Laborator2;

class DoublyLinkedList {

	private class Node {

		int data;
		Node next;
		Node previous;

		Node(int data, Node next, Node previous) {

			this.data = data;
			this.next = next;
			this.previous = previous;

		}

		void printData() {

			System.out.println(data);
		}
	}

	// -----------------------

	Node head;
	Node tail;
	int size = 0;

	void addFirst(int info) {

		Node bucket = new Node(info, head, null);

		if (head != null) {

			head.previous = bucket;

		}

		head = bucket;

		if (tail == null) {

			tail = bucket;
		}

		size++;

	}

	void addLast(int info) {

		if (tail == null) {

			addFirst(info);
		}

		if (tail != null) {

			Node bucket = new Node(info, null, tail);
			tail.next = bucket;
			tail = bucket;
			size++;
		}
	}
	
	void addAfter(int info, int reference){
		
		Node bucket = head;
		
		while(bucket != null){
			
			if(bucket.data == reference){
				
				if(bucket.equals(tail)){
					
					addLast(info);
					size++;
					return;
					
				}else{
					
					Node newLink = new Node(info, bucket.next, bucket);
					bucket.next = newLink;
					size++;
					return;
				}
				
			}

			bucket = bucket.next;
		}
		
		System.out.println("Elementul specificat nu a fost gasit in lista , nu s a realizat inserarea ! ");
	}
	
	void removeAfter(int reference){
		
		Node bucket = head;
		
		while(bucket != null){
			
			if(bucket.data == reference){
				
				if(bucket.equals(tail)){
					
					System.out.println("Am ajuns la sfarsitul listei , nu exista element pentru a fi eliminat");
					return;
					
				}else if(bucket.equals(tail.previous)){
				
					bucket.next = null;
					tail = bucket;
					size++;
					return;
					
				}else{
					
					bucket.next = bucket.next.next;
					bucket.next.previous = bucket;
					size++;
					return;
				}
			}
			
			bucket = bucket.next;
		}
		
	}
	
	void printForward() {

		Node bucket = head;

		System.out.println("Print de la cap la coada : ");

		while (bucket != null) {

			bucket.printData();
			bucket = bucket.next;
		}

		System.out.println("-------------------------------");
	}

	void printBackwards() {

		Node bucket = tail;

		System.out.println("Print de la coada la cap : ");

		while (bucket != null) {

			bucket.printData();
			bucket = bucket.previous;

		}

		System.out.println("-------------------------------");
	}

	void removeElement(int candidate) {

		Node bucket = head;

		if (bucket == null) {
			System.out.println("Lista goala");
			return;
		}

		while (bucket != null) {

			if (bucket.data == candidate) {

				if (bucket.equals(head)) {
					
					bucket.next.previous = null;
					head = bucket.next;
					size--;
					return;

				} else if (bucket.equals(tail)) {
					
					bucket.previous.next = null;
					tail = bucket.previous;
					size--;
					return;

				} else {
					
					bucket.previous.next = bucket.next;
					bucket.next.previous = bucket.previous;
					size--;
					return;
				}

			}

			bucket = bucket.next;
		}

		System.out.println("Elementul nu se afla in lista");
	}

	void removeLastElement() {

		if (tail == null) {
			
			System.out.println("Lista goala");
			return;
		}

		tail.previous.next = null;
		tail = tail.previous;
		size--;

	}

	void removeFirstElement() {

		head.next.previous = null;
		head = head.next;
		size--;
				
	}

	void printListSize() {

		System.out.println("Marimea liste : " + size);
	}

}
