package GUI;

import dataStructure.DGraph;
import dataStructure.Node;
import algorithms.Graph_Algo;

public class Main{

	public static void main(String[] args) {
		Graph_Algo algo = new Graph_Algo();
		algo.init(new DGraph());
		Node n1 = new Node(0,5,5);
		Node n2 = new Node(1,5,5);
		algo.g.addNode(n1);
		algo.g.addNode(n2);
		Window window = new Window(algo);
		window.setVisible(true);
	}

}
