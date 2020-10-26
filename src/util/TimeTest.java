package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Predicate;

import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Difficulty;
import model.Trail;
import model.TrailType;
import store.TreeMapStore;
import store.TreeSetStore;

public class TimeTest {
	private LinkedList<Long> difficultyTimeMapList= new LinkedList<Long>(); 
	private LinkedList<Long> elevationTimeMapList= new LinkedList<Long>(); 
	private LinkedList<Long> distanceTimeMapList= new LinkedList<Long>(); 
	private LinkedList<Long> typeTimeMapList= new LinkedList<Long>(); 
	private LinkedList<Long> nameTimeMapList= new LinkedList<Long>(); 
	private LinkedList<Long> difficultyTimeSetList= new LinkedList<Long>(); 
	private LinkedList<Long> elevationTimeSetList= new LinkedList<Long>(); 
	private LinkedList<Long> distanceTimeSetList= new LinkedList<Long>(); 
	private LinkedList<Long> typeTimeSetList= new LinkedList<Long>(); 
	private LinkedList<Long> nameTimeSetList= new LinkedList<Long>();
	private LinkedList<Long> insertionMapListTimes= new LinkedList<Long>();
	private LinkedList<Long> insertionSetListTimes= new LinkedList<Long>();


	
	
	public TimeTest() {
		super();
	}



	public void testSearchTime(TreeSetStore tSS, TreeMapStore tMS) {
		StringBuilder sb= new StringBuilder();
		StringBuilder sb2= new StringBuilder();
		sb.append("Search\t\t\t\t\t\t\t\t\tTime\n");
		sb.append("___________________________________________________\n");
		sb2.append("Insert\t\t\t\t\t\t\t\t\tTime\n");
		sb2.append("___________________________________________________\n");
		int trailNumber = 0;
		long insertionTimeMap = 0;
		long insertionTimeSet = 0;
		long difficultyTimeMap = 0;
		long elevationTimeMap = 0;
		long distanceTimeMap = 0;
		long typeTimeMap = 0;
		long nameTimeMap = 0;
		long difficultyTimeSet = 0;
		long elevationTimeSet = 0;
		long distanceTimeSet = 0;
		long typeTimeSet = 0;
		long nameTimeSet = 0;
		for (int i=0;i<=11;i++) {
			trailNumber=i*500;
			int numberOfCycles=trailNumber/10;
			for(int j=0;j<=numberOfCycles;j++) {
				insertionTimeMap+=insertionTestMapsTime(tMS,trailNumber);
				insertionTimeSet+=insertionTestSetTime(tSS,trailNumber);
				difficultyTimeMap+=testDifficultyTime(tMS);
				elevationTimeMap+=testElevationTime(tMS);
				distanceTimeMap+=testDistanceTime(tMS);
				typeTimeMap+=testTypeTime(tMS);
				nameTimeMap+=testNameTime(tMS);
				difficultyTimeSet+= testTreeSearchTime(x->x.getDifficulty().equals(randomDifficulty()),tSS);
				elevationTimeSet+= testTreeSearchTime(x->x.getElevationGain().equals(integerGenerator()),tSS);
				distanceTimeSet+= testTreeSearchTime(x->x.getLength().equals(integerGenerator()),tSS);
				typeTimeSet+= testTreeSearchTime(x->x.getTrailType().equals(randomType()),tSS);
				nameTimeSet+= testTreeSearchTime(x->x.getTrailName().contains(stringGenerator()),tSS);
			}
			if(i>1) {
			difficultyTimeMapList.add(difficultyTimeMap/numberOfCycles);
			elevationTimeMapList.add(elevationTimeMap/numberOfCycles);
			distanceTimeMapList.add(distanceTimeMap/numberOfCycles);
			typeTimeMapList.add(typeTimeMap/numberOfCycles);
			nameTimeMapList.add(nameTimeMap/numberOfCycles);
			difficultyTimeSetList.add(difficultyTimeSet/numberOfCycles);
			elevationTimeSetList.add(elevationTimeSet/numberOfCycles);
			distanceTimeSetList.add(distanceTimeSet/numberOfCycles);
			typeTimeSetList.add(typeTimeSet/numberOfCycles);
			nameTimeSetList.add(nameTimeSet/numberOfCycles);
			insertionMapListTimes.add(insertionTimeMap/numberOfCycles);
			insertionSetListTimes.add(insertionTimeSet/numberOfCycles);
			sb.append("difficultySearchHashMap "+trailNumber+" trails: "+"\t"+difficultyTimeMap/numberOfCycles+"\n");
			sb.append("elevationSearchTreeMap "+trailNumber+" trails: "+"\t"+elevationTimeMap/numberOfCycles+"\n");
			sb.append("distanceSearchTreeMap "+trailNumber+" trails: "+"\t"+distanceTimeMap/numberOfCycles+"\n");
			sb.append("typeSearchHashMap "+trailNumber+" trails: "+"\t\t"+typeTimeMap/numberOfCycles+"\n");
			sb.append("nameSearchLinkedList "+trailNumber+" trails: "+"\t\t"+nameTimeMap/numberOfCycles+"\n");
			sb.append("difficultySearchTreeSet "+trailNumber+" trails: "+"\t"+difficultyTimeSet/numberOfCycles+"\n");
			sb.append("elevationSearchTreeSet "+trailNumber+" trails: "+"\t"+elevationTimeSet/numberOfCycles+"\n");
			sb.append("distanceSearchTreeSet "+trailNumber+" trails: "+"\t"+distanceTimeSet/numberOfCycles+"\n");
			sb.append("typeSearchTreeSet "+trailNumber+" trails: "+"\t\t"+typeTimeSet/numberOfCycles+"\n");
			sb.append("nameSearchTreeSet "+trailNumber+" trails: "+"\t\t"+nameTimeSet/numberOfCycles+"\n");
			sb.append("___________________________________________________\n");
			sb2.append("insertionTreeMapStore "+trailNumber+" trails: "+"\t"+insertionTimeMap/numberOfCycles+"\n");
			sb2.append("insertionTreeSetStore "+trailNumber+" trails: "+"\t"+insertionTimeSet/numberOfCycles+"\n");
			sb2.append("___________________________________________________\n");
			insertionTimeMap = 0;
			insertionTimeSet = 0;
			difficultyTimeMap = 0;
			elevationTimeMap = 0;
			distanceTimeMap = 0;
			typeTimeMap = 0;
			nameTimeMap = 0;
			difficultyTimeSet = 0;
			elevationTimeSet = 0;
			distanceTimeSet = 0;
			typeTimeSet = 0;
			nameTimeSet = 0;
			}
			else {
			insertionTimeMap = 0;
			insertionTimeSet = 0;
			difficultyTimeMap = 0;
			elevationTimeMap = 0;
			distanceTimeMap = 0;
			typeTimeMap = 0;
			nameTimeMap = 0;
			difficultyTimeSet = 0;
			elevationTimeSet = 0;
			distanceTimeSet = 0;
			typeTimeSet = 0;
			nameTimeSet = 0;
			}
		}
		exportResults("searchTimeResults.txt",sb);
		exportResults("InsertionTimeResults.txt",sb2);
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
	
	private long insertionTestMapsTime(TreeMapStore tMS,int trailNumber) {
		tMS= new TreeMapStore();
		Timer.startTimer();
		Factory.trailFactory(tMS, trailNumber);
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long insertionTestSetTime(TreeSetStore tSS,int trailNumber) {
		tSS= new TreeSetStore();
		Timer.startTimer();
		Factory.trailFactory2(tSS, trailNumber);
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testDifficultyTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.searchByDifficulty(randomDifficulty());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testElevationTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.searchByElevation(integerGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testDistanceTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.searchByLength(integerGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testTypeTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.searchByTrailType(randomType());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testNameTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.searchByName(stringGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testTreeSearchTime(Predicate<Trail> p,TreeSetStore tSS) {
		Timer.startTimer();
		tSS.search(p);
		Timer.endTimer();
		return Timer.getEndTime();
		
	}
	
	private long testTreeDeleteTime(Predicate<Trail> p,TreeSetStore tSS) {
		Timer.startTimer();
		tSS.delete(p);
		Timer.endTimer();
		return Timer.getEndTime();
		
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
		lineChart.setTitle("Time for insertion TreeMapStore vs TreeSetStore");
		XYChart.Series seriesTreeMapInsert= new XYChart.Series<>();
		XYChart.Series seriesTreeSetInsert= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(insertionSetListTimes.isEmpty()!=true) {
			seriesTreeSetInsert.getData().add(new XYChart.Data(numberOfTrails,insertionSetListTimes.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(insertionMapListTimes.isEmpty()!=true) {
			seriesTreeMapInsert.getData().add(new XYChart.Data(numberOfTrails,insertionMapListTimes.pop()));
			numberOfTrails+=500;
		}
		
		
		seriesTreeMapInsert.setName("TreeMapStore Insertion");
		seriesTreeSetInsert.setName("TreeSetStore Insertion");
		
		
		
		lineChart.getData().addAll(seriesTreeMapInsert,seriesTreeSetInsert);

		
		return lineChart;
	}
	
	public LineChart generateSearchGraphMaps() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for search TreeMap and HashMap");
		XYChart.Series seriesDifficultyTimeMap= new XYChart.Series<>();
		XYChart.Series seriesElevationTimeMap= new XYChart.Series<>();
		XYChart.Series seriesDistanceTimeMap= new XYChart.Series<>();
		XYChart.Series seriesTypeTimeMap= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(difficultyTimeMapList.isEmpty()!=true) {
			seriesDifficultyTimeMap.getData().add(new XYChart.Data(numberOfTrails,difficultyTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(elevationTimeMapList.isEmpty()!=true) {
			seriesElevationTimeMap.getData().add(new XYChart.Data(numberOfTrails,elevationTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(distanceTimeMapList.isEmpty()!=true) {
			seriesDistanceTimeMap.getData().add(new XYChart.Data(numberOfTrails,distanceTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(typeTimeMapList.isEmpty()!=true) {
			seriesTypeTimeMap.getData().add(new XYChart.Data(numberOfTrails,typeTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		
		seriesDifficultyTimeMap.setName("Search Using Difficulty");
		seriesElevationTimeMap.setName("Search Using Elevation");
		seriesDistanceTimeMap.setName("Search Using Distance");
		seriesTypeTimeMap.setName("Search Using Trail Type");
		
		
		lineChart.getData().addAll(seriesDifficultyTimeMap,seriesElevationTimeMap,seriesDistanceTimeMap,seriesTypeTimeMap);

		
		return lineChart;
	}
	
	public LineChart searchStreamGraph() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for search using Stream");
		XYChart.Series seriesNameTimeMap= new XYChart.Series<>();
		XYChart.Series seriesDifficultyTimeSet= new XYChart.Series<>();
		XYChart.Series seriesElevationTimeSet= new XYChart.Series<>();
		XYChart.Series seriesDistanceTimeSet= new XYChart.Series<>();
		XYChart.Series seriesTypeTimeSet= new XYChart.Series<>();
		XYChart.Series seriesNameTimeSet= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(nameTimeMapList.isEmpty()!=true) {
			seriesNameTimeMap.getData().add(new XYChart.Data(numberOfTrails,nameTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(difficultyTimeSetList.isEmpty()!=true) {
			seriesDifficultyTimeSet.getData().add(new XYChart.Data(numberOfTrails,difficultyTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(elevationTimeSetList.isEmpty()!=true) {
			seriesElevationTimeSet.getData().add(new XYChart.Data(numberOfTrails,elevationTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(distanceTimeSetList.isEmpty()!=true) {
			seriesDistanceTimeSet.getData().add(new XYChart.Data(numberOfTrails,distanceTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(typeTimeSetList.isEmpty()!=true) {
			seriesTypeTimeSet.getData().add(new XYChart.Data(numberOfTrails,typeTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(nameTimeSetList.isEmpty()!=true) {
			seriesNameTimeSet.getData().add(new XYChart.Data(numberOfTrails,nameTimeSetList.pop()));
			numberOfTrails+=500;
		}
		seriesNameTimeMap.setName("Name LinkedList Time");
		seriesDifficultyTimeSet.setName("Difficulty TreeSet Time");
		seriesElevationTimeSet.setName("Elevation TreeSet Time");
		seriesDistanceTimeSet.setName("Distance TreeSet Time");
		seriesTypeTimeSet.setName("Type TreeSet Time");
		seriesNameTimeSet.setName("Name TreeSet Time");
		
		lineChart.getData().addAll(seriesNameTimeMap,seriesDifficultyTimeSet,seriesElevationTimeSet,
				seriesDistanceTimeSet,seriesTypeTimeSet,seriesNameTimeSet);

		
		return lineChart;
	}
	
	public void testDeleteTime(TreeSetStore tSS, TreeMapStore tMS) {
		StringBuilder sb= new StringBuilder();
		sb.append("Delete\t\t\t\t\t\t\t\t\tTime\n");
		sb.append("___________________________________________________\n");
		long nameTimeMap=0;
		long difficultyTimeMap=0;		
		long elevationTimeMap=0;
		long distanceTimeMap=0;	
		long typeTimeMap=0;
		long difficultyTimeSet= 0;
		long elevationTimeSet= 0;
		long distanceTimeSet= 0;
		long typeTimeSet= 0;
		long nameTimeSet=0;
		for (int i=0;i<=11;i++) {
			int trailNumber=i*500;
			int numberOfCycles=trailNumber/10;
			for(int j=0;j<=numberOfCycles;j++) {
			tMS= refreshTreeMapStore(tMS,trailNumber);
			tSS= refreshTreeSetStore(tSS,trailNumber);
			nameTimeMap+=testNameDeleteTime(tMS);
			tMS= refreshTreeMapStore(tMS,trailNumber);
			difficultyTimeMap+=testDifficultyDeleteTime(tMS);
			refreshTreeMapStore(tMS,trailNumber);
			elevationTimeMap+=testElevationDeleteTime(tMS);
			tMS= refreshTreeMapStore(tMS,trailNumber);
			distanceTimeMap+=testDistanceDeleteTime(tMS);
			tMS= refreshTreeMapStore(tMS,trailNumber);
			typeTimeMap+=testTypeDeleteTime(tMS);
			tSS= refreshTreeSetStore(tSS,trailNumber);
			difficultyTimeSet+= testTreeDeleteTime(x->x.getDifficulty().equals(randomDifficulty()),tSS);
			tSS= refreshTreeSetStore(tSS,trailNumber);
			elevationTimeSet+= testTreeDeleteTime(x->x.getElevationGain().equals(integerGenerator()),tSS);
			tSS= refreshTreeSetStore(tSS,trailNumber);
			distanceTimeSet+= testTreeDeleteTime(x->x.getLength().equals(integerGenerator()),tSS);
			tSS= refreshTreeSetStore(tSS,trailNumber);
			typeTimeSet+= testTreeDeleteTime(x->x.getTrailType().equals(randomType()),tSS);
			tSS= refreshTreeSetStore(tSS,trailNumber);
			nameTimeSet+= testTreeDeleteTime(x->x.getTrailName().contains(stringGenerator()),tSS);
			}
			if(i>1) {
			difficultyTimeMapList.add(difficultyTimeMap/numberOfCycles);
			elevationTimeMapList.add(elevationTimeMap/numberOfCycles);
			distanceTimeMapList.add(distanceTimeMap/numberOfCycles);
			typeTimeMapList.add(typeTimeMap/numberOfCycles);
			nameTimeMapList.add(nameTimeMap/numberOfCycles);
			difficultyTimeSetList.add(difficultyTimeSet/numberOfCycles);
			elevationTimeSetList.add(elevationTimeSet/numberOfCycles);
			distanceTimeSetList.add(distanceTimeSet/numberOfCycles);
			typeTimeSetList.add(typeTimeSet/numberOfCycles);
			nameTimeSetList.add(nameTimeSet/numberOfCycles);
			sb.append("difficultyDeleteHashMap "+trailNumber+" trails: "+"\t"+difficultyTimeMap/numberOfCycles+"\n");
			sb.append("elevationDeleteTreeMap "+trailNumber+" trails: "+"\t"+elevationTimeMap/numberOfCycles+"\n");
			sb.append("distanceDeleteTreeMap "+trailNumber+" trails: "+"\t"+distanceTimeMap/numberOfCycles+"\n");
			sb.append("typeDeleteHashMap "+trailNumber+" trails: "+"\t\t"+typeTimeMap/numberOfCycles+"\n");
			sb.append("nameDeleteLinkedList "+trailNumber+" trails: "+"\t\t"+nameTimeMap/numberOfCycles+"\n");
			sb.append("difficultyDeleteTreeSet "+trailNumber+" trails: "+"\t"+difficultyTimeSet/numberOfCycles+"\n");
			sb.append("elevationDeleteTreeSet "+trailNumber+" trails: "+"\t"+elevationTimeSet/numberOfCycles+"\n");
			sb.append("distanceDeleteTreeSet "+trailNumber+" trails: "+"\t"+distanceTimeSet/numberOfCycles+"\n");
			sb.append("typeDeleteTreeSet "+trailNumber+" trails: "+"\t\t"+typeTimeSet/numberOfCycles+"\n");
			sb.append("nameDeleteTreeSet "+trailNumber+" trails: "+"\t\t"+nameTimeSet/numberOfCycles+"\n");
			sb.append("___________________________________________________\n");
			nameTimeMap=0;
			difficultyTimeMap=0;		
			elevationTimeMap=0;
			distanceTimeMap=0;	
			typeTimeMap=0;
			difficultyTimeSet= 0;
			elevationTimeSet= 0;
			distanceTimeSet= 0;
			typeTimeSet= 0;
			nameTimeSet=0;
			}
			else {
			nameTimeMap=0;
			difficultyTimeMap=0;		
			elevationTimeMap=0;
			distanceTimeMap=0;	
			typeTimeMap=0;
			difficultyTimeSet= 0;
			elevationTimeSet= 0;
			distanceTimeSet= 0;
			typeTimeSet= 0;
			nameTimeSet=0;
			}
			
		}
		exportResults("timeResultsDelete.txt",sb);
	}
	
	public LineChart generateDeleteGraphMaps() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for Deletion TreeMap and HashMap");
		XYChart.Series seriesDifficultyTimeMap= new XYChart.Series<>();
		XYChart.Series seriesElevationTimeMap= new XYChart.Series<>();
		XYChart.Series seriesDistanceTimeMap= new XYChart.Series<>();
		XYChart.Series seriesTypeTimeMap= new XYChart.Series<>();
		XYChart.Series seriesNameTimeMap= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(difficultyTimeMapList.isEmpty()!=true) {
			seriesDifficultyTimeMap.getData().add(new XYChart.Data(numberOfTrails,difficultyTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(elevationTimeMapList.isEmpty()!=true) {
			seriesElevationTimeMap.getData().add(new XYChart.Data(numberOfTrails,elevationTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(distanceTimeMapList.isEmpty()!=true) {
			seriesDistanceTimeMap.getData().add(new XYChart.Data(numberOfTrails,distanceTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(typeTimeMapList.isEmpty()!=true) {
			seriesTypeTimeMap.getData().add(new XYChart.Data(numberOfTrails,typeTimeMapList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(nameTimeMapList.isEmpty()!=true) {
			seriesNameTimeMap.getData().add(new XYChart.Data(numberOfTrails,nameTimeMapList.pop()));
			numberOfTrails+=500;
		}
		seriesNameTimeMap.setName("Name LinkedList Time");
		seriesDifficultyTimeMap.setName("Difficulty HashMap Time");
		seriesElevationTimeMap.setName("Elevation TreeMap Time");
		seriesDistanceTimeMap.setName("Distance TreeMap Time");
		seriesTypeTimeMap.setName("Type HashMap Time");
		
		
		lineChart.getData().addAll(seriesDifficultyTimeMap,seriesElevationTimeMap,seriesDistanceTimeMap,seriesTypeTimeMap,seriesNameTimeMap);

		
		return lineChart;
	}
	
	public LineChart deleteStreamGraph() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Number of Trails");
		yAxis.setLabel("Time (ns)");
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time for deletion using Stream");
		XYChart.Series seriesDifficultyTimeSet= new XYChart.Series<>();
		XYChart.Series seriesElevationTimeSet= new XYChart.Series<>();
		XYChart.Series seriesDistanceTimeSet= new XYChart.Series<>();
		XYChart.Series seriesTypeTimeSet= new XYChart.Series<>();
		XYChart.Series seriesNameTimeSet= new XYChart.Series<>();
		int numberOfTrails= 1000;
		while(difficultyTimeSetList.isEmpty()!=true) {
			seriesDifficultyTimeSet.getData().add(new XYChart.Data(numberOfTrails,difficultyTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(elevationTimeSetList.isEmpty()!=true) {
			seriesElevationTimeSet.getData().add(new XYChart.Data(numberOfTrails,elevationTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(distanceTimeSetList.isEmpty()!=true) {
			seriesDistanceTimeSet.getData().add(new XYChart.Data(numberOfTrails,distanceTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(typeTimeSetList.isEmpty()!=true) {
			seriesTypeTimeSet.getData().add(new XYChart.Data(numberOfTrails,typeTimeSetList.pop()));
			numberOfTrails+=500;
		}
		numberOfTrails=1000;
		while(nameTimeSetList.isEmpty()!=true) {
			seriesNameTimeSet.getData().add(new XYChart.Data(numberOfTrails,nameTimeSetList.pop()));
			numberOfTrails+=500;
		}
		seriesDifficultyTimeSet.setName("Difficulty TreeSet Time");
		seriesElevationTimeSet.setName("Elevation TreeSet Time");
		seriesDistanceTimeSet.setName("Distance TreeSet Time");
		seriesTypeTimeSet.setName("Type TreeSet Time");
		seriesNameTimeSet.setName("Name TreeSet Time");
		
		lineChart.getData().addAll(seriesDifficultyTimeSet,seriesElevationTimeSet,
				seriesDistanceTimeSet,seriesTypeTimeSet,seriesNameTimeSet);

		
		return lineChart;
	}
	
	
	public TreeMapStore refreshTreeMapStore(TreeMapStore tMS,int trailNumber) {
		tMS= new TreeMapStore();
		Factory.trailFactory(tMS, trailNumber);
		return tMS;
	}



	public TreeSetStore refreshTreeSetStore(TreeSetStore tSS,int trailNumber) {
		tSS= new TreeSetStore();
		Factory.trailFactory2(tSS, trailNumber);
		return tSS;
	}
	
	
	private long testDifficultyDeleteTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.deleteByDifficulty(randomDifficulty());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testElevationDeleteTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.deleteByElevation(integerGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testDistanceDeleteTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.deleteByLength(integerGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testTypeDeleteTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.deleteByTrailType(randomType());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
	private long testNameDeleteTime(TreeMapStore tMS) {
		Timer.startTimer();
		tMS.deleteByName(stringGenerator());
		Timer.endTimer();
		return Timer.getEndTime();
	}
	
}
