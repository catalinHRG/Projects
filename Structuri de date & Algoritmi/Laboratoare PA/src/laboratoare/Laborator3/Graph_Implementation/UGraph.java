package laboratoare.Laborator3.Graph_Implementation;

import laboratoare.Laborator3.Graphable;
import laboratoare.Laborator3.Graph_Components.Edge;
import laboratoare.Laborator3.Graph_Components.Vertex;

public class UGraph extends Graph implements Graphable {

	@Override
	public void addEdge(String tailLabel, String headLabel, int weight) {

		Vertex tailVertex = returnDesiredVertex(tailLabel);
		Vertex headVertex = returnDesiredVertex(headLabel);

		Edge newEdge ;
		
		newEdge = new Edge(tailVertex, headVertex, weight);
		edges.add(newEdge);
		
		if(tailVertex.equals(headVertex)){
			
			tailVertex.addNeighbor(headVertex , weight);
			return;
		}
		tailVertex.addNeighbor(headVertex, weight);
		headVertex.addNeighbor(tailVertex, weight);

	}

}
