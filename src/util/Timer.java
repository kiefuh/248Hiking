package util;

public class Timer {
	private static long startTime;
	private static long endTime;

	public static void startTimer() {
		startTime=System.nanoTime();
	}
	
	public static void endTimer() {
		endTime=System.nanoTime()-startTime;
	}
	
	public static long getEndTime() {
		return endTime;
	}
	public static void displayTimer(String objectTimed) {
		System.out.println(objectTimed+": "+endTime);
	}
}
