package GUI;

import java.awt.BasicStroke;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.PopupFactory;
import algorithms.Graph_Algo;
import dataStructure.Node;
import dataStructure.edge_data;
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
		Collection<node_data> collection = this.ga.g.getV();
		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		for (node_data i : collection) {
			Point3D temp = i.getLocation();
			if (temp.ix() > maxX) {
				maxX = temp.ix();
			}
			if (temp.iy() > maxY) {
				maxY = temp.iy();
			}
		}
		this.setSize((int)(maxX+ 100),(int)(maxY+ 100));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font myFont = new Font ("Arial", 1, 15);
		this.setFont (myFont);

		MenuBar menuBar = new MenuBar();

		Menu graph = new Menu("graph");
		Menu algorithms = new Menu("algorithms");
		menuBar.add(algorithms);
		menuBar.add(graph);
		this.setMenuBar(menuBar);
		//algorithms menu
		MenuItem isConnected = new MenuItem("is Connected");
		isConnected.addActionListener(this);
		MenuItem shortestPathDist = new MenuItem("shortest Path distance");
		shortestPathDist.addActionListener(this);
		MenuItem shortestPath = new MenuItem("shortest Path");
		shortestPath.addActionListener(this);
		MenuItem TSP = new MenuItem("TSP");
		TSP.addActionListener(this);
		algorithms.add(isConnected);
		algorithms.add(shortestPathDist);
		algorithms.add(shortestPath);
		algorithms.add(TSP);
		//graph menu
		MenuItem loadGraph = new MenuItem("load graph");
		loadGraph.addActionListener(this);
		MenuItem saveGraph = new MenuItem("save graph");
		saveGraph.addActionListener(this);
		MenuItem addVertices = new MenuItem("add vertices");
		addVertices.addActionListener(this);
		MenuItem connect = new MenuItem("connect");
		connect.addActionListener(this);
		graph.add(loadGraph);
		graph.add(saveGraph);
		graph.add(addVertices);
		graph.add(connect);
		this.setVisible(true);

	}

	public void paint(Graphics g) {
		super.paint(g);
		Collection<node_data> collection = ga.g.getV();
		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		for (node_data i : collection) {
			Point3D temp = i.getLocation();
			if (temp.ix() > maxX) {
				maxX = temp.ix();
			}
			if (temp.iy() > maxY) {
				maxY = temp.iy();
			}
		}
		this.setSize((int)(maxX+ 100),(int)(maxY+ 100));
		for (node_data i : collection) {
			Point3D Location = i.getLocation();
			g.setColor(Color.BLUE);
			g.fillOval(Location.ix()+10, Location.iy()+50, 10, 10);
			g.drawString("" + i.getKey(), Location.ix()+5, Location.iy()+50);
		}
		Graphics2D g2 = (Graphics2D) g;
		for (node_data i : collection) {
			g2.setStroke(new BasicStroke(3));
			Collection<edge_data> collection1 = ga.g.getE(i.getKey());
			for (edge_data j : collection1) {
				g.setColor(Color.RED);
				Point3D from = i.getLocation();
				Point3D to = this.ga.g.getNode(j.getDest()).getLocation();
				g2.drawLine(from.ix()+15, from.iy()+55, to.ix()+15, to.iy()+55);
				g.drawString("" + j.getWeight(), ((from.ix() + to.ix())/2)+15,  ((from.iy() + to.iy())/2)+55);
				g.setColor(Color.YELLOW);
				int xGoBack = (from.ix() - to.ix())/10;
				int yGoBack = (from.iy() - to.iy())/10;
				g.fillOval(to.ix() + xGoBack +10, to.iy() + yGoBack +50, 10, 10);
			}
		}
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("TSP")) {
			if (!this.ga.isConnected()) {
				JOptionPane.showMessageDialog(null, "the graph not conected.\nyou cent use this option");
				return;

			}
			final JFrame TSP = new JFrame();
			Collection<node_data> temp = this.ga.g.getV();
			JCheckBox choices [] = new JCheckBox[temp.size()];
			int i = 0;
			for(node_data j: temp) {
				choices[i] = new JCheckBox("" + j.getKey());
				i++;
			}
			Object[] lines = {"chooses nodes", choices};
			if(JOptionPane.showConfirmDialog(TSP, lines, "TSP", JOptionPane.DEFAULT_OPTION) == -1)return;
			List<Integer> nodesSelected = new LinkedList<Integer>();
			for (i = 0; i < choices.length; i++) {
				if(choices[i].isSelected()) {
					nodesSelected.add(Integer.parseInt(choices[i].getText()));
				}
			}
			List<node_data> ans = this.ga.TSP(nodesSelected);
			String sAns = "";
			for (node_data j : ans) {
				sAns = sAns + "," + j.getKey();
			}
			sAns = (String) sAns.subSequence(1,sAns.length());
			JOptionPane.showMessageDialog(null, "the TSP is:\n" + sAns);
			return;
		}

		if (action.equals("shortest Path")) {
			final JFrame shortestPathDist = new JFrame();
			if (!this.ga.isConnected()) {
				JOptionPane.showMessageDialog(null, "the graph not conected.\nyou cent use this option");
				return;

			}
			String sStart = JOptionPane.showInputDialog(shortestPathDist,"enter a start vertices key", null);
			int start = 0;
			if(sStart == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				start = Integer.parseInt(sStart);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe Action canceled");
				return;
			}
			if (this.ga.g.getNode(start) == null) {
				JOptionPane.showMessageDialog(null, "the key you enterd does not exist\nthe Action canceled");
				return;
			}
			String sEnd = JOptionPane.showInputDialog(shortestPathDist,"enter a end vertices key", null);
			int end = 0;
			if(sEnd == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				end = Integer.parseInt(sEnd);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe Action canceled");
				return;
			}
			if (this.ga.g.getNode(end) == null) {
				JOptionPane.showMessageDialog(null, "the key you enterd does not exist\nthe Action canceled");
				return;
			}
			if (start == end) {
				JOptionPane.showMessageDialog(null, "the start and the end key is equals");
				return;
			}
			List<node_data> ans = this.ga.shortestPath(start, end);
			String sAns = "";
			for (node_data i : ans) {
				sAns = sAns + "," + i.getKey();
			}
			sAns = (String) sAns.subSequence(1,sAns.length());
			JOptionPane.showMessageDialog(null, "the shortest Path is:\n" + sAns);
			return;
		}
		if (action.equals("shortest Path distance")) {
			final JFrame shortestPathDist = new JFrame();
			if (!this.ga.isConnected()) {
				JOptionPane.showMessageDialog(null, "the graph not conected.\nyou cent use this option");
				return;

			}
			String sStart = JOptionPane.showInputDialog(shortestPathDist,"enter a start vertices key", null);
			int start = 0;
			if(sStart == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				start = Integer.parseInt(sStart);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe Action canceled");
				return;
			}
			if (this.ga.g.getNode(start) == null) {
				JOptionPane.showMessageDialog(null, "the key you enterd does not exist\nthe Action canceled");
				return;
			}
			String sEnd = JOptionPane.showInputDialog(shortestPathDist,"enter a end vertices key", null);
			int end = 0;
			if(sEnd == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				end = Integer.parseInt(sEnd);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe Action canceled");
				return;
			}
			if (this.ga.g.getNode(end) == null) {
				JOptionPane.showMessageDialog(null, "the key you enterd does not exist\nthe Action canceled");
				return;
			}
			if (start == end) {
				JOptionPane.showMessageDialog(null, "the shortest Path distance is:\n0.0");
				return;
			}
			double ans = this.ga.shortestPathDist(start, end);
			if (ans == 0) {
				JOptionPane.showMessageDialog(null, "this graph is not conected.\ncent find path");
				return;
			}
			JOptionPane.showMessageDialog(null, "the shortest Path distance is:\n" + this.ga.shortestPathDist(start, end));
			return;
		}
		if (action.equals("is Connected")) {
			if (this.ga.isConnected()) {
				JOptionPane.showMessageDialog(null, "this graph is conected");
				return;
			}
			else {
				JOptionPane.showMessageDialog(null, "this graph is not conected");
				return;
			}
		}
		if (action.equals("load graph")) {
			final JFrame load = new JFrame();
			String file_name = JOptionPane.showInputDialog(load,"enter an file name", null);
			if(file_name == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			this.ga.init(file_name);
			repaint();
		}
		if (action.equals("save graph")) {
			final JFrame save = new JFrame();
			String file_name = JOptionPane.showInputDialog(save,"enter an file name", null);
			if(file_name == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			this.ga.save(file_name);
			repaint();
		}
		if(action.equals("add vertices")){
			final JFrame toAdd = new JFrame();
			String sX = JOptionPane.showInputDialog(toAdd,"enter an x value", null);
			if(sX == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			String sY = JOptionPane.showInputDialog(toAdd,"enter an y value", null);
			if(sY == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			String sKey = JOptionPane.showInputDialog(toAdd,"enter an key", null);
			if(sKey == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			Node temp = null;
			try {
				temp = new Node(Integer.parseInt(sKey),Double.parseDouble(sX), Double.parseDouble(sY));
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe vertex did not added");
				return;
			}
			ga.g.addNode(temp);
			repaint();
		}
		if(action.equals("connect")) {
			final JFrame connect = new JFrame();
			int from = 0;
			int to = 0;
			double weight = 0;
			String sFrom = JOptionPane.showInputDialog(connect,"enter a vertices key to conect from", null);
			if(sFrom == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				from = Integer.parseInt(sFrom);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe vertices did not conect");
				return;
			}
			if (this.ga.g.getNode(from) == null) {
				JOptionPane.showMessageDialog(null, "the key you entered doesnt exist\nthe vertices did not conect");
				return;
			}
			String sto = JOptionPane.showInputDialog(connect,"enter a vertices key to conect to", null);
			if(sto == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				to = Integer.parseInt(sto);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe vertices did not conect");
				return;
			}
			if (this.ga.g.getNode(to) == null) {
				JOptionPane.showMessageDialog(null, "the key you entered doesnt exist\nthe vertices did not conect");
				return;
			}
			String sweight = JOptionPane.showInputDialog("enter a edge weight");
			if(sweight == null) {
				JOptionPane.showMessageDialog(null, "Action canceled");
				return;
			}
			try {
				weight = Double.parseDouble(sweight);
			}
			catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(null, "you entered a char instead of a number!\nthe vertices did not conect");
				return;
			}
			this.ga.g.connect(from, to, weight);
			repaint();
		}

	}
}