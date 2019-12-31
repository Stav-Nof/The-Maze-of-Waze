package GUI;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;

import java.util.Collection;

import algorithms.Graph_Algo;

public class Main{

	public static void main(String[] args) {
		Graph_Algo algo = new Graph_Algo();
		algo.init(new DGraph());
		Node n1 = new Node(0,100,100);
		Node n2 = new Node(1,100,200);
		Node n3 = new Node(2,200,100);
		Node n4 = new Node(3,200,200);
		algo.g.addNode(n1);
		algo.g.addNode(n2);
		algo.g.addNode(n3);
		algo.g.addNode(n4);
		Collection<node_data> collection1 = algo.g.getV();
		Window window = new Window(algo);
		window.setVisible(true);
	}

}
