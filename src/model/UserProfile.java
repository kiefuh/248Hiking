package model;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Stack;

public class UserProfile {
	private String userName;
	private String password;
	private BufferedImage profilePicture;
	private LinkedList<HikingHistory> hikingHistory;
	public UserProfile(String userName, String password, BufferedImage profilePicture,
			LinkedList<HikingHistory> hikingHistory) {
		super();
		this.userName = userName;
		this.password = password;
		this.profilePicture = profilePicture;
		this.hikingHistory = hikingHistory;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BufferedImage getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(BufferedImage profilePicture) {
		this.profilePicture = profilePicture;
	}
	public LinkedList<HikingHistory> getHikingHistory() {
		return hikingHistory;
	}
	public void setHikingHistory(LinkedList<HikingHistory> hikingHistory) {
		this.hikingHistory = hikingHistory;
	}
	
	public void addTrailHistory(HikingHistory trail) {
		hikingHistory.add(trail);
	}
	
	@Override
	public String toString() {
		return "UserProfile [userName=" + userName + ", password=" + password + ", profilePicture=" + profilePicture
				+ ", hikingHistory=" + hikingHistory + "]";
	}
	
	
	
}
