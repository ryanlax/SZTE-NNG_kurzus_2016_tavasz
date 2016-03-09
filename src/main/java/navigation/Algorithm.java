package navigation;

public interface Algorithm {

	void initialize(Graph graph);
	
	DistanceResult findShortestPath(int from, int to);
	
	TimeResult findFastestPath(int from, int to);
	
	boolean hasPath(int from, int to);
}
