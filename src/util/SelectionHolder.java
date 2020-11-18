package util;

import model.HikingHistory;
import model.UserProfile;

public class SelectionHolder {
	private static HikingHistory selected;
	private static UserProfile selectedUser;
	
	public static void setSelected(HikingHistory select) {
		selected=select;
	}
	
	public static HikingHistory getSelected() {
		return selected;
	}
	
	public static void setSelectedUserProfile(UserProfile select) {
		selectedUser=select;
	}
	
	public static UserProfile getSelectedUserProfile() {
		return selectedUser;
	}
}
