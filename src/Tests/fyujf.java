package Tests;

import java.util.Collection;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;

public class fyujf {
	public static void main(String[] args) {
		Node n1 = new Node(0,5,5);
		Node n2 = new Node(1,5,5);
		Node n3 = new Node(2,5,5);
		Node n4 = new Node(3,5,5);
		Node n5 = new Node(4,5,5);
		DGraph temp = new DGraph();
		temp.addNode(n1);
		temp.addNode(n2);
		temp.addNode(n3);
		temp.addNode(n4);
		temp.addNode(n5);
		System.out.println((temp.vertices));
		Collection<node_data> collection = temp.getV();
	}

}
