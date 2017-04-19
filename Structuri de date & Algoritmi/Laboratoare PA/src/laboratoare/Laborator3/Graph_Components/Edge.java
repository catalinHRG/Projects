package laboratoare.Laborator3.Graph_Components;

public class Edge implements Comparable<Edge> {

	private Vertex tail;
	private Vertex head;
	private int weight;
	private boolean visited;

	// ***********************************************

	public Edge(Vertex tail, Vertex head, int weight) {

		this.head = head;
		this.tail = tail;
		this.weight = weight;
		visited = false;

	}

	public Vertex getHead() {

		return head;
	}

	public Vertex getTail() {

		return tail;
	}

	public int getWeight() {

		return weight;
	}

	public void visitEdge() {

		visited = true;
	}

	public boolean checkStatus() {

		return visited;
	}

	@Override
	public String toString() {

		return "[ " + tail.getLabel() + " , " + head.getLabel() + " -- " + weight + " ]";
	}

	// no need to override equals since we allow duplicate edges I.E. the same
	// edge going from this vertex to another , multiple times .
	public boolean isEqualTo(Object reference, boolean graphType) {// graphType == true -> directed graph

		if (!(reference instanceof Edge)) {

			return false;
		}

		Edge castedReference = (Edge) reference;
		
		if(graphType) return tail.equals(castedReference.tail) && head.equals(castedReference.head);
		else return this.tail.equals(castedReference.head);
	}

	// sorting a colection of DEdges with .sort() will sort by the criteria
	// specified in the overriden version of compareTo
	public int compareTo(Edge reference) {

		return weight - reference.weight;
	}

}
