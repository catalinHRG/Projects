package laboratoare.Laborator6;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

public class L6 {

	private static int nrOfVertices = 6;
	private static ArrayList<ArrayList<Integer>> shortestPaths = new ArrayList<ArrayList<Integer>>();
	
	static ArrayList<ArrayList<Integer>> getShortestPaths(){
		
		return shortestPaths;
	}
	
	static int minDistance(int distances[], Boolean visited[]) {

		int min = Integer.MAX_VALUE, minIndex = -1;

		for (int v = 0; v < nrOfVertices; v++) {

			if (visited[v] == false && distances[v] <= min) {

				min = distances[v];
				minIndex = v;
			}
		}

		return minIndex;
	}

	static void printPath(int parent[], int j, ArrayList<Integer> tmp) {
		
		if (parent[j] == -1)
			return;

		printPath(parent, parent[j], tmp);

		System.out.print(" " + (j + 1));
		tmp.add(j + 1);
	}

	static void printSolution(int dist[], int parent[]) {
		
		int src = 1;
		ArrayList<Integer> temp = null;
		
		System.out.println("Vertex\t  Distance\tPath");
		
		for (int i = 1; i < nrOfVertices; i ++) {
			
			temp = new ArrayList<Integer>();
			
			System.out.print("\n" + src + " -> " + (i + 1) + " \t   " + dist[i] + "\t\t" + src);
			temp.add(src);
			
			printPath(parent, i, temp);
			
			shortestPaths.add(temp);
		}
		
	}

	static void dijkstra(int graph[][], int startingPoint) {

		int distances[] = new int[nrOfVertices];
		int shortestPath[] = new int[nrOfVertices];
		Boolean visited[] = new Boolean[nrOfVertices];
		
		shortestPath[0] = -1;
		
		for (int i = 0; i < nrOfVertices; i ++) {

			distances[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		distances[startingPoint] = 0;

		for (int c = 0; c < nrOfVertices - 1; c ++) {

			int pivot = minDistance(distances, visited);
			visited[pivot] = true;
			
			for (int v = 0; v < nrOfVertices; v++)

				if (!visited[v] && graph[pivot][v] != 0 && distances[pivot] != Integer.MAX_VALUE
						&& distances[pivot] + graph[pivot][v] < distances[v]) {

					distances[v] = distances[pivot] + graph[pivot][v];
					shortestPath[v] = pivot;
				}
		}

		printSolution(distances, shortestPath);
	}
	
	public static void main(String[] args) {

		int graph[][] = new int[][] {

				{ 0, 7, 9, 0, 0, 14 }, { 7, 0, 10, 15, 0, 0 }, { 9, 10, 0, 11, 0, 2 }, { 0, 15, 11, 0, 6, 0 },
				{ 0, 0, 0, 6, 0, 9 }, { 14, 0, 2, 0, 9, 0 }

		};

		L6.dijkstra(graph, 0);
		
		ArrayList<ArrayList<Integer>> graphEdges = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tmp;
		
		for(int i = 0 ; i < graph.length ; i ++){
			
			tmp = new ArrayList<Integer>();
			
			for(int j = 0 ; j < graph.length ; j ++){
				
				if(graph[i][j] != 0){
					
					tmp.add(i + 1);
					tmp.add(j + 1);
				}
				
			}
			
			graphEdges.add(tmp);
		}
		
		DijkstraGUI display = new DijkstraGUI(L6.getShortestPaths(), graphEdges, nrOfVertices);
		
		JFrame mainFrame = new JFrame();
		mainFrame.add(display);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.pack();
		mainFrame.setBackground(Color.BLACK);
		mainFrame.setVisible(true);
		
	}
}
