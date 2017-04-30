package laboratoare.Laborator4;

import java.util.ArrayList;
import java.util.List;

public class HeapManager {

	public ArrayList<Integer> mergeAndSortLists(ArrayList<ArrayList<Integer>> listOfLists) {

		Integer rootValue;
		int i, j;
		
		Tuple<Integer> pair;
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		List<Integer> table = new ArrayList<Integer>();
		
		// populate the table with the first element of each list in the listOfLists
		for (ArrayList<Integer> list : listOfLists) { table.add(list.get(0)); }
		
		// convert 'table' into a min heap
		makeMinHeap(table);
		
		while( !table.isEmpty() ){
			
			System.out.println("---------------------");
			System.out.println("Heap curent : " + table.toString());
			rootValue = removeRoot(table, false);
			System.out.println("Am eliminat rootul " + rootValue + "\n" + "Heap dupa eliminarea rootului :" 
									+ table.toString());
			pair = findKeyPosition(listOfLists, rootValue);
			System.out.println("Indexul la care se afla rootul , in lista de liste : " + pair.toString());
			System.out.println("Lista din care face parte rootul este " + listOfLists.get(pair.getFirstElement()));
			sortedList.add(rootValue);
			
			// indeces of the rootValue within the matrix 'listOfLists'
			i = pair.getFirstElement(); // the i th list
			j = pair.getSecondElement(); // the j th element within the i th list
			
			j++;
			
			System.out.println("Indexul la care trebuie sa se afle elementul din lista de liste ce urmeaza a fi inserat in heap este " 
						+ i + " -- " + j);
			
			
			if( j < listOfLists.get(i).size()){ 
				
				Integer toInsert = listOfLists.get(i).get(j);
				System.out.println("Inserez pe " + toInsert + " in heap");
				insertElement(table, toInsert, false); 
			}
		}
		
		return sortedList;
	}
	
	public Tuple<Integer> findKeyPosition(ArrayList<ArrayList<Integer>> matrix, Integer key) {
		
		ArrayList<Integer> temp;
		
		for (int i = 0; i < matrix.size(); i++) {

			temp = matrix.get(i);
			
			for (int j = 0; j < temp.size(); j++) {
				
				if (key == temp.get(j)) {
					
					return new Tuple<Integer>(i, j); 
				}
			}
		}
		
		return null;
	}

	public Integer removeRoot(List<Integer> table, boolean maxHeap) {
		
		Integer rootValue;
		
		if(table.size() > 1){ 
		
			rootValue = table.get(0);
			table.set(0, table.remove(table.size() - 1)); 
			
			if (maxHeap) {
				siftDown_MaxHeap(table, 0);
			} else {
				siftDown_MinHeap(table, 0);
			}
			
			return rootValue;
		}
		
		return table.remove(table.size() - 1);
	}

	public void insertElement(List<Integer> table, Integer key, boolean maxHeap) {

		table.add(key);

		if (maxHeap) {

			bubbleUp_MaxHeap(table, table.size() - 1);

		} else {

			bubbleUp_MinHeap(table, table.size() - 1);
		}

	}

	public void deleteElement(List<Integer> table, Integer key, boolean maxHeap) {

		Integer keyIndex = table.indexOf(key);

		if (keyIndex >= 0) {
			
			if (keyIndex == table.size() - 1){ table.remove(keyIndex); } // I.E.  need to remove last element
			else { table.set(keyIndex, table.remove(table.size() - 1)); } // I.E. everything else
			
			if (maxHeap) {
				siftDown_MaxHeap(table, keyIndex);
			} else {
				siftDown_MinHeap(table, keyIndex);
			}

		} else {

			System.out.println("Elementul " + key + " nu se afla in heap!! ");
		}

	}

	public void increaseKeyValue(List<Integer> table, int value, int keyIndex, boolean maxHeap) {

		if (value > table.get(keyIndex)) {

			table.set(keyIndex, value);

			if (maxHeap) {

				bubbleUp_MaxHeap(table, keyIndex);

			} else {

				siftDown_MinHeap(table, keyIndex);
			}
		}
	}

	public void makeMaxHeap(List<Integer> table) {

		for (int i = table.size(); i >= 0; i--) {

			siftDown_MaxHeap(table, i);
		}
	}

	public void makeMinHeap(List<Integer> table) {

		for (int i = table.size(); i >= 0; i--) {

			siftDown_MinHeap(table, i);
		}

	}

	public void swap(List<Integer> table, int firstElementIndex, int secondElementIndex) {

		Integer temp = table.get(secondElementIndex);
		table.set(secondElementIndex, table.get(firstElementIndex));
		table.set(firstElementIndex, temp);
	}

	public void bubbleUp_MaxHeap(List<Integer> table, int startingIndex) {

		int child, parrent;

		child = startingIndex;

		if (child % 2 == 0) {

			parrent = child / 2 - 1;
		} else {

			parrent = child / 2;
		}

		if (child > 0 && table.get(parrent) < table.get(child)) {

			swap(table, child, parrent);
			bubbleUp_MaxHeap(table, parrent);
		}
	}

	public void bubbleUp_MinHeap(List<Integer> table, int startingIndex) {

		int child, parrent;

		child = startingIndex;

		if (child % 2 == 0) {

			parrent = child / 2 - 1;
		} else {

			parrent = child / 2;
		}

		if (child > 0 && table.get(parrent) > table.get(child)) {

			swap(table, child, parrent);
			bubbleUp_MinHeap(table, parrent);
		}
	}

	public void siftDown_MaxHeap(List<Integer> table, int startingIndex) {

		int largest, left, right;

		largest = startingIndex;
		left = largest * 2 + 1;
		right = left + 1;

		if (left < table.size() && table.get(left) > table.get(largest)) {

			largest = left;
		}

		if (right < table.size() && table.get(right) > table.get(largest)) {

			largest = right;
		}

		if (largest != startingIndex) {

			swap(table, largest, startingIndex);
			siftDown_MaxHeap(table, largest);
		}
	}

	public void siftDown_MinHeap(List<Integer> table, int startingIndex) {

		int smallest, left, right;

		smallest = startingIndex;
		left = 2 * smallest + 1;
		right = left + 1;

		if (left < table.size() && table.get(left) < table.get(smallest)) {

			smallest = left;
		}
		if (right < table.size() && table.get(right) < table.get(smallest)) {

			smallest = right;
		}

		if (smallest != startingIndex) {

			swap(table, smallest, startingIndex);
			siftDown_MinHeap(table, smallest);
		}
	}

	public void printHeap(List<Integer> heap) {

		int left, right;

		for (int i = 0; i < heap.size(); i++) {

			left = i * 2 + 1;
			right = left + 1;

			if (left < heap.size() && right < heap.size()) {

				System.out.println("     " + heap.get(i));
				System.out.println("    /" + " " + "\\");
				System.out.println("  " + heap.get(left) + "   " + heap.get(right));
				System.out.println("----------------------------");
			}

		}
	}

}