package navigation;

public interface Algorithm {

	void initialize(Graph graph);
	
	Iterable<Vertex> findShortestPath(long from, long to, long startTime);
	
	Iterable<Vertex> findFastestPath(long from, long to, long startTime);
	
	boolean hasPath(long from, long to);
}
