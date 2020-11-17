package model;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class UserProfile {
	private String userName;
	private String password;
	private BufferedImage profilePicture;
	private LinkedList<HikingHistory> hikingHistory;
	private boolean isAdmin;
	public UserProfile(String userName, String password, BufferedImage profilePicture,
			LinkedList<HikingHistory> hikingHistory,boolean isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.profilePicture = profilePicture;
		this.hikingHistory = hikingHistory;
		this.isAdmin=isAdmin;
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
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin=isAdmin;
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	@Override
	public String toString() {
		return "UserProfile [userName=" + userName + ", password=" + password + ", profilePicture=" + profilePicture
				+ ", hikingHistory=" + hikingHistory + "]";
	}
	
	
	
}
