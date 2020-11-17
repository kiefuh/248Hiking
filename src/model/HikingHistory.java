package model;


import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class HikingHistory {
	String trailName;
	LocalDateTime dateTime=LocalDateTime.now();
	Integer distance;
	Integer duration;
	LinkedList<BufferedImage> picturesTaken;
	double averagePace;
	public HikingHistory(String trailName, int distance, Integer duration,
			LinkedList<BufferedImage> picturesTaken) {
		super();
		this.trailName = trailName;
		this.distance = distance;
		this.duration = duration;
		this.picturesTaken = picturesTaken;
		this.averagePace = ((double)distance)/((double)duration);
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
	public LinkedList<BufferedImage> getPicturesTaken() {
		return picturesTaken;
	}
	public void setPicturesTaken(LinkedList<BufferedImage> picturesTaken) {
		this.picturesTaken = picturesTaken;
	}
	public double getAveragePace() {
		return averagePace;
	}
	public void setAveragePace(double averagePace) {
		this.averagePace = averagePace;
	}
	
	public void addPicture(BufferedImage img) {
		picturesTaken.add(img);
	}
	@Override
	public String toString() {
		return "HikingHistory [trailName=" + trailName + ", dateTime=" + dateTime + ", distance=" + distance
				+ ", duration=" + duration + ", picturesTaken=" + picturesTaken + ", averagePace=" + averagePace + "]";
	}
	
	
}
