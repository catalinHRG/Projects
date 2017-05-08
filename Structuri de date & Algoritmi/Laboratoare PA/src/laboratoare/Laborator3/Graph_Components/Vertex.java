package laboratoare.Laborator3.Graph_Components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vertex implements Comparable<Vertex> {

	private List<Edge> neighbors;
	private String label;
	private int data;
	private boolean visited, startingVertex;
	private Random rng;
	// ***********************************************

	public Vertex(String label, boolean startingVertex) {

		rng = new Random();
		neighbors = new ArrayList<Edge>();

		this.label = label;
		this.startingVertex = startingVertex;
		this.data = rng.nextInt(200); // random starting value;
		visited = false;
	}

	public String getLabel() {

		return label;
	}

	public boolean hasNeighbor(Edge neighbor) {

		return neighbors.contains(neighbor);
	}

	public void visitVertex() {

		visited = true;
	}

	public boolean isVisited() {

		return visited;
	}

	public void setStartingVertex() {

		startingVertex = true;
	}

	public boolean isFirstVertex() {

		return startingVertex;
	}

	public void setdata(int data) {

		this.data = data;
	}

	public int getData() {

		return data;
	}

	public boolean hasNeighbors() {

		return neighbors.isEmpty() ? false : true;
	}
	
	public boolean areNeighborsVisited(){
		
		boolean result = true;
		
		for(Edge neighbor : neighbors){
			if(!neighbor.getHead().isVisited()){
				result = false;
				break;
			}
		}
		
		return result;
	}

	public void eraseVisitedTrace() {

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

	public Edge getFurthestNeighbor() {

		List<Edge> neighbors = this.getNeighbors();

		int maxIndex = 0;

		for (int i = 0; i < neighbors.size(); i++) {

			if (!neighbors.get(i).getHead().isVisited()) {

				maxIndex = i;
				break;
			}
		}

		for (int i = 0; i < neighbors.size(); i++) {

			Edge candidate = neighbors.get(i);
			Edge max = neighbors.get(maxIndex);

			if (candidate.getWeight() > max.getWeight() && !candidate.getHead().isVisited()) {

				maxIndex = i;
			}

		}

		return neighbors.get(maxIndex);

	}

	public Edge getNearestNeighbor() {

		List<Edge> neighbors = this.getNeighbors();

		int minIndex = 0;

		for (int i = 0; i < neighbors.size(); i++) {

			if (!neighbors.get(i).getHead().isVisited()) {

				minIndex = i;
				break;
			}
		}

		for (int i = 0; i < neighbors.size(); i++) {

			Edge candidate = neighbors.get(i);
			Edge min = neighbors.get(minIndex);

			if (candidate.getWeight() < min.getWeight() && !candidate.getHead().isVisited()) {

				minIndex = i;
			}

		}

		return neighbors.get(minIndex);
	}

	public Vertex getNeighbor(int index) {

		return neighbors.get(index).getHead();
	}

	public ArrayList<Edge> getNeighbors() {

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

	public boolean equals(Object reference) {

		if (!(reference instanceof Vertex)) {
			return false;
		}

		Vertex castedReference = (Vertex) reference;
		return label.equals(castedReference.label);

	}

	public int compareTo(Vertex candidate) {

		return this.data - candidate.data;
	}

}
