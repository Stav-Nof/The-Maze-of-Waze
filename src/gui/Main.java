package gui;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;

import java.util.Collection;
import java.util.LinkedList;

import algorithms.Graph_Algo;

public class Main{

	public static void main(String[] args) {		
		
		Graph_Algo g = new Graph_Algo();
		g.init("test");		
		
		Window window = new Window(g);
	}

}
