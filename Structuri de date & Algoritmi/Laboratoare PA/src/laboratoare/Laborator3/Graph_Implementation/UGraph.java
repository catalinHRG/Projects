package laboratoare.Laborator3.Graph_Implementation;

import java.util.Collections;
import java.util.List;

import laboratoare.Laborator3.Graphable;
import laboratoare.Laborator3.Graph_Components.Edge;
import laboratoare.Laborator3.Graph_Components.Vertex;

public class UGraph extends Graph implements Graphable {
	
	
	@Override
	public void addEdge(String tailLabel, String headLabel, int weight) {

		Vertex tailVertex = returnDesiredVertex(tailLabel);
		Vertex headVertex = returnDesiredVertex(headLabel);

		Edge newEdge;

		newEdge = new Edge(tailVertex, headVertex, weight);
		edges.add(newEdge);

		if (tailVertex.equals(headVertex)) {

			tailVertex.addNeighbor(headVertex, weight);
			return;
		}
		tailVertex.addNeighbor(headVertex, weight);
		headVertex.addNeighbor(tailVertex, weight);

	}

	@Override
	public Graph kruskalMST() {
		
		UGraph minSpanningTree = new UGraph(); // the resulting MST
		minSpanningTree.generateVertices(this.getVertices().size(), 1); // generate the same vertices in the MST
																		// we are trying to build , no edges tho .

		List<Edge> sortedEdges = this.getEdges(); 
		Collections.sort(sortedEdges); // sort the edges of the graph we are trying to find
										// the MST for , by weight , ascending
		
		Vertex tail, head;
		int weight;
		
		for(Edge candidate : sortedEdges){ // for each edge , check whether there is a path between the
											// two endpoints within the MST we are trying to build , 
											// if there is one , then discard the edge , else add it .
											// Stop when we have V - 1 edges in the MST .
			
			tail = minSpanningTree.returnDesiredVertex(candidate.getTail().getLabel());
			head = minSpanningTree.returnDesiredVertex(candidate.getHead().getLabel());
			weight = candidate.getWeight();
			
			if(minSpanningTree.hasPath(head, tail)){ continue; }
			
			minSpanningTree.addEdge(tail.getLabel(), head.getLabel(), weight);
			
			if(minSpanningTree.getEdges().size() == minSpanningTree.getVertices().size() - 1){ break; }
		}
		
		return minSpanningTree;	
	}
}
