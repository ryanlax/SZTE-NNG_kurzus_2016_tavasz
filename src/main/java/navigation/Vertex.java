package navigation;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private double x;
	private double y;
	private int id;
	private List<Edge> inEdges;
	private List<Edge> outEdges;
	
	Vertex() {
		init(0.0, 0.0);
	}
	
	Vertex(double x, double y) {
		init(x, y);
	}

	private void init(double x, double y){
		this.x = x;
		this.y = y;
		this.inEdges = new ArrayList<Edge>();
		this.outEdges = new ArrayList<Edge>();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addEdge(Edge e, boolean out) {
		(out ? this.outEdges: this.inEdges).add(e);
	}
	
	public double distanceFrom(Vertex other){
		if(other == null){
			throw new InvalidParameterException("Cannot calculate distance from null");
		}
		double deltaX = Math.abs(x - other.x);
		double deltaY = Math.abs(y - other.y);
		
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
	
	public List<Edge> getInEdges() {
		return this.inEdges;
	}
	
	public List<Edge> getOutEdges() {
		return this.outEdges;
	}
}
