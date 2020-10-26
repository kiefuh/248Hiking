package model;

public enum TrailType {
	LOOP("LOOP"),OUTANDBACK("OUT AND BACK"),POINTTOPOINT("POINT-TO-POINT");
	
	private String type;
	
	private TrailType(String t) {
		type=t;
	}
	
	public String getTrailType() {
		return type;
	}
	
	
}
