package laboratoare.Laborator2;

public class Node {
	
	int data; // SLL  & DLL
	
	// for dictionary only
	int key;
	String value;
	
	Node next; // SLL & DLL
	Node previous; // for DLL only
	
 	Node(int data, Node next, Node previous) {

		this.data = data;
		this.next = next;
		this.previous = previous;
	}
 	
 	Node(int key, String value, Node next, Node previous){
 		
 		this.key = key;
 		this.value = value;
 		this.next = next;
 		this.previous = previous;
 		
 	}

}
