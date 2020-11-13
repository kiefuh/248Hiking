package util;

import model.UserProfile;

public class UserHolder {
	private static UserProfile currentUser;
	
	
	public static void setUser(UserProfile user) {
		currentUser=user;
	}
	
	public static UserProfile getUser() {
		return currentUser;
	}
}
