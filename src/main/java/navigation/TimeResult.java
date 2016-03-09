package navigation;

/**
 * @author blevai
 *
 */
public interface TimeResult extends Result {

	/**
	 * @return the whole travel time of going along the result path
	 */
	double getTravelTime();
	
}
