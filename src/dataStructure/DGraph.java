package dataStructure;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


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
		this.edges.put(n.getKey(),new HashMap<Integer,edge_data>());
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
			this.edgesCounter++;
			this.mc++;
		}


	}


	@Override
	public Collection<node_data> getV() {
		Collection<node_data> ans =  new LinkedList<node_data>();
		int counter = this.verticeCounter;
		for (int i = 0; i <= counter; i++) {
			if (this.vertices.get(i) != null) {
				ans.add(this.vertices.get(i));
			}
		}
		return ans;
	}


	@Override
	public Collection<edge_data> getE(int node_id) {
		Collection<edge_data> ans =  new LinkedList<edge_data>();
		int counter = this.verticeCounter;
		for (int i = 0; i <= counter; i++) {
			if (this.edges.get(node_id).get(i) != null) {
				ans.add(this.edges.get(node_id).get(i));
			}
		}
		return ans;
	}


	@Override
	public node_data removeNode(int key) {
		if (this.vertices.containsKey(key)) {
			node_data toReturn = this.vertices.remove(key);
			int num = this.edges.get(key).size();
			this.edges.remove(key);
			this.verticeCounter = this.verticeCounter - num;
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
