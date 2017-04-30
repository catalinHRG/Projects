package laboratoare.Laborator3;

import laboratoare.Laborator3.Graph_Implementation.DGraph;
import laboratoare.Laborator3.Graph_Implementation.UGraph;

public class L3 {
	
	/*
	 * Graph/Tree implementation using Nodes/Pointers to Nodes
	 * 
	 * */
	
	
	public static void main(String[] args) {
		
		System.out.println("********************* Graf orientat , depth first traversal *************************");
		
		DGraph directedGraph = new DGraph();

		directedGraph.generateVertices(10, 1);
		
		directedGraph.addEdge("1", "2", 1 /* this is the edge weight or cost */ );
		directedGraph.addEdge("2", "3", 1);
		directedGraph.addEdge("3", "4", 1);
		directedGraph.addEdge("3", "5", 1);
		directedGraph.addEdge("5", "6", 1);
		directedGraph.addEdge("6", "7", 1);
		directedGraph.addEdge("7", "3", 1);
		directedGraph.addEdge("8", "7", 1);
		directedGraph.addEdge("5", "5", 1);
		directedGraph.addEdge("9", "3", 1);
		directedGraph.addEdge("10", "2", 1);
		directedGraph.addEdge("1", "9", 1);
		
		// P1
		//System.out.println("Este graful de mai sus simplu conex ? " + directedGraph.isGraphSimplyConnected() + "\n");
		// P2
		//System.out.println("\nEste graful de mai sus semi conex ? " + directedGraph.isGraphSemiConnected());
		
		//directedGraph.depthFirstTraversal();

		System.out.println("********************* Graf neorientat , depth first traversal *************************");
		
		UGraph unDirectedGraph = new UGraph();

		unDirectedGraph.generateVertices(10, 1);
		
		unDirectedGraph.addEdge("1", "2", 1 /* this is the edge weight or cost */ );
		unDirectedGraph.addEdge("2", "3", 1);
		unDirectedGraph.addEdge("3", "4", 1);
		unDirectedGraph.addEdge("3", "5", 1);
		unDirectedGraph.addEdge("5", "6", 1);
		unDirectedGraph.addEdge("6", "7", 1);
		unDirectedGraph.addEdge("7", "3", 1);
		unDirectedGraph.addEdge("8", "7", 1);
		unDirectedGraph.addEdge("5", "5", 1);
		unDirectedGraph.addEdge("9", "3", 1);
		unDirectedGraph.addEdge("10", "2", 1);
		unDirectedGraph.addEdge("1", "9", 1);
		
		unDirectedGraph.depthFirstTraversal();
		
		System.out.println("*************** Arbore neorientat , depth first traversal *******************************");
		
		UGraph tree = new UGraph();
		tree.generateVertices(7, 1 /* tree root == node with label "1" */ );
		tree.addEdge("1", "2", 1);
		tree.addEdge("1", "3", 1);
		tree.addEdge("1", "4", 1);
		tree.addEdge("2", "5", 1);
		tree.addEdge("3", "6", 1);
		tree.addEdge("3", "7", 1);
		
		//tree.depthFirstTraversal();
		
		System.out.println("********************* Arbore neorientat , breadth first traversal *************************");
		
		//tree.breadthFirstTraversal();
	}
}