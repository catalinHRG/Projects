package laboratoare.Laborator2.ADT_Implementation;
import laboratoare.Laborator2.SinglyLinkedList;

public class CStack {
	
	SinglyLinkedList stack;
	
	public CStack(){
		
		stack = new SinglyLinkedList();
		
	}
	
	public void push(int data){
		
		stack.append(data);
		
	}
	
	public void pop(){
		
		if(stack.isEmpty()){
			
			System.out.println("Stiva este goala , nu se poate executa operatia de POP");
			
		}else{

			stack.removeFirst();
		}
	}
	
	public void printStack(){
		
		System.out.println(stack.toString("Normal"));
	}
	
}
