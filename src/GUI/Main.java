package GUI;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;

import java.util.Collection;

import algorithms.Graph_Algo;

public class Main{

	public static void main(String[] args) {
		DGraph g = new DGraph();
		node_data n = new Node(5,250,250);
		node_data n1 = new Node(6,65,204);
		node_data n2 = new Node(7,300,300);
		node_data n3 = new Node(8,320,120);
		node_data n4 = new Node(15,202,200);
	
		
	
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
//		g.addNode(n3);
//		g.addNode(n4);
		
		g.connect(n.getKey(), n1.getKey(), 0);
		g.connect(n1.getKey(), n2.getKey(), 1);
		g.connect(n2.getKey(), n.getKey(), 10);
		
		
		
		
		Graph_Algo t = new Graph_Algo();
		t.init(g);
		Window window = new Window(t);
	}

}
