package demo;

import java.util.LinkedList;
import java.util.Set;

import model.IntersectGraph;
import util.IntersectGraphUtil;

public class Demo {

	public static void main(String[] args) {
		IntersectGraph intersectGraph= new IntersectGraph();
		intersectGraph.addIntersect("0", 10);
		intersectGraph.addIntersect("1", 30);
		intersectGraph.addIntersect("2", 100);
		intersectGraph.addIntersect("3", 20);
		intersectGraph.addEdge("0", "1");
		intersectGraph.addEdge("1", "2");
		intersectGraph.addEdge("0", "3");
		intersectGraph.addEdge("3", "2");
		
		}

}
