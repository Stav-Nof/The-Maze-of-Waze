package dataStructure;

public class Edge implements edge_data {
	private double weight;
	private node_data src;
	private node_data dest;
	private String info;
	private int tag;
	
	
	public Edge() {
		this.src = null;
		this.dest = null;
		this.weight = 0;
		this.info = "";
		this.tag = 0;
	}
	
	
	public Edge(node_data src, node_data dest,double weight) {
		this.src= src;
		this.dest = dest;
		this.weight = weight;
		
	}
	
	
	@Override
	public int getSrc() {
		return this.src.getKey();
	}

	
	@Override
	public int getDest() {
		return this.dest.getKey();
	}

	
	@Override
	public double getWeight() {
		return this.weight;
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
