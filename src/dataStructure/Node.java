package dataStructure;

import utils.Point3D;

public class Node implements node_data {
	private int id;
	private Point3D location;
	private double weight;
	private String info;
	private int tag;

	public Node() {
		this.id=0;
		this.location=null;
		this.weight=0;
		this.info="";
		this.tag=0;
	}

	@Override
	public int getKey() {
		return this.id;
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
