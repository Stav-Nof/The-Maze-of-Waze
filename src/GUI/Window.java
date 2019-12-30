package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import dataStructure.Node;
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
	}

	public void paint(Graphics g) {
		super.paint(g);
		Collection<node_data> collection = ga.g.getV();
		g.setColor(Color.BLUE);
		for (node_data i : collection) {
			Point3D Location = i.getLocation();
			g.fillOval(Location.ix(), Location.iy(), 10, 10);
		}

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if(action.equals("add vertices")){
			final JFrame toAdd = new JFrame();
			String sX = JOptionPane.showInputDialog(toAdd,"enter an x valu", null);
			String sY = JOptionPane.showInputDialog(toAdd,"enter an y valu", null);
			String sKey = JOptionPane.showInputDialog(toAdd,"enter an key", null);
			Node temp = null;
			try {
				temp = new Node(Integer.parseInt(sKey), Double.parseDouble(sX), Double.parseDouble(sY));
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe vertices did not added");
			}
			ga.g.addNode(temp);
			repaint();
		}

	}
}