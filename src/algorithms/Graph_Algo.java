package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	
	
	graph g;

	@Override
	public void init(graph g) {
		this.g = g;
	}

	@Override
	public void init(String file_name)  {
		
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		
		if (isReachable(src,dest) == false) {
			return 0;
		}
		
	}
	
	public boolean isReachable(int src,int dest) {
		
		Collection<edge_data> collection = (Collection<edge_data>) this.g.getE(src);
		boolean bool = false;
		for(edge_data i : collection) {
			
			int e = i.getDest();
			int b = this.g.getNode(dest).getKey();
			
			if(e == b) {
				bool = true ;
			}
			else {
				return isReachable(e,b);
			}
		}
		
		return bool;
	
		
		
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
