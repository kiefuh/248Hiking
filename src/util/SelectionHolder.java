package util;

import model.HikingHistory;
import model.Trail;
import model.UserProfile;

public class SelectionHolder {
	private static HikingHistory selected;
	private static UserProfile selectedUser;
	private static Trail selectedTrail;
	
	public static Trail getSelectedTrail() {
		return selectedTrail;
	}

	public static void setSelectedTrail(Trail selectedTrail) {
		SelectionHolder.selectedTrail = selectedTrail;
	}

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
