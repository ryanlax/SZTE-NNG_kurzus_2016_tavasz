package navigation;

public class Edge {
	
	private Vertex[] vertices;
	private int averageSpeed;
	
	Edge() {
		this.vertices = new Vertex[2];
	}
	
	Edge(Vertex a, Vertex b) {
		this.vertices = new Vertex[2];
		this.setVertices(a, b);
	}
	
	Edge(Vertex a, Vertex b, int averageSpeed) {
		this.vertices = new Vertex[2];
		this.setVertices(a, b);
		this.averageSpeed = averageSpeed;
	}
	
	public void setVertices(Vertex a, Vertex b){
		this.vertices[0] = a;
		this.vertices[1] = b;
	}
	
	public int getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(int averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Vertex[] getVertices() {
		return this.vertices;
	}
	
}