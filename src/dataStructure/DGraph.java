package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	HashMap <Integer,node_data> vertices;
	HashMap <Integer,HashMap<Integer,edge_data>> edges;
	int v;
	
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
		this.vertices.put(n.getKey(),n);
		v++;

	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		if (vertices.containsKey(key)) {
			
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
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