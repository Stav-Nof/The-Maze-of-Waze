package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

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
		this.resetNodeTags();
		Collection<node_data> collection = this.g.getV();
		for (node_data temp : collection) {
			if (temp != null) {
				tag(temp.getKey());
				break;
			}
		}
		for (node_data i : collection) {
			if (i.getTag() == 0) {
				return false;
			}
		}
		return true;
	}


	public void tag(int key) {
		Collection<edge_data> collection = this.g.getE(key);
		for (edge_data i : collection) {
			if(i != null) {
				if (g.getNode(i.getDest()).getTag() == 0) {
					g.getNode(i.getDest()).setTag(1);
					tag(i.getDest());
				}
			}
		}
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
		return 0;
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
