package model;

import java.io.Serializable;

public class Trail implements Comparable<Trail>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7891441421017545749L;
	String trailName;
	String trailHeadAdresses;
	Integer length;
	Integer elevationGain;
	Difficulty difficulty;
	TrailType trailType;
	private static Integer  iDCounter=0;
	private Integer iD;
	
	
	
	public Trail() {
		super();
	}
	public Trail(String trailName, String trailHeadAdresses, Integer length, Integer elevationGain, Difficulty difficulty,
			TrailType trailType) {
		super();
		this.trailName = trailName;
		this.trailHeadAdresses = trailHeadAdresses;
		this.length = length;
		this.elevationGain = elevationGain;
		this.difficulty = difficulty;
		this.trailType = trailType;
		iD=iDCounter++;
	}
	public String getTrailName() {
		return trailName;
	}
	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}
	public String getTrailHeadAdresses() {
		return trailHeadAdresses;
	}
	public void setTrailHeadAdresses(String trailHeadAdresses) {
		this.trailHeadAdresses = trailHeadAdresses;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Integer getElevationGain() {
		return elevationGain;
	}
	public void setElevationGain(int elevationGain) {
		this.elevationGain = elevationGain;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public TrailType getTrailType() {
		return trailType;
	}
	public void setTrailType(TrailType trailType) {
		this.trailType = trailType;
	}
	public Integer getId() {
		return this.iD;
	}
	@Override
	public String toString() {
		return "Trail [trailName=" + trailName + ", trailHeadAdresses=" + trailHeadAdresses + ", length=" + length
				+ ", elevationGain=" + elevationGain + ", difficulty=" + difficulty + ", trailType=" + trailType + "]";
	}
	@Override
	public int compareTo(Trail o) {
		return this.trailName.compareTo(o.getTrailName());
	}
	
	
	

}
