	package model;

public enum Difficulty {
	EASY("EASY"), MODERATE("MODERATE"),HARD("HARD");
	
	private String level;
	
	private Difficulty(String dif) {
		level=dif;
	}
	
	public String getDifficulty() {
		return level;
	}
}
