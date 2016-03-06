package navigation;

public interface Algorithm {

	void initialize(Graph graph);
	
	Iterable<Integer> findShortestPath(int from, int to);
	
	Iterable<Integer> findFastestPath(long from, long to, long startTime);
	
	boolean hasPath(long from, long to);
}
