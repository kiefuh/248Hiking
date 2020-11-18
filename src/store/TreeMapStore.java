package store;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Difficulty;
import model.Trail;
import model.TrailType;

public class TreeMapStore implements Serializable {
	/**
	 * @author Kiefer
	 * This class contains all the trails stored in the program. It allows for searching and deletion of trails.
	 */
	private static final long serialVersionUID = 3650119099714494093L;
	LinkedList<Trail> trailNameList;
	TreeMap<Integer, LinkedList<Trail>> trailLengthMap;
	TreeMap<Integer, LinkedList<Trail>> trailElevationMap;
	HashMap<Difficulty, LinkedList<Trail>> trailDifficultyMap;
	HashMap<TrailType, LinkedList<Trail>> trailTypeMap;
	TreeMap<Integer,Trail> trailIdMap;

	public TreeMapStore() {
		trailNameList = new LinkedList<Trail>();
		trailLengthMap = new TreeMap<>();
		trailElevationMap = new TreeMap<>();
		trailDifficultyMap = new HashMap<>(20);
		trailTypeMap = new HashMap<>(20);
		trailIdMap= new TreeMap<>();
	}
	
	/**
	 * This method adds a trail to the trail store. It will add two trails that are the same.
	 * It adds it too each data structure which all contain a LinkedList at a key.
	 * @param Trail trail
	 */
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
		trailIdMap.put(trail.getId(), trail);

	}
	/**
	 * This method searches through the LinkedList that contains all the names of trails to return any trail 
	 * with the searchPhrase in it. For example if you searched tree, both a trail named tree woods and tree place would be returned.
	 * @param searchPhrase
	 * @return List<Trail>
	 */
	public List<Trail> searchByName(String searchPhrase) {
		return trailNameList.stream().filter(x -> x.getTrailName().contains(searchPhrase)).collect(Collectors.toList());
	}
	/**
	 * This method returns the LinkedList stored at the searchPhrase one inputs.
	 * @param searchPhrase
	 * @return
	 */
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
	/**
	 * This method is used in the gui to delete a selected trail.
	 * It uses all of the above removal methods to remove from all the TreeMaps, HashMaps, and LinkedList.
	 * @param searchPhrase is a trail 
	 * @return Trail trail
	 */
	public Trail deleteByID(Trail searchPhrase){
		Trail t=trailIdMap.remove(searchPhrase.getId());
		List<Trail>removalList=trailNameList.stream().filter(x -> x.getId().equals(searchPhrase.getId())).collect(Collectors.toList());
		trailNameList.removeAll(removalList);
		LinkedList<Trail> lengthRemove = searchByLength(searchPhrase.getLength());
		lengthRemove.removeIf(x->x.getId().equals(searchPhrase.getId()));
		LinkedList<Trail> elevationRemove = searchByElevation(searchPhrase.getElevationGain());
		elevationRemove.removeIf(x->x.getId().equals(searchPhrase.getId()));
		LinkedList<Trail> trailDifficulty= searchByDifficulty(searchPhrase.getDifficulty());
		trailDifficulty.removeIf(x->x.getId().equals(searchPhrase.getId()));
		LinkedList<Trail> trailTypeRemove= searchByTrailType(searchPhrase.getTrailType());
		trailTypeRemove.removeIf(x->x.getId().equals(searchPhrase.getId()));
		return t;
	}
	
	
	

}
