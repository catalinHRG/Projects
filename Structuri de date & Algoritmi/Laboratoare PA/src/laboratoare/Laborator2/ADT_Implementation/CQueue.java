package laboratoare.Laborator2.ADT_Implementation;
import laboratoare.Laborator2.SinglyLinkedList;

public class CQueue {
	
	SinglyLinkedList queue ;
	
	public CQueue(){
		
		queue = new SinglyLinkedList();
	}
	
	public void enqueue(int data){
		
		queue.append(data);
	}
	
	public void dequeue(){
		
		queue.removeLast();
	}
	
	public void printQueue(){
		
		System.out.println(queue.toString("Normal"));
	}

}
