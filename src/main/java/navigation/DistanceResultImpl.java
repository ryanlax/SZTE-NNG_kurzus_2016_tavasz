package navigation;

import java.util.LinkedList;
import java.util.List;

public class DistanceResultImpl implements DistanceResult {
	
	private List<Integer> path;
	private double travelDistance;
	
	DistanceResultImpl(){
		path = new LinkedList<Integer>();
	}

	@Override
	public List<Integer> getResultPath() {
		return this.path;
	}

	@Override
	public double getTravelDistanceOfResultPath() {
		return this.travelDistance;
	}

	public void setTravelDistance(double travelDistance) {
		this.travelDistance = travelDistance;
	}

	public void setPath(List<Integer> path) {
		this.path = path;
	}
	
}
