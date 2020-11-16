package util;

import store.TreeMapStore;

public class TrailStoreHolder {
	private static TreeMapStore trailStore;
	
	public static void setTrailStore(TreeMapStore trails) {
		trailStore=trails;
	}
	
	public static TreeMapStore getTrailStore() {
		return trailStore;
	}
}
