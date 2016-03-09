package navigation;

/**
 * Interface to store the result path of path searchings.
 * 
 * @author blevai
 *
 */
public interface Result {

	/**
	 * @return a path as an iterable list of node ids, the first element must be
	 *         the start node, the last element must be the end node of the path
	 *         search. The elements in between them must be in the same order as
	 *         they follow each other in the graph.
	 */
	public Iterable<Integer> getPath();

}
