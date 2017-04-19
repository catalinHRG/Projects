package laboratoare.Laborator3.Graph_Components;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private List<Edge> neighbors;
	private String label;
	private int data;
	private boolean visited, startingVertex;

	// ***********************************************

	public Vertex(String label ,int data, boolean startingVertex) {

		this.label = label;
		this.startingVertex = startingVertex;
		this.data = data;
		visited = false;
		neighbors = new ArrayList<Edge>();
	}

	public String getLabel() {

		return label;
	}
	
	public boolean hasNeighbor(Edge neighbor) {

		return neighbors.contains(neighbor);
	}
	
	public void visitVertex(){
		
		visited = true;
	}
	
	public boolean isVisited(){
		
		return visited;
	}
	
	public void setStartingVertex(){
		
		startingVertex = true;
	}
	
	public boolean isFirstVertex(){
		
		return startingVertex;
	}
	
	public int getData(){
		
		return data;
	}
	
	public boolean hasNeighbors(){
		
		return neighbors.isEmpty() ? false : true;
	}
	
	public void eraseVisitedTrace(){
		
		visited = false;
	}
	
	public void addNeighbor(Vertex v, int weight) {

		Edge newEdge = new Edge(this, v, weight);

		if (neighbors.contains(newEdge)) {
			return;
		}

		neighbors.add(newEdge);
	}

	public void removeNeighbor(Edge e) {

		neighbors.remove(e);
	}

	public int getNeighborCount() {

		return neighbors.size();
	}
	
	public ArrayList<Edge> getNeighbors(){
		
		return (ArrayList<Edge>) neighbors;
	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (Edge e : neighbors) {

			sb.append(e.toString() + "\n");
		}

		return sb.toString();
	}

	public boolean equals(Object reference){
		
		if(! (reference instanceof Vertex)){
			return false;
		}
		
		Vertex castedReference = (Vertex)reference;
		return label.equals(castedReference.label);
		
	}

}
