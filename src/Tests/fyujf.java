package Tests;

import java.util.Collection;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import gui.Window;

public class fyujf {
	public static void main(String[] args) {
		Node n1 = new Node(0,0,0);
		Node n2 = new Node(1,100,100);
		Node n3 = new Node(2,200,200);
		Node n4 = new Node(3,300,300);
		Node n5 = new Node(4,250,250);
		DGraph temp = new DGraph();
		temp.addNode(n1);
		temp.addNode(n2);
		temp.addNode(n3);
		temp.addNode(n4);
		temp.addNode(n5);
		Graph_Algo ga = new Graph_Algo(temp);
		Window w = new Window(ga);
	}

}
