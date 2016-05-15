package navigation;

public class DijsktraVertex implements Comparable<DijsktraVertex> {
	
	public Vertex vertex;
	public double distance;
	public int previous; // Id of the previous vertex
	
	DijsktraVertex(Vertex vertex) {
		this.vertex = vertex;
		this.distance = Double.POSITIVE_INFINITY; 
	}
	
	DijsktraVertex(Vertex vertex, double distance, int previous) {
		this.vertex = vertex;
		this.distance = distance;
		this.previous = previous;
	}

	@Override
	public int compareTo(DijsktraVertex other) {
		if(this.distance < other.distance) {
			return -1;
		} else if(this.distance > other.distance){
			return 1;
		}
		return 0;
	}

}