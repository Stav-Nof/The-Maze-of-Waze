package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;

class TestGraph_Algo {



	@Test
	void TestInit() {
		Graph_Algo g = new Graph_Algo();
		g.init(new DGraph());

		Node n = new Node(2,250,250);
		Node n1 = new Node(4,25,20);
		Node n2 = new Node(20,245,40);
		Node n3 = new Node(6,60,75);

		g.g.addNode(n);
		g.g.addNode(n1);
		g.g.addNode(n2);
		g.g.addNode(n3);

		g.g.connect(n.getKey(), n1.getKey(), 5);
		g.g.connect(n.getKey(), n2.getKey(), 5);
		g.g.connect(n2.getKey(), n3.getKey(), 5);

		g.save("test.txt");
		Graph_Algo g1 = new Graph_Algo();
		g1.init("test.txt");
		
		 Collection<node_data> Nodes = g.g.getV();
		 Collection<node_data> Nodes1 = g1.g.getV();
		
		Nodes.add(n);
		Nodes.add(n1);
		Nodes.add(n2);
		Nodes.add(n3);
		
		g.g.connect(n.getKey(), n1.getKey(), 5);
		g.g.connect(n.getKey(), n2.getKey(), 5);
		g.g.connect(n2.getKey(), n3.getKey(), 5);

		
		


		Iterator<node_data> it = Nodes.iterator();
		for (node_data i : Nodes) {
			assertEquals(i.getKey(), it.next().getKey() );
		}
		
	

			
		
		

	}


	@Test
	void testIsConnected() {
		Graph_Algo g = new Graph_Algo();
		g.init(new DGraph());

		Node n = new Node(2,250,250);
		Node n1 = new Node(4,25,20);
		Node n2 = new Node(20,245,40);
		Node n3 = new Node(6,60,75);

		g.g.addNode(n);
		g.g.addNode(n1);
		g.g.addNode(n2);
		g.g.addNode(n3);

		g.g.connect(n.getKey(), n1.getKey(), 5);
		g.g.connect(n.getKey(), n2.getKey(), 5);
		g.g.connect(n2.getKey(), n3.getKey(), 5);

		assertFalse(g.isConnected());
	}


	@Test
	void testIsReachable() {

		Graph_Algo g = new Graph_Algo();
		g.init(new DGraph());

		Node n = new Node(2,250,250);
		Node n1 = new Node(4,25,20);
		Node n2 = new Node(20,245,40);
		Node n3 = new Node(6,60,75);

		g.g.addNode(n);
		g.g.addNode(n1);
		g.g.addNode(n2);
		g.g.addNode(n3);

		g.g.connect(n.getKey(), n1.getKey(), 5);
		g.g.connect(n.getKey(), n2.getKey(), 5);
		g.g.connect(n2.getKey(), n3.getKey(), 5);
		g.g.connect(n3.getKey(), n1.getKey(), 5);

		assertTrue(g.isReachable(n.getKey(), n3.getKey()));
	}

	@Test
	void testShortestPathDist() {
		Graph_Algo g = new Graph_Algo();
		g.init(new DGraph());

		Node n = new Node(2,250,250);
		Node n1 = new Node(4,25,20);
		Node n2 = new Node(20,245,40);
		Node n3 = new Node(6,60,75);


		g.g.addNode(n);
		g.g.addNode(n1);
		g.g.addNode(n2);
		g.g.addNode(n3);


		g.g.connect(n.getKey(), n1.getKey(), 10);
		g.g.connect(n.getKey(), n2.getKey(), 1);
		g.g.connect(n2.getKey(), n1.getKey(), 2);
		g.g.connect(n2.getKey(), n3.getKey(), 45);
		g.g.connect(n1.getKey(), n3.getKey(), 3);

		assertEquals(6,g.shortestPathDist(n.getKey(), n3.getKey()));
	}


	@Test
	void testShortestPath() {

		Graph_Algo g = new Graph_Algo();
		g.init(new DGraph());

		Node n = new Node(2,250,250);
		Node n1 = new Node(4,25,20);
		Node n2 = new Node(20,245,40);
		Node n3 = new Node(6,60,75);


		g.g.addNode(n);
		g.g.addNode(n1);
		g.g.addNode(n2);
		g.g.addNode(n3);


		g.g.connect(n.getKey(), n1.getKey(), 10);
		g.g.connect(n.getKey(), n2.getKey(), 1);
		g.g.connect(n2.getKey(), n1.getKey(), 2);
		g.g.connect(n2.getKey(), n3.getKey(), 45);
		g.g.connect(n1.getKey(), n3.getKey(), 3);
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(2);
		l.add(20);
		l.add(4);
		l.add(6);



		for (int i = 0; i < l.size(); i++) {


			assertEquals(l.get(i),g.shortestPath(n.getKey(), n3.getKey()).get(i).getKey());
		}

	}


	@Test
	void testCopy() {

		Graph_Algo g = new Graph_Algo();
		g.init(new DGraph());

		Node n = new Node(2,250,250);
		Node n1 = new Node(4,25,20);
		Node n2 = new Node(20,245,40);
		Node n3 = new Node(6,60,75);


		g.g.addNode(n);
		g.g.addNode(n1);
		g.g.addNode(n2);
		g.g.addNode(n3);


		g.g.connect(n.getKey(), n1.getKey(), 10);
		g.g.connect(n.getKey(), n2.getKey(), 1);
		g.g.connect(n2.getKey(), n1.getKey(), 2);
		g.g.connect(n2.getKey(), n3.getKey(), 45);
		g.g.connect(n1.getKey(), n3.getKey(), 3);

		Graph_Algo g1 = new Graph_Algo();
		g1.g = g.copy();


	}

}
