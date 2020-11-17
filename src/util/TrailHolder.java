package util;

import model.Trail;

public class TrailHolder {
	private static Trail selectedTrail;
	
	public static void setSelectedTrail(Trail selection) {
		selectedTrail=selection;
	}
	
	public static Trail setSelectedTrail() {
		return selectedTrail;
	}
	
	public static void clearSelected() {
		selectedTrail=null;
	}
}
