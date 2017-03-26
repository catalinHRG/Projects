package laboratoare.Laborator2;

import java.util.Random;

public class L2 {

	public static void main(String[] args) {
		
		SinglyLinkedList firstList = new SinglyLinkedList();

		Random rng = new Random();

		for (int i = 0; i < 13; i++) {

			firstList.append(rng.nextInt(100));
			if (i == 9) {
				firstList.append(150);
			}
		}
		
		System.out.println("Lista simplu inlantuita dupa initializare random este :");
		firstList.print();
		
		System.out.println("Am adaugat elementul 500 dupa elementul 150");
		firstList.addAfter(500, 150);
		firstList.print();
		
		System.out.println("Am sters elementul care se afla dupa elementul 500");
		firstList.removeAfter(500);
		firstList.print();
		
		System.out.println("Am sters primul element");
		firstList.removeFirst();
		firstList.print();
		
		System.out.println("5 se afla in lista ? --> " + firstList.search(5));
		System.out.println("--------------------------------");

		// -------------------------------------------------------------------
		
		System.out.println("Lista dublu inlantuita dupa initializare este : \n");
		
		DoublyLinkedList secondList = new DoublyLinkedList();
		
		for (int i = 0; i < 13; i++) {

			secondList.addFirst(rng.nextInt(100));
			if (i == 9) {
				
				secondList.addFirst(150);
				
			}else if(i == 10){
				
				secondList.addFirst(55);
			}
		}
		secondList.addFirst(399);
	
		secondList.printBackwards();
		secondList.printForward();
		
		
		System.out.println("\nAm adaugat la sfarsitul listei pe 800");
		secondList.addLast(800);
		secondList.printForward();
		
		System.out.println("Am sters elementul 150 din lista");
		secondList.removeElement(150);
		secondList.printForward();
		
		System.out.println("Am sters elementul 800 din lista");
		secondList.removeElement(800);
		secondList.printForward();
		
		System.out.println("Am sters elementul 399 din lista");
		secondList.removeElement(399);
		secondList.printForward();
		
		System.out.println("Am sters ultimul element din lista");
		secondList.removeLastElement();
		secondList.printForward();
		
		System.out.println("Am sters primul elemnt din lista");
		secondList.removeFirstElement();
		secondList.printForward();
		
		System.out.println("Am adaugat elementul 250 , dupa elementul 55 in lista");
		secondList.addAfter(250, 55);
		secondList.printForward();
		
		System.out.println("Am sters elementul ce se afla dupa 250 in lista");
		secondList.removeAfter(250);
		secondList.printForward();
		
	}

}
