import java.util.Collection;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DGraph g = new DGraph();
		node_data n = new Node(1,3);
		node_data n1 = new Node(2,5);
		node_data n2 = new Node(3,2);
		node_data n3 = new Node(4,3);
		node_data n4 = new Node(5,1);
		
		Edge e = new Edge(n,n1,3);
		System.out.println(e.getSrc());
		System.out.println(e.getDest());
		g.addNode(n);
		g.addNode(n1);
		
		g.connect(n.getKey(), n1.getKey(), 3);
		

	}
	


}
