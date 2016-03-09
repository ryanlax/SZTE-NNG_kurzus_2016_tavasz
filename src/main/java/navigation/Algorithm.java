package navigation;

/**
 * @author blevai
 *
 */
public interface Algorithm {

	/**
	 * Store the graph and implement any computation you would like to rely on
	 * later during path searching, like calculating optimal landmark set, etc.
	 * 
	 * @param graph
	 *            data over you will optimize
	 */
	void initialize(Graph graph);

	/**
	 * Implement shortest path search minimizing the travel distance between two
	 * points.
	 * 
	 * @param from
	 *            start point
	 * @param to
	 *            end point
	 * @return a good path between the start and end points, and the length of
	 *         the path
	 */
	DistanceResult findShortestPath(int from, int to);

	/**
	 * Implement shortest path search minimizing the travel time of the path.
	 * 
	 * @param from
	 *            start point
	 * @param to
	 *            end point
	 * @return a good path between the start and end points, and travel time of
	 *         the path
	 */
	TimeResult findFastestPath(int from, int to);

	/**
	 * Determine whether there is at least one path between the start and end
	 * points.
	 * 
	 * @param from
	 *            start point
	 * @param to
	 *            end point
	 */
	boolean hasPath(int from, int to);
}
