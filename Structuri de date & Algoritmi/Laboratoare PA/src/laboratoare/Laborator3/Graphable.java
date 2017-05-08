package laboratoare.Laborator3;

import laboratoare.Laborator3.Graph_Implementation.Graph;

public interface Graphable {
	
	public void addEdge(String tailLabel, String headLabel, int weight);
	
	public Graph kruskalMST();

}
