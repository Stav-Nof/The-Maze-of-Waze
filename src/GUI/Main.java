package GUI;

import dataStructure.DGraph;
import dataStructure.Node;
import algorithms.Graph_Algo;

public class Main{

	public static void main(String[] args) {
		Graph_Algo algo = new Graph_Algo();
		algo.init(new DGraph());
		Node n1 = new Node(0,100,100);
		Node n2 = new Node(1,0,0);
		Node n3 = new Node(2,200,200);
		Node n4 = new Node(3,300,300);
		algo.g.addNode(n1);
		algo.g.addNode(n2);
		algo.g.addNode(n3);
		algo.g.addNode(n4);
		Window window = new Window(algo);
		window.setVisible(true);
	}

}
