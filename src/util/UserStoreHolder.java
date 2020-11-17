package util;

import model.UserProfile;
import store.UserStore;

public class UserStoreHolder {
private static UserStore userStore;
	
	
	public static void setUserStore(UserStore user) {
		userStore=user;
	}
	
	public static UserStore getUserStore() {
		return userStore;
	}
}
