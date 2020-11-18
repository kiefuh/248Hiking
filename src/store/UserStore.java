package store;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.UserProfile;

public class UserStore implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7560669577514198315L;
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
	
	public List<UserProfile> searchUsersGui(String searchPhrase){
		return users.values().stream().filter(x -> x.getUserName().contains(searchPhrase)).collect(Collectors.toList());
	}
	
	
}
