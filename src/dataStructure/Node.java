package dataStructure;

import java.io.Serializable;

import utils.Point3D;

public class Node implements node_data, Serializable {
	private int key;
	private Point3D location;
	private double weight;
	private String info;
	private int tag;



	public Node() {
		this.key=0;
		this.location=null;
		this.weight=0;
		this.info="";
		this.tag=0;
	}
	public Node(int key,double x, double y) {
		this.key = key;
		this.location = new Point3D (x,y);
	}
	
	public Node(int key,Point3D location) {
		this.key = key;
		this.location = location;
	}
	
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;

	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}
	
	
}
