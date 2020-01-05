package algorithms;

import java.util.Collection;
import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;


/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable{
	public graph g;
	public int mc;


	public Graph_Algo(graph _graph) {
		this.g = _graph;
		this.mc = g.getMC();
	}


	public Graph_Algo() {
		this.g = null;
		this.mc = 0;
	}



	@Override
	public void init(graph g) {
		this.g = g;
		this.mc = g.getMC();
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
			return;
		} 
		catch(ClassNotFoundException ex) { 
			System.out.println("ClassNotFoundException is caught"); 
			return;
		}
		this.g = temp.g;
	}


	@Override
	public void save(String file_name) {
		try{    
			FileOutputStream file = new FileOutputStream(new File(file_name)); 
			ObjectOutputStream ous = new ObjectOutputStream(file); 
			ous.writeObject(this); 
			ous.close(); 
			file.close(); 
		}
		catch(IOException ex) {
			ex.printStackTrace();
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


	public void isReachableExtend(int src) {
		if (this.g.getNode(src).getTag() == 1) {
			return;
		}
		this.g.getNode(src).setTag(1);
		Collection<edge_data> collection = this.g.getE(src);
		for (edge_data i : collection) {
			if(this.g.getNode(i.getDest()).getTag() == 1) {
				continue;
			}
			isReachableExtend(i.getDest());
		}
	}


	public boolean isReachable(int src,int dest) {
		resetNodeTags();
		isReachableExtend(src);
		if (this.g.getNode(dest).getTag() == 1)return true;
		return false;
	}


	@Override
	public double shortestPathDist(int src, int dest) {
		if (!(isReachable(src, dest))) {
			return 0;
		}
		shortestPathcalc(src, dest);
		return this.g.getNode(dest).getWeight();
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


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if (!(isReachable(src, dest))) {
			return null;
		}
		shortestPathcalc(src, dest);
		List<node_data> ans = new LinkedList<node_data>();
		String path = this.g.getNode(dest).getInfo();
		StringTokenizer tokenizer = new StringTokenizer(path);
		while (tokenizer.hasMoreElements()) {
			String nodeKey = tokenizer.nextToken(",");
			ans.add(this.g.getNode(Integer.parseInt(nodeKey)));
		}
		ans.add(this.g.getNode(dest));
		return ans;
	}


	public void shortestPathcalc(int src, int dest) {
		this.setAllWeight();
		this.resetNodeTags();
		this.g.getNode(src).setWeight(0);
		this.g.getNode(src).setInfo("");
		shortestPathcalcExtend(src);
	}


	public void shortestPathcalcExtend(int src){
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
			String newpath = this.g.getNode(src).getInfo() + "," + g.getNode(src).getKey();
			if (newWeight < this.g.getNode(i.getDest()).getWeight()) {
				this.g.getNode(i.getDest()).setInfo(newpath);
				this.g.getNode(i.getDest()).setWeight(newWeight);
			}
		}
		node_data temp = minWeightVal();
		if (temp != null) {
			shortestPathcalcExtend(temp.getKey());
		}
	}


	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if(!isConnected())return null;
		if (targets.isEmpty())return new LinkedList<node_data>();
		if (targets.size() == 1) {
			List<node_data> ans = new LinkedList<node_data>();
			ans.add(this.g.getNode(targets.get(0)));
			return ans;
		}
		List<node_data> ans = new LinkedList<node_data>();
		int destId = targets.get(1);
		List<node_data> toAdd = shortestPath(targets.get(0), destId);
		ans.addAll(toAdd);
		for (node_data i : toAdd) {
			if (targets.contains(i.getKey())) {
			targets.remove(targets.indexOf(i.getKey()));
			}
		}
		ans.addAll(TSP(destId, targets));
		return ans;
	}


	public List<node_data> TSP(int src, List<Integer> targets){
		if (targets.isEmpty())return new LinkedList<node_data>();
		if (targets.size() == 1) {
			List<node_data> ans = new LinkedList<node_data>();
			ans.add(this.g.getNode(targets.get(0)));
			return ans;
		}
		List<node_data> ans = new LinkedList<node_data>();
		int destId = targets.get(1);
		List<node_data> toAdd = shortestPath(targets.get(0), destId);
		ans.addAll(toAdd);
		for (node_data i : toAdd) {
			if (targets.contains(i.getKey())) {
			targets.remove(targets.indexOf(i.getKey()));
			}
		}
		ans.addAll(TSP(destId, targets));
		return ans;
	}

	@Override
	public graph copy() {
		DGraph copy = new DGraph();
		Collection<node_data> collection = this.g.getV();
		for (node_data i : collection) {
			Node temp = new Node(i.getKey(),new Point3D(i.getLocation()));
			copy.addNode(temp);
		}
		for (node_data i : collection) {
			Collection<edge_data> edges = this.g.getE(i.getKey());
			for (edge_data j : edges) {
				copy.connect(i.getKey(), j.getDest(), j.getWeight());
			}
		}
		return copy;
	}


}