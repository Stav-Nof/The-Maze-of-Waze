package dataStructure;


import java.util.Collection;
import java.util.HashMap;


public class DGraph implements graph{
	HashMap <Integer,node_data> vertices; //pints
	HashMap <Integer,HashMap<Integer,edge_data>> edges;//lines
	int verticesCounter;
	int edgesCounter;


	public DGraph() {
		vertices = new HashMap <Integer,node_data>();
		edges = new HashMap <Integer,HashMap<Integer,edge_data>>();
		verticesCounter = 0;
		edgesCounter = 0;
	}
	
	
	@Override
	public node_data getNode(int key) {
		if(vertices.containsKey(key)){
			return vertices.get(key);
		} else
			return null;
	}


	@Override
	public edge_data getEdge(int src, int dest) {
		if (edges.containsKey(src) && edges.get(src).containsKey(dest)) {
			return edges.get(src).get(dest);
		}
		else {
			return null;
		}
	}

	
	@Override
	public void addNode(node_data n) {
		if (this.vertices == null) {
			DGraph temp = new DGraph();
			this.edges = temp.edges;
			this.vertices = temp.vertices;
			this.verticesCounter = temp.verticesCounter;
		}
		this.vertices.put(n.getKey(),n);
		verticesCounter++;
	}

	
	@Override
	public void connect(int src, int dest, double w) {
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<node_data> getV() {
		return (Collection<node_data>) this.vertices;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<edge_data> getE(int node_id) {
		return (Collection<edge_data>) this.edges.get(node_id);
	}

	
	@Override
	public node_data removeNode(int key) {
		if (this.vertices.containsKey(key)) {
			node_data toReturn = this.vertices.remove(key);
			this.edges.remove(key);
			this.verticesCounter--;
			return toReturn;
		}
		return null;
	}

	
	@Override
	public edge_data removeEdge(int src, int dest) {
		if (this.edges.containsKey(src)) {
			edge_data toReturn = this.edges.get(src).remove(dest);
			if (toReturn != null) {
				this.edgesCounter--;
			}
			return toReturn;
		}
		return null;
	}

	
	@Override
	public int nodeSize() {
		return this.verticesCounter;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
