package laboratoare.Laborator2;

import java.util.Random;

import laboratoare.Laborator2.ADT_Implementation.CDictionary;
import laboratoare.Laborator2.ADT_Implementation.CQueue;
import laboratoare.Laborator2.ADT_Implementation.CStack;

public class L2 {

	public static void testSinglyLinkedLists() {

		SinglyLinkedList firstList = new SinglyLinkedList();

		Random rng = new Random();

		for (int i = 0; i < 12; i++) {

			if (i == 9) {

				firstList.append(150);

			} else {

				firstList.append(rng.nextInt(100));
			}

		}

		System.out.println("Lista simplu inlantuita dupa initializare random este : \n" + firstList.toString("Normal"));

		System.out.println("Am adaugat elementul 500 dupa elementul 150");
		firstList.addAfter(500, 150);
		System.out.println(firstList.toString("Normal"));

		System.out.println("Am sters elementul care se afla dupa elementul 500");
		firstList.removeAfter(500);
		System.out.println(firstList.toString("Normal"));

		System.out.println("Am sters primul element");
		firstList.removeFirst();
		System.out.println(firstList.toString("Normal"));

		System.out.println("5 se afla in lista ? --> " + firstList.search(5));
		System.out.println("--------------------------------");

	}

	public static void testDoublyLinkedLists() {

		DoublyLinkedList secondList = new DoublyLinkedList();
		Random rng = new Random();

		for (int i = 0; i < 13; i++) {

			if (i == 9) {

				secondList.addFirst(150);

			} else if (i == 10) {

				secondList.addFirst(55);

			} else {

				secondList.addFirst(rng.nextInt(100));
			}

		}

		System.out.println("Elementele de la coada la cap : \n" + secondList.toString("Backward"));
		System.out.println("Elementele de la cap la coada : \n" + secondList.toString("Forward"));
		System.out.println("Am sters ultimul element din lista");
		secondList.removeLastElement();
		System.out.println(secondList.toString("Forward") + "\n");

		System.out.println("Am sters primul elemnt din lista");
		secondList.removeFirstElement();
		System.out.println(secondList.toString("Forward") + "\n");

		System.out.println("Am adaugat elementul 250 , dupa elementul 55 in lista");
		secondList.addAfter(250, 55);
		System.out.println(secondList.toString("Forward") + "\n");

		System.out.println("Am sters elementul ce se afla dupa 250 in lista");
		secondList.removeAfter(250);
		System.out.println(secondList.toString("Forward") + "\n");

	}

	public static void main(String[] args) {

		// Testare liste simplu si dublu inlantuite

		//L2.testSinglyLinkedLists();
		//L2.testDoublyLinkedLists();

		// Rezolvarea problemelor din laboratorul 2
		
		
		// Exercitiul 1

		CStack stack = new CStack();

		stack.push(5);
		stack.push(29);
		stack.push(33);
		stack.pop();
		stack.push(72);
		stack.push(12);
		stack.push(4);
		stack.pop();
		stack.push(1023);
		stack.push(6723);

		stack.printStack();

		// Exercitiul 2

		CQueue queue = new CQueue();

		queue.enqueue(13);
		queue.enqueue(52);
		queue.enqueue(472);
		queue.enqueue(1);
		queue.dequeue();
		queue.enqueue(123);
		queue.enqueue(4213);
		queue.enqueue(553);
		queue.dequeue();
		queue.enqueue(98);
		queue.enqueue(12344);

		queue.printQueue();

		// Exercitiul 3

		CDictionary dictionary = new CDictionary();
		dictionary.append(1, "Catalin");
		dictionary.append(2, "Claudiu");
		dictionary.append(3, "Georgel");
		dictionary.append(4, "Gigi");
		dictionary.append(5, "Ferentz");
		dictionary.append(6, "Sam");
		dictionary.append(7, "Gandalf");
		dictionary.append(8, "Nemtudom");
		dictionary.append(9, "Yghen");
		dictionary.append(10, "Frodo");
		dictionary.print();

		dictionary.delete(10);
		dictionary.delete(1);
		dictionary.print();
		dictionary.delete(2);
		dictionary.delete(5);
		dictionary.print();

		System.out.println(dictionary.search(20));

		dictionary.delete(7);
		dictionary.print();

		// Exercitiul 4

		DoublyLinkedList dList1 = new DoublyLinkedList();
		dList1.addFirst(5);
		dList1.addFirst(7);
		dList1.addFirst(6);
		dList1.addFirst(3);
		dList1.addFirst(1);
		dList1.addFirst(2);
		dList1.addFirst(4);
		System.out.println("Lista 1 : " + dList1.toString("Forward"));
		
		DoublyLinkedList dList2 = new DoublyLinkedList();
		dList2.addFirst(50);
		dList2.addFirst(51);
		dList2.addFirst(52);
		dList2.addFirst(53);
		dList2.addFirst(54);
		dList2.addFirst(55);
		dList2.addFirst(56);
		System.out.println("Lista 2 : " + dList2.toString("Forward"));

		dList1.makeUnion(dList2);
		System.out.println("Dupa reuniune , setul este \n" + dList1.toString("Forward") + "\n");

		// Exercitiul 5

		SinglyLinkedList sList1 = new SinglyLinkedList();
		SinglyLinkedList sList2 = new SinglyLinkedList();

		sList1.append(23);
		sList1.append(1);
		sList1.append(243);
		sList1.append(0);
		sList1.append(5213);

		sList2.append(123);
		sList2.append(0);
		sList2.append(12);
		sList2.append(53);
		sList2.append(2);
		sList2.append(623);
		
		// concatenare + sortare
		sList1.csNoSentinel(sList2);
		
		
		// Exercitiul 6
		
		SinglyLinkedList myList = new SinglyLinkedList();
		myList.append(1);
		myList.append(5123);
		myList.append(23);
		myList.append(12);
		myList.append(6123);
		myList.append(423);
		
		myList.invertSLL();

	}

}
