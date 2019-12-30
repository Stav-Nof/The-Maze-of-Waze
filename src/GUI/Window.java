package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.JFrame;
import algorithms.Graph_Algo;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener {
	Graph_Algo ga;

	public Window() {
		this.ga = new Graph_Algo();
		firstWindow();
	}
	
	
	public Window(Graph_Algo g) {
		this.ga = g;
		firstWindow();
	}
	

	private void firstWindow() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();

		Menu graph = new Menu("graph");

		menuBar.add(graph);
		this.setMenuBar(menuBar);

		MenuItem addVertices = new MenuItem("add vertices");
		addVertices.addActionListener(this);

		MenuItem item2 = new MenuItem("Item 2");
		item2.addActionListener(this);

		graph.add(addVertices);
		graph.add(item2);
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Collection<node_data> collection = ga.g.getV();
		g.setColor(Color.BLUE);
		for (node_data i : collection) {
			Point3D Location = i.getLocation();
			g.fillOval(Location.ix(), Location.iy(), 10, 10);
		}
		
		

//		Point3D prev = null;
//
//		for (Point3D p : points) 
//		{
//			g.setColor(Color.BLUE);
//			g.fillOval((int)p.x(), (int)p.y(), 10, 10);
//
//			if(prev != null)
//			{
//				g.setColor(Color.RED);
//				g.drawLine((int)p.x(), (int)p.y(), 
//						(int)prev.x(), (int)prev.y());
//
//				g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
//			}
//
//			prev = p;
//		}
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if(action.equals("add vertices")){
			System.out.println("add vertices");
			repaint();
		}

	}

}
