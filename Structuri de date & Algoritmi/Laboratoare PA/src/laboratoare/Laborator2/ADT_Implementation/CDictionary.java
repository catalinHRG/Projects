package laboratoare.Laborator2.ADT_Implementation;
import laboratoare.Laborator2.SinglyLinkedList;

public class CDictionary {
	
	SinglyLinkedList list;
	
	public CDictionary(){
		
		list = new SinglyLinkedList();
	}
	
	public void append(int key , String value){
		
		list.appendTuple(key, value);
	}
	
	public void delete(int key){
		
		System.out.println("Am eliminat elementul a carui cheie a fost " + key);
		list.removeTuple(key);
	}
	
	public String search(int key) {

		System.out.println("Elementul a carui cheie este " + key + " este : ");
		return list.searchForTuple(key);
	}
	
	public void print(){
		
		System.out.println("Elementele din dictionar sunt : \n" + list.toString("Dictionary"));
	}
}
