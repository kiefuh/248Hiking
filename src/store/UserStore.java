package store;

import java.util.TreeMap;

import model.UserProfile;

public class UserStore {
	TreeMap<String,UserProfile> users;

	public UserStore() {
		super();
		this.users = new TreeMap<String,UserProfile>();
	}
	
	public void addUser(UserProfile user) {
		this.users.put(user.getUserName(), user);
	}
	
	public UserProfile searchBag(String searchPhrase) {
		return users.get(searchPhrase);
	}
	
	public UserProfile removeUser(String searchPhrase) {
		return users.remove(searchPhrase);
	}
	
	
}
