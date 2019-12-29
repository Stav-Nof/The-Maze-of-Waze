package dataStructure;


import java.util.Collection;
import java.util.HashMap;


public class DGraph implements graph{

	
	private HashMap <Integer,node_data> vertices;
	private HashMap <Integer,HashMap<Integer,edge_data>> edges;
	private int verticeCounter;
	private int edgesCounter;
	int mc;


	public DGraph() {
		vertices = new HashMap <Integer,node_data>();
		edges = new HashMap <Integer,HashMap<Integer,edge_data>>();
		verticeCounter = 0;
		edgesCounter = 0;
		mc=0;
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
			this.verticeCounter = temp.verticeCounter;
		}
		this.vertices.put(n.getKey(),n);
		verticeCounter++;
		mc++;
	}

	
	@Override
	public void connect(int src, int dest, double w) {
		node_data a = getNode(src);
		node_data b = getNode(dest);
		
		if(a != null && b!=null) {
			Edge e = new Edge(this.vertices.get(src), this.vertices.get(dest), w);
			this.edges.get(src).put(dest,e);
			mc++;
		}
	}

	
	@Override
	public Collection<node_data> getV() {
		return (Collection<node_data>) this.vertices;
	}

	
	@Override
	public Collection<edge_data> getE(int node_id) {
		return (Collection<edge_data>) this.edges.get(node_id);
	}

	
	@Override
	public node_data removeNode(int key) {
		if (this.vertices.containsKey(key)) {
			node_data toReturn = this.vertices.remove(key);
			this.edges.remove(key);
			this.verticeCounter--;
			mc++;
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
				mc++;
			}
			return toReturn;
		}
		return null;
	}

	
	@Override
	public int nodeSize() {
		return this.verticeCounter;
	}

	@Override
	public int edgeSize() {
		return this.edgesCounter;
	}

	@Override
	public int getMC() {
		return mc;
	}
	
	
}
