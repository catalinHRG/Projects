package laboratoare.Laborator3.Graph_Implementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import laboratoare.Laborator3.Graph_Components.Edge;
import laboratoare.Laborator3.Graph_Components.Vertex;

/*
 * Might have to make the distinction between the graph and  
 * the tree , since a graph can accept duplicate vertices
 * whilst the tree cannot .
 * 
 * Right now none of them accept duplicate vertices !
 * 
 * Making another class Tree that extends the Graph with it's own 
 * implementation of addVertex() might do the trick . 
 * 
 * The user has freedom to add any kind of edges and in case he 
 * does not uphold the tree restrictions , he will end up with 
 * a graph instead of a tree implicitly .
 * 
 * */

public class Graph {

	/*
	 * 
	 * All the similarities between DGraph and UGraph will be found here whilst
	 * the differences will be coded in the Graphable interface which ultimetly
	 * will be implemented by the DGraph and UGraph classes with their own
	 * unique version of it .
	 * 
	 */

	protected List<Vertex> vertices;
	protected List<Edge> edges;

	// ***********************************************

	public Graph() {

		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	
	public void pushNextVertexOnStack(Stack<Vertex> aStack, ArrayList<Edge> neighbors){
		
		Vertex current;
		
		for (Edge neighbor : neighbors) {

			current = neighbor.getHead();

			if (!current.isVisited()) {

				current.visitVertex();
				aStack.push(current);
				break;
			}
		}
	}
	
	public void inOrderTraversal() {

		Stack<Vertex> stack = new Stack<Vertex>();
		Vertex currentVertex;
		ArrayList<Edge> currentVertexNeighbors;
		
		for (Vertex current : vertices) {

			if (current.isFirstVertex()) {

				current.visitVertex();
				stack.push(current);
				break;
			}
		}
		
		while (!stack.isEmpty()) {
			
			currentVertex = stack.peek();
			currentVertexNeighbors = currentVertex.getNeighbors();

			if (currentVertex.getNeighborCount() > 2 || currentVertex.isFirstVertex()) {
				
				pushNextVertexOnStack(stack, currentVertexNeighbors);

			} else { 

				currentVertex = stack.pop();
				System.out.println("Sunt la nodul " + currentVertex.getLabel());
				currentVertexNeighbors = currentVertex.getNeighbors();

				if (stack.isEmpty()) {
					
					pushNextVertexOnStack(stack, currentVertexNeighbors);
					
				} else {

					currentVertex = stack.pop();
					System.out.println("Sunt la nodul " + currentVertex.getLabel());
					currentVertexNeighbors = currentVertex.getNeighbors();
					pushNextVertexOnStack(stack, currentVertexNeighbors);
				}
			}
		}

		System.out.println("Sfarsit");
		clearVisitedTraces();
	}

	public void postOrderTraversal() {

		Stack<Vertex> stack = new Stack<Vertex>();
		Vertex currentVertex;
		ArrayList<Edge> currentVertexNeighbors;
		Edge neighbor;
		
		for (Vertex current : vertices) {

			if (current.isFirstVertex()) {

				stack.push(current);
				break;
			}
		}
		
		while (!stack.isEmpty()) {

			if (!stack.peek().hasNeighbors() || stack.peek().isVisited()) {

				currentVertex = stack.pop();
				System.out.println("----------------------");
				System.out.println("Sunt la nodul " + currentVertex.getLabel());

			} else {

				stack.peek().visitVertex();
				currentVertexNeighbors = stack.peek().getNeighbors();

				for (int i = currentVertexNeighbors.size() - 1; i >= 0; i--) {

					neighbor = currentVertexNeighbors.get(i);

					if (!neighbor.getHead().isVisited())
						stack.push(neighbor.getHead());
				}
			}
		}

		System.out.println("Sfarsit");
		clearVisitedTraces();
	}

	public void breadthFirstTraversal() {

		Queue<Vertex> queue = new LinkedList<Vertex>();
		Vertex currentVertex;

		for (Vertex current : vertices) {

			if (current.isFirstVertex()) {

				System.out.println("Radacina arborelui este " + current.getLabel());
				queue.add(current);
				break;
			}
		}

		while (!queue.isEmpty()) {

			currentVertex = queue.poll();

			System.out.println("Sunt la nodul " + currentVertex.getLabel());
			System.out.println("-----------------------");

			if (currentVertex.isVisited()) {
				System.out.println();
				continue;
			}

			currentVertex.visitVertex();

			for (Edge neighbor : currentVertex.getNeighbors()) {

				if (!neighbor.getHead().isVisited())
					queue.add(neighbor.getHead());
			}
		}

		System.out.println("Sfarsit");
		clearVisitedTraces();
	}

	public void depthFirstTraversal /* AKA pre order traversal */ () {

		Stack<Vertex> stack = new Stack<Vertex>();
		Vertex currentVertex;
		Edge neighbor;
		ArrayList<Edge> currentVertexNeighbors;

		for (Vertex current : vertices) {

			if (current.isFirstVertex()) {

				stack.push(current);
				break;
			}
		}
		
		while (!stack.isEmpty()) {

			currentVertex = stack.pop();

			System.out.println("Sunt la nodul " + currentVertex.getLabel());
			System.out.println("-----------------------");

			if (currentVertex.isVisited()) {
				System.out.println();
				continue;
			}

			currentVertex.visitVertex();
			currentVertexNeighbors = currentVertex.getNeighbors();

			/*
			 * because of the nature of the nature of the stack , it would
			 * traverse the graph , in depth , from right to left ; now by
			 * reverse the order we push on the stack it's neighbors , we
			 * accomplish the correct traversel order , from left to right ,
			 * depth first .
			 * 
			 */

			for (int i = currentVertexNeighbors.size() - 1; i >= 0; i--) {

				neighbor = currentVertexNeighbors.get(i);
				if (!neighbor.getHead().isVisited())
					stack.push(neighbor.getHead());
			}
		}

		System.out.println("Sfarsit");
		clearVisitedTraces();
	}

	public boolean isGraphSimplyConnected() {

		/*
		 * iterrative DepthFirstSearch is equivalent to a BreadthFirstSearch
		 * with a stack instead of a queue aux DS graph traversal -- iterative
		 * aproach(user-made stack instead of function call stack) start by
		 * pushing on the stack the starting node while the stack has elements
		 * I.E there are paths to follow pop the stack in orded to check whether
		 * the current node is visited if it is jump to the next iteration if
		 * not , visit the element and add on the stack all of it's neighbors
		 * I.E paths to follow subsequently resume the loop with the next
		 * iteration stopping conditions -- if there are edges from vertex X to
		 * itself if there are multiple edges from vertex X to vertex Y maybe if
		 * we come across vertex X in a path that started from it ** if any of
		 * the above conditions evaluate , then the graph is not simply
		 * connected after all the elements have been visited , clear the
		 * visited flags and return true , graph is simply connected
		 */

		Stack<Vertex> stack = new Stack<Vertex>();
		List<Edge> currentVertexNeighbors;

		Vertex currentVertex;

		for (Vertex current : vertices) {

			if (current.isFirstVertex()) {

				stack.push(current);
				break;
			}
		}
		
		while (!stack.isEmpty()) {

			currentVertex = stack.pop();
			currentVertexNeighbors = currentVertex.getNeighbors();
			System.out.println("Sunt la nodul " + currentVertex.getLabel());

			if (currentVertex.isVisited()) {

				continue; // resumes the loop with the next iterration
			}

			currentVertex.visitVertex();

			for (int i = 0; i < currentVertexNeighbors.size(); i++) {

				if (currentVertex.equals(currentVertexNeighbors.get(i).getHead())) {

					System.out.println("\nMuchie de la " + currentVertex.getLabel() + " la "
							+ currentVertexNeighbors.get(i).getHead().getLabel() + "!!");
					clearVisitedTraces();
					return false;

				} else if (i == 0) {

					for (int j = 1; j < currentVertexNeighbors.size(); j++) {

						if (currentVertexNeighbors.get(i).isEqualTo(currentVertexNeighbors.get(j), true)) {

							// isEqualTo() evaluates whether any two edges have
							// the same .head and .tail
							// no need to override .equals() since we allow
							// duplicates in the DEdge collection
							System.out.println("\nAvem doua muchii identice");
							clearVisitedTraces();
							return false;
						}
					}
				}

				stack.push(currentVertexNeighbors.get(i).getHead());
			}
		}

		clearVisitedTraces();
		return true;
	}

	public boolean isGraphSemiConnected() {

		/*
		 * For all paths except the ones starting from and ending at the same
		 * vertex we make use of a function hasPath() that attempts to traverse
		 * the path between the specified vertices . It will return true if
		 * there is one and false if there is not . Computing the hasPath() for
		 * two vertices in both directions at the same time , allows us to
		 * trigger a False result on the first occurrence of both values being
		 * false I.E. no path whatsoever between the two speficified vertices --> Graph is not semi connected .
		 */

		boolean b1, b2;

		for (int i = 0; i < vertices.size(); i++) {

			for (int j = 0; j < vertices.size(); j++) {

				if (i != j) {

					Vertex start = vertices.get(i);
					Vertex destination = vertices.get(j);

					System.out.print("Verific daca exista drum intre " + start.getLabel() + " si "
							+ destination.getLabel() + " : ");

					b1 = hasPath(start, destination);
					b2 = hasPath(destination, start);

					if (!(b1 || b2)) {

						System.out.print(false);
						return false;
					}

					System.out.println(true);
				}
			}
		}

		return true;
	}

	public void generateVertices(int verticesCount, int startingVertexIndex) {

		if (startingVertexIndex < 0) {

			System.out.println("Index ul trebuie sa fie intre 1 si " + verticesCount);
			return;
		}

		for (int i = 0; i < verticesCount; i++) {

			if (i == (startingVertexIndex - 1))
				this.addVertex(new Vertex(String.valueOf(i + 1), true));
			else
				this.addVertex(new Vertex(String.valueOf(i + 1), false));
		}

	}

	public boolean hasPath(Vertex start, Vertex destination) {

		Stack<Vertex> stack = new Stack<Vertex>();
		Vertex currentVertex;

		boolean path = false;

		stack.push(start);

		while (!stack.isEmpty()) {

			currentVertex = stack.pop();

			if (currentVertex.equals(destination)) {

				path = true;
				break;
			}

			if (currentVertex.isVisited()) {

				continue;
			}

			currentVertex.visitVertex();

			for (Edge neighbor : currentVertex.getNeighbors()) {

				stack.push(neighbor.getHead());
			}

		}

		clearVisitedTraces();
		return path;
	}
	
	public Vertex returnDesiredVertex(String label) {

		for (Vertex target : vertices) {

			if (target.getLabel().equals(label)) {

				return target;
			}
		}

		return null; // target not found;
	}

	public void clearVisitedTraces() {

		for (Vertex currentVertex : vertices) {

			if (currentVertex.isVisited()) {

				currentVertex.eraseVisitedTrace();
			}
		}
	}

	public void addVertex(Vertex toBeAdded) {

		if (vertices.contains(toBeAdded)) {
			return;
		}
		vertices.add(toBeAdded);
	}

	public ArrayList<Vertex> getVertices() {

		return (ArrayList<Vertex>) vertices;
	}

	public ArrayList<Edge> getEdges() {

		return (ArrayList<Edge>) edges;
	}

	public void printVertices() {

		StringBuilder sb = new StringBuilder();
		for (Vertex current : vertices) {

			sb.append(current.getLabel() + " ");
		}

		System.out.println(sb.toString());
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		int counter = 1;

		for (Edge neighbor : edges) {

			sb.append(counter + " -- " + neighbor.toString() + "\n");
			counter++;
		}

		return sb.toString();

	}

}
