package algorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		Collection<node_data> collection = this.g.getV();
		for (node_data i : collection) {
			for (node_data j : collection) {
				if (i.getKey() != j.getKey()) {
					if(!(isReachable(i.getKey(), j.getKey()))) {
						return false;
					}
				}
			}

		}
		return true;
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


	@Override
	public double shortestPathDist(int src, int dest) {
		return 0;
	}


	public void setAllWeight() {
		Collection<node_data> collection = this.g.getV();
		for (node_data i : collection) {
			i.setWeight(Double.POSITIVE_INFINITY);
		}
	}


	public void resetNodeTags() {
		Collection<node_data> collection = this.g.getV();
		for (node_data i : collection) {
			i.setTag(0);
		}
	}


	public node_data minWeightVal() {
		Collection<node_data> collection = this.g.getV();
		node_data ans = null;
		for (node_data i : collection) {
			if (ans == null && i.getTag() ==  0) {
				ans = i;
			}
			if (i.getTag() ==  0 && i.getWeight() < ans.getWeight()) {
				ans = i;
			}
		}
		return ans;
	}


	public void shortestPathExtend(int src){
		if (this.g.getNode(src).getTag() == 1) {
			return;
		}
		this.g.getNode(src).setTag(1);
		Collection<edge_data> collection = this.g.getE(src);
		for (edge_data i : collection) {
			if(this.g.getNode(i.getDest()).getTag() == 1) {
				continue;
			}
			double newWeight = this.g.getNode(src).getWeight() + i.getWeight();
			if (newWeight < this.g.getNode(i.getDest()).getWeight()) {
				this.g.getNode(i.getDest()).setInfo("" + src);
				this.g.getNode(i.getDest()).setWeight(newWeight);
			}
		}
		node_data temp = minWeightVal();
		if (temp != null) {
			shortestPathExtend(temp.getKey());
		}
	}


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if (!(isReachable(src, dest))) {
			return null;
		}
		this.setAllWeight();
		this.resetNodeTags();
		this.g.getNode(src).setWeight(0);
		this.g.getNode(src).setTag(1);
		shortestPathExtend(src);
		Collection<edge_data> collection = this.g.getE(src);
		node_data temp = this.g.getNode(dest);
		LinkedList<Integer> upsideDown = new LinkedList<>();
		while (true) {
			if (temp.getKey() == src) {
				break;
			}
			upsideDown.add(temp.getKey());
			temp = this.g.getNode(Integer.parseInt(temp.getInfo()));
		}
		Object[] upsideDown1 =  upsideDown.toArray();
		LinkedList<node_data> ans = new LinkedList<>();
			for (int i = upsideDown1.length-1; i >= 0; i--) {
				ans.add(this.g.getNode((int)upsideDown1[i]));
			}
		return ans;
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
