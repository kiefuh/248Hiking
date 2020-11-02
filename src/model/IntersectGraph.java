package model;

import java.util.ArrayList;	
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class IntersectGraph {
	  private TreeMap<Intersect, List<Intersect>> adjVertices= new TreeMap<Intersect, List<Intersect>>();
	  private TreeMap<String,Intersect> mapOfIntersects= new TreeMap<String,Intersect>();

	public IntersectGraph() {
		super();
	}

	public TreeMap<Intersect, List<Intersect>> getAdjVertices() {
		return adjVertices;
	}

	public void setAdjVertices(TreeMap<Intersect, List<Intersect>> adjVertices) {
		this.adjVertices = adjVertices;
	}
	
	public TreeMap<String,Intersect> getMapOfIntersects(){
		return mapOfIntersects;
	}
	  
	public void addIntersect(String intersectName, Integer height) {
		Intersect intersect= new Intersect(height, intersectName);
		this.adjVertices.putIfAbsent(intersect, new ArrayList<>());
		this.mapOfIntersects.putIfAbsent(intersectName, intersect);
	}
	
	public void removeIntersect(String intersectName) {
		Intersect intersect= new Intersect(intersectName);
		adjVertices.values().stream().forEach(x->x.remove(intersect));
	}
	
	public void addEdge(String intersectName, String intersectName2) {
	    Intersect intersect1 = mapOfIntersects.get(intersectName);
	    Intersect intersect2 = mapOfIntersects.get(intersectName2);
	    adjVertices.get(intersect1).add(intersect2);
	    adjVertices.get(intersect2).add(intersect1);
	}
	
	public void removeEdge(String intersectName, String intersectName2) {
		Intersect intersect1 = mapOfIntersects.get(intersectName);
		Intersect intersect2 = mapOfIntersects.get(intersectName2);
	    List<Intersect> eV1 = adjVertices.get(intersect1);
	    List<Intersect> eV2 = adjVertices.get(intersect2);
	    eV1.remove(intersect2);
	    eV2.remove(intersect1);
	}
	
	
}
