package util;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import model.Difficulty;
import model.HikingHistory;
import model.Trail;
import model.TrailType;
import model.UserProfile;
import store.TreeMapStore;
import store.TreeSetStore;
import store.UserStore;

public class Factory {
	
	public static void userFactory(UserStore userBag, int numberOfUsers) {
		String userName;
		String password;
		BufferedImage profilePic;
		LinkedList<HikingHistory> historyStack= new LinkedList<HikingHistory>();
		for(int i=0;i<numberOfUsers;i++) {
			userName= stringGenerator();
			password= stringGenerator();
			profilePic=null;
			historyStack= new LinkedList<HikingHistory>();
			for(int j=0;j<3;j++) {
				String trailName= stringGenerator();
				Integer distance= integerGenerator();
				Integer duration= integerGenerator();
				LinkedList<BufferedImage> picList=new LinkedList<>();
				double averagePace= integerGenerator();
				HikingHistory history= new HikingHistory(trailName,distance,duration,picList);
				historyStack.add(history);
			}
		
			UserProfile user= new UserProfile(userName,password,profilePic,historyStack,false);
			userBag.addUser(user);
		}
		
	}
	
	public static void trailFactory(TreeMapStore trailBag, int numberOfTrails) {
		String trailName;
		String trailHeadAdresses;
		Integer length;
		Integer elevationGain;
		Difficulty difficulty;
		TrailType trailType;
		
		for(int i=0;i<numberOfTrails;i++) {
			trailName= stringGenerator();
			trailHeadAdresses= stringGenerator();
			length= integerGenerator();
			elevationGain= integerGenerator();
			difficulty= randomDifficulty();
			trailType= randomType();
			Trail trail= new Trail(trailName,trailHeadAdresses,length,elevationGain,difficulty,trailType);
			trailBag.addTrail(trail);
			
		}
	}
	
	public static void trailFactory2(TreeSetStore trailBag2, int numberOfTrails) {
		String trailName;
		String trailHeadAdresses;
		Integer length;
		Integer elevationGain;
		Difficulty difficulty;
		TrailType trailType;
		
		for(int i=0;i<numberOfTrails;i++) {
			trailName= stringGenerator();
			trailHeadAdresses= stringGenerator();
			length= integerGenerator();
			elevationGain= integerGenerator();
			difficulty= randomDifficulty();
			trailType= randomType();
			Trail trail= new Trail(trailName,trailHeadAdresses,length,elevationGain,difficulty,trailType);
			trailBag2.addTrail(trail);
			
		}
	}
	
	private static String stringGenerator() {
		 String startingChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder randomString = new StringBuilder();
	        Random rnd = new Random();
	        while (randomString.length() < 2) { 
	            int index = (int) (rnd.nextFloat() * startingChar.length());
	            randomString.append(startingChar.charAt(index));
	        }
	        randomString.append(" ");
	      for(int i=0;i<2;i++) {
	            int index = (int) (rnd.nextFloat() * startingChar.length());
	            randomString.append(startingChar.charAt(index));
	        }
	        randomString.append(" ");
	        for(int j=0;j<2;j++) {
	            int index = (int) (rnd.nextFloat() * startingChar.length());
	            randomString.append(startingChar.charAt(index));
	        }
	        String saltStr = randomString.toString();
	        return saltStr;
	}
	
	private static Integer integerGenerator() {
		return (int)(Math.random()*100);
	}
	
	private static Difficulty randomDifficulty() {
		int randomNumber=(int) (Math.random()*3);
		if (randomNumber==0) {
			return Difficulty.EASY;
		}
		if(randomNumber==1) {
			return Difficulty.MODERATE;
		}
		if(randomNumber==2) {
			return Difficulty.HARD;
		}
		else {
			return null;
		}
	}
	
	private static TrailType randomType() {
		int randomNumber=(int) (Math.random()*3);
		if (randomNumber==0) {
			return TrailType.LOOP;
		}
		if(randomNumber==1) {
			return TrailType.OUTANDBACK;
		}
		if(randomNumber==2) {
			return TrailType.POINTTOPOINT;
		}
		else {
			return null;
		}
	}
}
