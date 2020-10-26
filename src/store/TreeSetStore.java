package store;



import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.Trail;

public class TreeSetStore {
	TreeSet<Trail> trailName;

	public TreeSetStore() {
		super();
		this.trailName = new TreeSet<Trail>();
	}
	
	public void addTrail(Trail trail) {
		this.trailName.add(trail);
	}
	
	public List<Trail> search(Predicate<Trail> predicate) {
		return trailName.stream().filter(predicate).collect(Collectors.toList());
	}
	
	public boolean delete(Predicate<Trail> predicate){
		return trailName.removeIf(predicate);
	}
	
	
}
