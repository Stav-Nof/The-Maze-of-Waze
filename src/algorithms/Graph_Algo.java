package algorithms;

import java.util.Collection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.w3c.dom.Node;
import dataStructure.DGraph;
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
	public graph g;



	@Override
	public void init(graph g) {
		this.g = g;
	}


	@Override
	public void init(String file_name) {
		Graph_Algo temp = null; 
		try{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 
			temp = (Graph_Algo)in.readObject(); 
			in.close(); 
			file.close(); 
		} 
		catch(IOException ex) { 
			System.out.println("IOException is caught"); 
		} 
		catch(ClassNotFoundException ex) { 
			System.out.println("ClassNotFoundException is caught"); 
		}
		this.g = temp.g;
	}


	@Override
	public void save(String file_name) {
		try{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream ous = new ObjectOutputStream(file); 
			ous.writeObject(this); 
			ous.close(); 
			file.close(); 
		}
		catch(IOException ex) {
			System.out.println("IOException is caught"); 
		}
	}


	@Override
	public boolean isConnected() {
		Collection<node_data> collection = this.g.getV();
		boolean ans = true;
		for (node_data i : collection) {
			for (node_data j : collection) {
				if (i.getKey() != j.getKey()) {
					if(!(isReachable(i.getKey(), j.getKey()))) {
						return false;
					}
				}
			}

		}
		return ans;
	}


	public boolean isReachable(int src,int dest) {
		Collection<edge_data> collection = this.g.getE(src);
		boolean ans = false;
		for(edge_data i : collection) {
			int e = i.getDest();
			if(e == dest) {
				ans = true ;
			}
			else {
				return isReachable(e,dest);
			}
		}
		return ans;
	}


	public void resetNodeTags() {
		Collection<node_data> collection = this.g.getV();
		for (node_data i : collection) {
			i.setTag(0);
		}
	}


	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub

		if (isReachable(src,dest) == false) {
			return 0;
		}

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
		//graph copy= new DGraph();
	//	for (Iterator<Node>verticeCounter : iterable) {	
		//}
		this.save("copiedGraph");
		Graph_Algo copiedGraph= new Graph_Algo ();
		copiedGraph.init("copiedGraph");
	
		return copiedGraph.g;
	}




}
