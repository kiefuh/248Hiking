package util;

import model.HikingHistory;

public class SelectionHolder {
	private static HikingHistory selected;
	
	public static void setSelected(HikingHistory select) {
		selected=select;
	}
	
	public static HikingHistory getSelected() {
		return selected;
	}
}
