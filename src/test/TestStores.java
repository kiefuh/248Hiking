package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Difficulty;
import model.Trail;
import model.TrailType;
import model.UserProfile;
import store.TreeMapStore;
import store.UserStore;

public class TestStores {

	@Test
	public void testUserSearch() {
		UserProfile test= new UserProfile("John","Doe",null,null,false,null);
		UserStore store= new UserStore();
		store.addUser(test);
		assertEquals(test,store.searchBag(test.getUserName()));
	}
	
	@Test
	public void testUserDeletion() {
		UserProfile test= new UserProfile("John","Doe",null,null,false,null);
		UserStore store= new UserStore();
		store.addUser(test);
		assertEquals(test,store.removeUser(test.getUserName()));
	}
	
	@Test
	public void testTrailSearch() {
		TreeMapStore trailStore = new TreeMapStore();
		Trail trail = new Trail("Test","Test",20,20,Difficulty.EASY,TrailType.LOOP);
		trailStore.addTrail(trail);
		assertEquals(trail,trailStore.deleteByID(trail));
	}
	

}
