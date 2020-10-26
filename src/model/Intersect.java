package model;

public class Intersect implements Comparable<Intersect> {
	Integer height;
	String name;
	public Intersect(Integer height, String name) {
		super();
		this.height = height;
		this.name = name;
	}
	public Intersect( String name) {
		super();
		this.name = name;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(Intersect o) {
		return this.name.compareTo(o.getName());
	}
	
	
}
