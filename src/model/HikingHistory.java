package model;


import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class HikingHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -787446088339909333L;
	String trailName;
	LocalDateTime dateTime=LocalDateTime.now();
	Integer distance;
	Integer duration;
	LinkedList<String> picturesTaken;
	Integer numberOfPictures;
	double averagePace;
	
	public HikingHistory(String trailName, int distance, Integer duration,
			LinkedList<String> picturesTaken) {
		super();
		this.trailName = trailName;
		this.distance = distance;
		this.duration = duration;
		this.picturesTaken = picturesTaken;
		this.averagePace = ((double)distance)/((double)duration);
		this.numberOfPictures=this.picturesTaken.size();
	}
	public String getTrailName() {
		return trailName;
	}
	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public LinkedList<String> getPicturesTaken() {
		return picturesTaken;
	}
	public void setPicturesTaken(LinkedList<String> picturesTaken) {
		this.picturesTaken = picturesTaken;
	}
	public double getAveragePace() {
		return averagePace;
	}
	public void setAveragePace() {
		this.averagePace = ((double)distance)/((double)duration);
	}
	
	public void addPicture(String img) {
		picturesTaken.add(img);
		numberOfPictures++;
	}
	
	public void setNumberOfPictures(int number) {
		this.numberOfPictures=number;
	}
	
	public Integer getNumberOfPictures() {
		return this.numberOfPictures;
	}
	@Override
	public String toString() {
		return "HikingHistory [trailName=" + trailName + ", dateTime=" + dateTime + ", distance=" + distance
				+ ", duration=" + duration + ", picturesTaken=" + picturesTaken + ", averagePace=" + averagePace + "]";
	}
	
	
}
