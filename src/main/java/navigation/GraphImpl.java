package navigation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Implement your graph representation here. This class will be instantiated
 * during the unit tests.
 */
public class GraphImpl implements Graph {
	private Vertex[] vertices;
	private Edge[] edges;

	@Override
	public void initializeFromFile(File inputXmlFile) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputXmlFile);
			doc.getDocumentElement().normalize();
			Node graph = doc.getFirstChild();
			String nodeName;
			int vertexCounter = 0;
			int edgeCounter = 0;
			for(Node n = graph.getFirstChild(); n.getNextSibling() != null; n = n.getNextSibling()) {
				nodeName = n.getNodeName();
				if(nodeName.equals("node")){
					++vertexCounter;
				} else if(nodeName.equals("edge")) {
					++edgeCounter;
				}
			}
			this.vertices = new Vertex[vertexCounter];
			this.edges = new Edge[edgeCounter];
			vertexCounter = 0;
			edgeCounter = 0;
			for(Node n = graph.getFirstChild(); n.getNextSibling() != null; n=n.getNextSibling()) {
				nodeName = n.getNodeName();
				if(nodeName.equals("node")){
					this.vertices[vertexCounter] = readVertexFrom(n);
					this.vertices[vertexCounter].setId(vertexCounter + 1);
					++vertexCounter;
				} else if (nodeName.equals("edge")) {
					this.edges[edgeCounter] = readEdgeFrom(n);
					Vertex[] ends = this.edges[edgeCounter].getVertices();
					ends[0].addEdge(this.edges[edgeCounter], true);
					ends[1].addEdge(this.edges[edgeCounter], false);
					++edgeCounter;
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	private Vertex readVertexFrom(Node node) {
		NodeList nodeList = node.getChildNodes();
		double x = 0.0;
		double y = 0.0;
		char axis;
		double coordinate;
		Node currentNode;
		for(int i = 0; i < nodeList.getLength(); ++i){
			currentNode = nodeList.item(i);
			if(currentNode.getNodeName().equals("property")){
				axis = currentNode.getAttributes().getNamedItem("name").getNodeValue().charAt(0);
				coordinate = Double.parseDouble(currentNode.getChildNodes().item(0).getNodeValue());
				if(axis == 'x') {
					x = coordinate;
				} else if (axis == 'y'){
					y = coordinate;
				}
			}
		}
		return new Vertex(x, y);
	}
	
	// Assume we have already read the vertices
	private Edge readEdgeFrom(Node node) {
		NodeList nodeList = node.getChildNodes();
		String name;
		int value;
		Node currentNode;
		Vertex startVertex = null;
		Vertex endVertex = null;
		int speed = -1;
		
		for(int i = 0; i < nodeList.getLength(); ++i){
			currentNode = nodeList.item(i);
			if(currentNode.getNodeName().equals("property")){
				name = currentNode.getAttributes().getNamedItem("name").getNodeValue();
				value = Integer.parseInt(currentNode.getChildNodes().item(0).getNodeValue());
				if(name.equals("startNode")) {
					startVertex = this.vertices[value - 1];
				} else if (name.equals("endNode")){
					endVertex = this.vertices[value - 1];
				} else if(name.equals("averageSpeed")){
					speed = value;
				}
			}
		}
		
		return new Edge(startVertex, endVertex, speed);
	}
	
	public int getNumVertices(){
		return this.vertices.length;
	}
	
	public int getNumEdges(){
		return this.edges.length;
	}
	
	public Edge getEdge(int index){
		return this.edges[index];
	}
	
	public Vertex getVertex(int index){
		return this.vertices[index];
	}

}
