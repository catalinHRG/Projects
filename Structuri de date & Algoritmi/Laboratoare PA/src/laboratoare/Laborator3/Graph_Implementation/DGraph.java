package laboratoare.Laborator3.Graph_Implementation;

import laboratoare.Laborator3.Graphable;
import laboratoare.Laborator3.Graph_Components.Edge;
import laboratoare.Laborator3.Graph_Components.Vertex;

public class DGraph extends Graph implements Graphable {

	@Override
	public void addEdge(String tailLabel, String headLabel, int weight) {
			
			Vertex tailVertex = returnDesiredVertex(tailLabel);
			Vertex headVertex = returnDesiredVertex(headLabel);
			
			Edge newEdge = new Edge(tailVertex, headVertex, weight);
			edges.add(newEdge);

			tailVertex.addNeighbor(headVertex, weight);
	}

	@Override
	public UGraph kruskalMST(){
		
		// TODO
		return null;
	}

}
