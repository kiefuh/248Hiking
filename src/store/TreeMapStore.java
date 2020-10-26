package store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Difficulty;
import model.Trail;
import model.TrailType;

public class TreeMapStore {
	LinkedList<Trail> trailNameList;
	TreeMap<Integer, LinkedList<Trail>> trailLengthMap;
	TreeMap<Integer, LinkedList<Trail>> trailElevationMap;
	HashMap<Difficulty, LinkedList<Trail>> trailDifficultyMap;
	HashMap<TrailType, LinkedList<Trail>> trailTypeMap;

	public TreeMapStore() {
		trailNameList = new LinkedList<Trail>();
		trailLengthMap = new TreeMap<>();
		trailElevationMap = new TreeMap<>();
		trailDifficultyMap = new HashMap<>(20);
		trailTypeMap = new HashMap<>(20);
	}

	public void addTrail(Trail trail) {
		trailNameList.add(trail);
		if (trailLengthMap.containsKey(trail.getLength())) {
			trailLengthMap.get(trail.getLength()).add(trail);
		} else {
			LinkedList<Trail> trailList = new LinkedList<>();
			trailList.add(trail);
			trailLengthMap.put(trail.getLength(), trailList);
		}
		if (trailElevationMap.containsKey(trail.getElevationGain())) {
			trailElevationMap.get(trail.getElevationGain()).add(trail);

		} else {
			LinkedList<Trail> trailList = new LinkedList<>();
			trailList.add(trail);
			trailElevationMap.put(trail.getElevationGain(), trailList);
		}
		if (trailDifficultyMap.containsKey(trail.getDifficulty())) {
			trailDifficultyMap.get(trail.getDifficulty()).add(trail);
		} else {
			LinkedList<Trail> trailList = new LinkedList<>();
			trailList.add(trail);
			trailDifficultyMap.put(trail.getDifficulty(), trailList);
		}
		if (trailTypeMap.containsKey(trail.getTrailType())) {
			trailTypeMap.get(trail.getTrailType()).add(trail);

		} else {
			LinkedList<Trail> trailList = new LinkedList<>();
			trailList.add(trail);
			trailTypeMap.put(trail.getTrailType(), trailList);
		}

	}

	public List<Trail> searchByName(String searchPhrase) {
		return trailNameList.stream().filter(x -> x.getTrailName().contains(searchPhrase)).collect(Collectors.toList());
	}

	public LinkedList<Trail> searchByLength(Integer searchPhrase) {
		return trailLengthMap.get(searchPhrase);
	}

	public LinkedList<Trail> searchByElevation(Integer searchPhrase) {
		return trailElevationMap.get(searchPhrase);
	}

	public LinkedList<Trail> searchByDifficulty(Difficulty searchPhrase) {
		return trailDifficultyMap.get(searchPhrase);
	}

	public LinkedList<Trail> searchByTrailType(TrailType searchPhrase) {
		return trailTypeMap.get(searchPhrase);
	}
	
	public List<Trail> deleteByName(String searchPhrase){
		List<Trail> removalList=trailNameList.stream().filter(x -> x.getTrailName().contains(searchPhrase)).collect(Collectors.toList());
		trailNameList.removeAll(removalList);
		if(removalList!=null) {
		for(int i=0;i<removalList.size();i++) {
			Trail t=removalList.get(i);
			LinkedList<Trail> lengthRemove = searchByLength(t.getLength());
			lengthRemove.removeIf(x->x.getTrailName().equals(t.getTrailName()));
			LinkedList<Trail> elevationRemove = searchByElevation(t.getElevationGain());
			elevationRemove.removeIf(x->x.getTrailName().equals(t.getTrailName()));
			LinkedList<Trail> difficultyRemove = searchByDifficulty(t.getDifficulty());
			difficultyRemove.removeIf(x->x.getTrailName().equalsIgnoreCase(t.getTrailName()));
			LinkedList<Trail> trailTypeRemove= searchByTrailType(t.getTrailType());
			trailTypeRemove.removeIf(x->x.getTrailName().equalsIgnoreCase(t.getTrailName()));
		}
		return removalList;
		}
		else {
			return removalList;
		}
	}
	
	public LinkedList<Trail> deleteByLength(Integer searchPhrase){
		LinkedList<Trail> removalList=trailLengthMap.remove(searchPhrase);
		if(removalList!=null) {
		for(int i=0;i<removalList.size();i++) {
			Trail t=removalList.get(i);
			List<Trail> nameRemove = searchByName(t.getTrailName());
			nameRemove.removeIf(x->x.getLength().equals(t.getLength()));
			LinkedList<Trail> elevationRemove = searchByElevation(t.getElevationGain());
			elevationRemove.removeIf(x->x.getLength().equals(t.getLength()));
			LinkedList<Trail> difficultyRemove = searchByDifficulty(t.getDifficulty());
			difficultyRemove.removeIf(x->x.getLength().equals(t.getLength()));
			LinkedList<Trail> trailTypeRemove= searchByTrailType(t.getTrailType());
			trailTypeRemove.removeIf(x->x.getLength().equals(t.getLength()));
		}
		return removalList;
		}
		else {
			return removalList;
		}
	}
	
	public LinkedList<Trail> deleteByElevation(Integer searchPhrase){
		LinkedList<Trail> removalList=trailElevationMap.remove(searchPhrase);
		if(removalList!=null) {
		for(int i=0;i<removalList.size();i++) {
			Trail t=removalList.get(i);
			List<Trail> nameRemove = searchByName(t.getTrailName());
			nameRemove.removeIf(x->x.getElevationGain().equals(t.getElevationGain()));
			LinkedList<Trail> lengthRemove = searchByLength(t.getLength());
			lengthRemove.removeIf(x->x.getElevationGain().equals(t.getElevationGain()));
			LinkedList<Trail> difficultyRemove = searchByDifficulty(t.getDifficulty());
			difficultyRemove.removeIf(x->x.getElevationGain().equals(t.getElevationGain()));
			LinkedList<Trail> trailTypeRemove= searchByTrailType(t.getTrailType());
			trailTypeRemove.removeIf(x->x.getElevationGain().equals(t.getElevationGain()));
		}
		return removalList;
		}
		else {
			return removalList;
		}
	}
	
	public LinkedList<Trail> deleteByDifficulty(Difficulty searchPhrase){
		LinkedList<Trail> removalList=trailDifficultyMap.remove(searchPhrase);
		if(removalList!=null) {
		for(int i=0;i<removalList.size();i++) {
			Trail t=removalList.get(i);
			List<Trail> nameRemove = searchByName(t.getTrailName());
			nameRemove.removeIf(x->x.getDifficulty().equals(t.getDifficulty()));
			LinkedList<Trail> lengthRemove = searchByLength(t.getLength());
			lengthRemove.removeIf(x->x.getDifficulty().equals(t.getDifficulty()));
			LinkedList<Trail> elevationRemove = searchByElevation(t.getElevationGain());
			elevationRemove.removeIf(x->x.getDifficulty().equals(t.getDifficulty()));
			LinkedList<Trail> trailTypeRemove= searchByTrailType(t.getTrailType());
			trailTypeRemove.removeIf(x->x.getDifficulty().equals(t.getDifficulty()));
		}
		return removalList;
		}
		else {
			return removalList;
		}
	}
	
	public LinkedList<Trail> deleteByTrailType(TrailType searchPhrase){
		LinkedList<Trail> removalList=trailTypeMap.remove(searchPhrase);
		if(removalList!=null) {
		for(int i=0;i<removalList.size();i++) {
			Trail t=removalList.get(i);
			List<Trail> nameRemove = searchByName(t.getTrailName());
			nameRemove.removeIf(x->x.getTrailType().equals(t.getTrailType()));
			LinkedList<Trail> lengthRemove = searchByLength(t.getLength());
			lengthRemove.removeIf(x->x.getTrailType().equals(t.getTrailType()));
			LinkedList<Trail> elevationRemove = searchByElevation(t.getElevationGain());
			elevationRemove.removeIf(x->x.getTrailType().equals(t.getTrailType()));
			LinkedList<Trail> trailDifficulty= searchByDifficulty(t.getDifficulty());
			trailDifficulty.removeIf(x->x.getTrailType().equals(t.getTrailType()));
		}
		return removalList;
		}
		else {
			return removalList;
		}
	}
	
	
	

}
