package laboratoare.Laborator4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import laboratoare.Laborator3.Graph_Implementation.UGraph;

public class L4 {

	public static ArrayList<ArrayList<Integer>> generateMatrix(int rows, int columns, int maxInt) {

		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp;
		Random rng = new Random();

		for (int i = 0; i < rows; i++) {

			temp = new ArrayList<Integer>();

			for (int j = 0; j < columns; j++) {
				
				temp.add(new Integer(rng.nextInt(maxInt)));
			}

			Collections.sort(temp);
			lists.add(temp);
		}

		return lists;
	}

	public static void main(String[] args) {

		// 'Nodes/Pointers to Nodes' representation of binary trees .

		UGraph binaryTree = new UGraph();
		binaryTree.generateVertices(10, 1);
		binaryTree.addEdge("1", "2", 1);
		binaryTree.addEdge("1", "3", 1);
		binaryTree.addEdge("2", "4", 1);
		binaryTree.addEdge("2", "5", 1);
		binaryTree.addEdge("3", "6", 1);
		binaryTree.addEdge("3", "7", 1);
		binaryTree.addEdge("7", "8", 1);
		binaryTree.addEdge("8", "9", 1);
		binaryTree.addEdge("8", "10", 1);
		
		System.out.println("Parcurgeri de arbori binari :");
		
		//System.out.println(binaryTree.toString());
		
		// parcurgere pre ordine : Radacina , Stanga , Dreapta
		System.out.println("Pre ordine :");
		binaryTree.depthFirstTraversal();

		// pargurgere post ordine : Stanga , Dreapta , Radacina
		System.out.println("Post ordine :");
		binaryTree.postOrderTraversal();

		// parcurgere in ordine : Stanga , Radacina , Dreapta
		System.out.println("In ordine");
		binaryTree.inOrderTraversal();

		
		// 'Array' representation for Heap

		List<Integer> table = new ArrayList<Integer>();
		Random rng = new Random();

		for (int i = 0; i < 20; i++) {
			table.add(rng.nextInt(150));
		}

		HeapManager hm = new HeapManager();
		
		System.out.println("******************** Max Heap ******************************");
		
		hm.makeMaxHeap(table);
		System.out.println("Max heap " + "\n" + table.toString());
		hm.increaseKeyValue(table, 200, 15, true);
		System.out.println("Am incercat sa maresc valoarea elementului de la pozitia " + 15 + "\n" + table.toString());
		hm.deleteElement(table, 29, true);
		System.out.println("Am incercat sa elimin elementul de cheie " + 29 + "\n" + table.toString());
		
		System.out.println("******************** Min Heap ******************************");
		
		hm.makeMinHeap(table);
		System.out.println("Min heap " + "\n" + table.toString());
		hm.increaseKeyValue(table, 200, 12, false);
		System.out.println("Am incercat sa maresc valoarea elementului de la pozitia " + 
							12 + "\n" + table.toString());
		hm.deleteElement(table, 54, false);
		System.out.println("Am incercat sa elimin elementul de cheie " + 54 + "\n" + table.toString());

		System.out.println("********************** Interclasare Optima -- Merge / Sort ***************************");
		
		int numberOfLists, numberOfElementsPerList, rngBoundary, sortedListSize;
		
		numberOfLists = 5;
		numberOfElementsPerList = 3;
		sortedListSize = numberOfLists * numberOfElementsPerList;
		rngBoundary = 50;
		
		ArrayList<ArrayList<Integer>> lists = L4.generateMatrix(numberOfLists,numberOfElementsPerList,rngBoundary);

		System.out.println("Listele inainte de sortare " + lists.toString());
		List<Integer> sortedList = hm.mergeAndSortLists(lists);
		System.out.println("Lista sortata obtinuta din listele de mai sus " + sortedList.toString());
		System.out.println("Lista sortata trebuie sa aiva " + sortedListSize + " elemente");
		System.out.println("Marimea listei sortate este " + sortedList.size());
		
	}
}