package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import store.UserStore;

public class TimeTestUser {
	LinkedList<Long> searchTime= new LinkedList<>();
	LinkedList<Long> insertTime= new LinkedList<>();
	LinkedList<Long> removeTime= new LinkedList<>();
	UserStore store;
	public TimeTestUser(UserStore store) {
		this.store=store;
	}
	
	public void getTime() {
		StringBuilder sb= new StringBuilder();
		StringBuilder sb2= new StringBuilder();
		StringBuilder sb3= new StringBuilder();
		sb.append("Insertion\t\t\t\t\t\t\t\t\tTime\n");
		sb.append("___________________________________________________\n");
		sb2.append("Search\t\t\t\t\t\t\t\t\tTime\n");
		sb2.append("___________________________________________________\n");
		long insertTimer=0;
		long searchTimer=0;
		long deleteTimer=0;
		for(int i=0;i<=11;i++) {
			int trailNumber=i*500;
			int cycles=trailNumber/10;
			for(int j=0;j<cycles;j++) {
			insertTimer+=timeInsert(trailNumber);
			searchTimer+=timeSearch();
			deleteTimer+=timeRemove();
			}
			if(i>1) {
			insertTime.add(insertTimer/cycles);
			searchTime.add(searchTimer/cycles);
			removeTime.add(deleteTimer/cycles);
			sb.append("Insertion Time "+trailNumber+" trails: "+"\t"+insertTimer/cycles+"\n");
			sb2.append("Search Time "+trailNumber+" trails: "+"\t"+searchTimer/cycles+"\n");
			sb3.append("Removal Time "+trailNumber+" trails: "+"\t"+deleteTimer/cycles+"\n");
			insertTimer=0;
			searchTimer=0;
			deleteTimer=0;
			}
			else {
				insertTimer=0;
				searchTimer=0;
				deleteTimer=0;
			}
		}
		
		exportResults("UserInsertionTimeResults.txt",sb);
		exportResults("UserSearchTimeResults.txt",sb2);
		exportResults("UserRemovalTimeResults.txt",sb3);
	}
	
	private long timeSearch() {
		Timer.startTimer();
		store.searchBag(stringGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long timeInsert(int numberOfUsers) {
		store= new UserStore();
		Timer.startTimer();
		Factory.userFactory(store, numberOfUsers);
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long timeRemove() {
		Timer.startTimer();
		store.removeUser(stringGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
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
	
	private void exportResults(String location, StringBuilder sb) {
		FileWriter fw;
		try {
			fw= new FileWriter(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fw=null;
		}
		PrintWriter pw;
		pw= new PrintWriter(fw);
		String result= sb.toString();
		pw.println(result);
		pw.close();
	}
	
	public LineChart generateInsertionGraph() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for insertion in User TreeMap");
		XYChart.Series seriesTreeMapInsert= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(insertTime.isEmpty()!=true) {
			seriesTreeMapInsert.getData().add(new XYChart.Data(numberOfTrails,insertTime.pop()));
			numberOfTrails+=500;
		}
		
		seriesTreeMapInsert.setName("UserStore Insertion");
		
		
		lineChart.getData().addAll(seriesTreeMapInsert);

		
		return lineChart;
	}
	
	public LineChart generateRemovalGraph() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for Removal in User TreeMap");
		XYChart.Series seriesTreeMapRemove= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(removeTime.isEmpty()!=true) {
			seriesTreeMapRemove.getData().add(new XYChart.Data(numberOfTrails,removeTime.pop()));
			numberOfTrails+=500;
		}
		
		seriesTreeMapRemove.setName("UserStore Removal");
		
		
		lineChart.getData().addAll(seriesTreeMapRemove);

		
		return lineChart;
	}
	
	public LineChart generateSearchGraph() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for Searching in User TreeMap");
		XYChart.Series seriesTreeMapSearch= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(searchTime.isEmpty()!=true) {
			seriesTreeMapSearch.getData().add(new XYChart.Data(numberOfTrails,searchTime.pop()));
			numberOfTrails+=500;
		}
		
		seriesTreeMapSearch.setName("UserStore Search");
		
		
		lineChart.getData().addAll(seriesTreeMapSearch);

		
		return lineChart;
	}

}
