package dataStructure;


import java.util.Collection;
import java.util.HashMap;


public class DGraph implements graph{

	
	HashMap <Integer,node_data> vertices;
	HashMap <Integer,HashMap<Integer,edge_data>> 	edges;
	int verticeCounter;
	int edgesCounter;


	public DGraph() {
		vertices = new HashMap <Integer,node_data>();
		edges = new HashMap <Integer,HashMap<Integer,edge_data>>();
		verticeCounter = 0;
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
			this.verticeCounter = temp.verticeCounter;
		}
		this.vertices.put(n.getKey(),n);
		verticeCounter++;
	}

	
	@Override
	public void connect(int src, int dest, double w) {
		node_data a = getNode(src);
		node_data b = getNode(dest);
		
		if(a != null && b!=null) {
			Edge e = new Edge(w);
			this.edges.get(src).put(dest,e);
		}
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
			this.verticeCounter--;
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
		return this.verticeCounter;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return this.edgesCounter;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
