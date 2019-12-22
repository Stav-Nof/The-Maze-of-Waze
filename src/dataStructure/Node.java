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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Point3D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub

	}

}
