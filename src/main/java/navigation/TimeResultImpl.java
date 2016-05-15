package navigation;

import java.util.LinkedList;
import java.util.List;

public class TimeResultImpl implements TimeResult {

	private List<Integer> path;
	private double travelTime;
	
	TimeResultImpl(){
		path = new LinkedList<Integer>();
	}
	
	@Override
	public List<Integer> getResultPath() {
		return this.path;
	}

	@Override
	public double getTravelTimeOfResultPath() {
		return this.travelTime;
	}

	public void setTravelTime(double travelTime) {
		this.travelTime = travelTime;
	}
	
	public void setPath(List<Integer> path) {
		this.path = path;
	}
	

	

}
