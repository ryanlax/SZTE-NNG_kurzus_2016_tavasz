package navigation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collections;

interface EdgeWeightCalculator {
	public double getWeight(Edge edge);
}

/**
 * Implement your navigation algorithm here. This class will be instantiated
 * during the unit tests.
 */
public class AlgorithmImpl implements Algorithm {

	private GraphImpl graph;

	@Override
	public void preprocess(Graph graph) {
		this.graph = (GraphImpl) graph;
	}

	private MyResult dijkstra(int startNodeId, int destinationNodeId, EdgeWeightCalculator edgeWeightCalculator) {
		// The first place in this array are null
		DijsktraVertex[] vertices = new DijsktraVertex[this.graph.getNumVertices() + 1];
		PriorityQueue<DijsktraVertex> queue = new PriorityQueue<DijsktraVertex>();
		
		for(int i=1; i < vertices.length; ++i){
			vertices[i] = new DijsktraVertex(this.graph.getVertex(i-1));
		}
		vertices[startNodeId].distance = 0.0;
		
		for(int i=1; i < vertices.length; ++i){
			queue.add(vertices[i]);
		}
		
		Iterator<Edge> edgeIt;
		Edge edge;
		DijsktraVertex currentDV, neighbourDV;
		Vertex neighbourVertex;
		double newDistance;
		while (!queue.isEmpty()) {
			currentDV = queue.poll();
			edgeIt = currentDV.vertex.getOutEdges().iterator();
			while (edgeIt.hasNext()) {
				edge = edgeIt.next();
				neighbourVertex = edge.getVertices()[1];
				neighbourDV = vertices[neighbourVertex.getId()];
				newDistance = currentDV.distance + edgeWeightCalculator.getWeight(edge);
				if (newDistance < neighbourDV.distance) {
					queue.remove(neighbourDV);
					neighbourDV.distance = newDistance;
					neighbourDV.previous = currentDV.vertex.getId();
					queue.add(neighbourDV);
				}
			}
		}

		// decode path
		MyResult result = new MyResult();
		result.result = vertices[destinationNodeId].distance;
		result.resultPath = new LinkedList<Integer>();
		result.resultPath.add(destinationNodeId);
		int current = destinationNodeId;
		while(vertices[current].previous != 0){
			current = vertices[current].previous;
			result.resultPath.add(current);
		}
		
		Collections.reverse(result.resultPath);
		return result;
	}

	@Override
	public DistanceResult findShortestPath(int startNodeId, int destinationNodeId) {
		DistanceResultImpl result = new DistanceResultImpl();
		EdgeWeightCalculator edgeWeightCalculator = (Edge e) -> {
			Vertex[] vertices = e.getVertices();
			return vertices[0].distanceFrom(vertices[1]);
		};
		MyResult mr = dijkstra(startNodeId, destinationNodeId, edgeWeightCalculator);
		result.setPath(mr.resultPath);
		result.setTravelDistance(mr.result);
		return result;
	}

	@Override
	public TimeResult findFastestPath(int startNodeId, int destinationNodeId) {
		TimeResultImpl result = new TimeResultImpl();
		EdgeWeightCalculator edgeWeightCalculator = (Edge e) -> {
			Vertex[] vertices = e.getVertices();
			return vertices[0].distanceFrom(vertices[1]) / e.getAverageSpeed();
		};
		MyResult mr = dijkstra(startNodeId, destinationNodeId, edgeWeightCalculator);
		result.setPath(mr.resultPath);
		result.setTravelTime(mr.result);
		return result;
	}

	@Override
	public boolean hasPath(int startNodeId, int destinationNodeId) {
		boolean[] seen = new boolean[this.graph.getNumVertices() + 1];
		Queue<Integer> queue = new LinkedList<Integer>(); // Breadth-first search
		queue.add(startNodeId);
		int currentNodeId;
		int neighbourId;
		while (!queue.isEmpty()) {
			currentNodeId = queue.poll().intValue();
			if (seen[currentNodeId]) {
				continue;
			}
			seen[currentNodeId] = true;
			Iterator<Edge> edgeIterator = this.graph.getVertex(currentNodeId - 1).getOutEdges().iterator();
			while (edgeIterator.hasNext()) {
				neighbourId = edgeIterator.next().getVertices()[1].getId();
				if (neighbourId == destinationNodeId) { // speed up
					return true;
				}
				if (!seen[neighbourId]) {
					queue.add(neighbourId);
				}
			}
		}
		return false;
	}

}