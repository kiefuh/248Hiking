package util;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import model.Intersect;
import model.IntersectGraph;

public class IntersectGraphUtil {
	public static LinkedList<Intersect> depthFirstTraversal(IntersectGraph graph, String root) {
	    LinkedList<Intersect> visited = new LinkedList<Intersect>();
	    Stack<Intersect> stack = new Stack<Intersect>();
	    Intersect intersect= new Intersect(root);
	    stack.push(graph.getMapOfIntersects().get(root));
	    while (!stack.isEmpty()) {
	        Intersect searchIntersect = stack.pop();
	        if (!visited.contains(searchIntersect)) {
	            visited.add(searchIntersect);
	            for (Intersect i : graph.getAdjVertices().get(intersect)) {              
	                stack.push(i);
	            }
	        }
	    }
	    return visited;
	}
}
