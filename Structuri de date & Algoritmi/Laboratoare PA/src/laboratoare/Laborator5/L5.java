package laboratoare.Laborator5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import laboratoare.Laborator3.Graph_Components.Edge;
import laboratoare.Laborator3.Graph_Components.Vertex;
import laboratoare.Laborator3.Graph_Implementation.UGraph;

public class L5 {

	private static Stack<Edge> pathEdges;
	
	// *************************************************	

	public static void generateRandomValues(List<Integer> list, int numberOfElements, int maxValue) {

		Random rng = new Random();
		for (int i = 0; i < numberOfElements; i++) {
			list.add(rng.nextInt(maxValue));
		}
	}

	public static void binarySearch(List<Integer> sortedList, int key) {

		int min = 0;
		int max = sortedList.size() - 1;

		while (min <= max) {

			int mid = min + (max - min) / 2;
			if (key < sortedList.get(mid))
				max = mid - 1;
			else if (key > sortedList.get(mid))
				min = mid + 1;
			else {

				System.out.println(key + " se afla la pozitia " + mid);
				return;
			}

		}

		System.out.println(key + " nu se afla in lista !!");
	}

	public static void greedyTravelingSalesman(UGraph space) {

		List<Vertex> cities = space.getVertices();
		Vertex currentCity = null;
		Edge neighbor;
		int totalCost = 0;

		for (Vertex city : cities) {

			if (city.isFirstVertex()) {

				currentCity = city;
				break;
			}
		}

		while (!currentCity.isVisited() || currentCity.isFirstVertex()) {

			currentCity.visitVertex();
			System.out.println("Sunt la nodul " + currentCity.getLabel());
			neighbor = currentCity.getNearestNeighbor();
			currentCity = neighbor.getHead();
			totalCost = totalCost + neighbor.getWeight();

		}

		System.out.println("Costul total este : " + totalCost);
	}

	public static void nBitGrayCodeGenerator(int numberOfBits) {

		List<String> firstList = new ArrayList<String>();
		List<String> secondList;

		String tempString = null;
		int counter = 0;

		// 1 bit Gray Code
		firstList.add("0");
		firstList.add("1");

		while (counter < numberOfBits - 1) {

			secondList = new ArrayList<String>(firstList);
			Collections.reverse(secondList);

			for (int i = 0; i < firstList.size(); i++) {

				tempString = "0" + firstList.get(i);
				firstList.set(i, tempString);
				tempString = "1" + secondList.get(i);
				secondList.set(i, tempString);
			}

			counter++;
			firstList.addAll(secondList);
		}

		System.out.println((counter + 1) + " bit Gray Code " + firstList.toString());
	}
	
	// Pentru reprezentare cu matrice de adiacenta a grafului
	public static void primsAlgorithm(int[][] graph) {

		int[] visited = new int[graph.length];
		int min = Integer.MAX_VALUE;
		int x = 0, y = 0, sum = 0;

		visited[0] = 1;

		for (int count = 0; count < graph.length - 1; count++) {

			for (int i = 0; i < graph.length; i++) {

				if (visited[i] == 1) {

					for (int j = 0; j < graph.length; j++) {

						if (min > graph[i][j]) {

							min = graph[i][j];
							x = i;
							y = j;

						}
					}
				}

				visited[y] = 1;
				sum += min;
				System.out.println(x + " " + y);
			}

		}

		System.out.println(sum + " . ");
	}

	public static void findMaxCostEdge(UGraph tree, String start, String destination) {

		Stack<Edge> edgeStack = new Stack<Edge>();
		
		Vertex startV = tree.returnDesiredVertex(start);
		Vertex destinationV = tree.returnDesiredVertex(destination);
		
		recursiveDFT(tree, startV, destinationV, edgeStack);
		
		Edge maxCostEdge = pathEdges.pop();
		Edge temp;
		
		while(!pathEdges.isEmpty()){
			
			temp = pathEdges.pop();
			
			if(temp.getWeight() > maxCostEdge.getWeight()){
				
				maxCostEdge = temp;
			}
		}
		
		System.out.println("Muchia de cost maxim este : " + maxCostEdge.toString());
	}
	
	// DFT = Depth First Traversal
	public static void recursiveDFT(UGraph tree, Vertex start, Vertex destination, Stack<Edge> eStack) {

		if (start.equals(destination)) {

			pathEdges = eStack;
			return;
		} else if (start.areNeighborsVisited()) {

			eStack.pop();
			return;
		} else {
			
			start.visitVertex();
			
			for (Edge neighbor : start.getNeighbors()) {
					
				if(!neighbor.getHead().isVisited()){
					
					eStack.push(neighbor);
					recursiveDFT(tree, neighbor.getHead(), destination, eStack);
				}
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("Cautarea binara : ");
		
		List<Integer> list = new ArrayList<Integer>();
		
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Introduceti un numar intre 0 si 50 :");
		int candidate = userInput.nextInt();

		L5.generateRandomValues(list, 30, 50);
		System.out.println("Lista este " + list.toString());
		Collections.sort(list);

		L5.binarySearch(list, candidate);

		System.out.println("------Comisul Voiajor , Nearest Neighbour (Greedy)------------");
		UGraph uGraph = new UGraph();
		uGraph.generateVertices(5, 1);

		uGraph.addEdge("1", "2", 1);
		uGraph.addEdge("1", "3", 2);
		uGraph.addEdge("1", "4", 7);
		uGraph.addEdge("1", "5", 5);
		uGraph.addEdge("2", "3", 4);
		uGraph.addEdge("2", "4", 4);
		uGraph.addEdge("2", "5", 3);
		uGraph.addEdge("3", "4", 1);
		uGraph.addEdge("3", "5", 2);
		uGraph.addEdge("4", "5", 3);
		L5.greedyTravelingSalesman(uGraph); // not efficient enough , nontheless
											// , correct implementation

		System.out.println("----------Generator Cod Grey pe N Biti ------------------");
		System.out.println("Introduceti numarul de biti :");
		int numberOfBits = userInput.nextInt();
		L5.nBitGrayCodeGenerator(numberOfBits);

		//System.out.println("---------Prim , Arbore partial de cost minim ---------------");
		//int[][] graph = new int[6][6];
		
		//L5.primsAlgorithm(graph);
		
		System.out.println("-----------Muchia de cost maxim-------------");
		UGraph tree = new UGraph();
		tree.generateVertices(13, 1);
		tree.addEdge("1", "2", 4);
		tree.addEdge("1", "3", 3);
		tree.addEdge("2", "4", 6);
		tree.addEdge("2", "5", 2);
		tree.addEdge("3", "6", 4);
		tree.addEdge("3", "7", 9);
		tree.addEdge("7", "8", 23);
		tree.addEdge("7", "9", 13);
		tree.addEdge("8", "10", 4);
		tree.addEdge("8", "11", 6);
		tree.addEdge("11", "12", 4);
		tree.addEdge("11", "13", 9);
		
		System.out.println(tree.toString());
		System.out.println("Caut muchia de cost maxim aflata in drumul dintre 1 si 13 !!");
		L5.findMaxCostEdge(tree, "1", "13");
		
		System.out.println("--------Al doilea cel ai bun arbore partial de cost minim------------");
		UGraph space = new UGraph();
		space.generateVertices(5, 1);
		space.addEdge("1", "2", 3);
		space.addEdge("1", "5", 1);
		space.addEdge("2", "5", 4);
		space.addEdge("2", "3", 5);
		space.addEdge("3", "5", 6);
		space.addEdge("3", "4", 2);
		space.addEdge("4", "5", 7);
		System.out.println(space.toString());
		System.out.println("**************");
		UGraph minSpanningTree = (UGraph)space.kruskalMST();
		System.out.println(minSpanningTree.toString());
		
		// pe baza MST ului aflat , cum pot afla al doilea cel mai bun MST ????
	}
}