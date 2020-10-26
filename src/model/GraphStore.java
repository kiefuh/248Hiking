package model;

import java.io.Serializable;

import javafx.scene.chart.LineChart;

public class GraphStore implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8843869650416283580L;
	LineChart searchGraph;
	LineChart searchGraphStream;
	LineChart deleteGraph;
	LineChart deleteStreamGraph;
	LineChart insertionGraph;
	LineChart insertionGraphUser;
	LineChart searchingUserGraph;
	LineChart deletingUserGraph;
	public GraphStore(LineChart searchGraph, LineChart searchGraphStream, LineChart deleteGraph,
			LineChart deleteStreamGraph, LineChart insertionGraph, LineChart insertionGraphUser,
			LineChart searchingUserGraph, LineChart deletingUserGraph) {
		super();
		this.searchGraph = searchGraph;
		this.searchGraphStream = searchGraphStream;
		this.deleteGraph = deleteGraph;
		this.deleteStreamGraph = deleteStreamGraph;
		this.insertionGraph = insertionGraph;
		this.insertionGraphUser = insertionGraphUser;
		this.searchingUserGraph = searchingUserGraph;
		this.deletingUserGraph = deletingUserGraph;
	}
	public LineChart getSearchGraph() {
		return searchGraph;
	}
	public void setSearchGraph(LineChart searchGraph) {
		this.searchGraph = searchGraph;
	}
	public LineChart getSearchGraphStream() {
		return searchGraphStream;
	}
	public void setSearchGraphStream(LineChart searchGraphStream) {
		this.searchGraphStream = searchGraphStream;
	}
	public LineChart getDeleteGraph() {
		return deleteGraph;
	}
	public void setDeleteGraph(LineChart deleteGraph) {
		this.deleteGraph = deleteGraph;
	}
	public LineChart getDeleteStreamGraph() {
		return deleteStreamGraph;
	}
	public void setDeleteStreamGraph(LineChart deleteStreamGraph) {
		this.deleteStreamGraph = deleteStreamGraph;
	}
	public LineChart getInsertionGraph() {
		return insertionGraph;
	}
	public void setInsertionGraph(LineChart insertionGraph) {
		this.insertionGraph = insertionGraph;
	}
	public LineChart getInsertionGraphUser() {
		return insertionGraphUser;
	}
	public void setInsertionGraphUser(LineChart insertionGraphUser) {
		this.insertionGraphUser = insertionGraphUser;
	}
	public LineChart getSearchingUserGraph() {
		return searchingUserGraph;
	}
	public void setSearchingUserGraph(LineChart searchingUserGraph) {
		this.searchingUserGraph = searchingUserGraph;
	}
	public LineChart getDeletingUserGraph() {
		return deletingUserGraph;
	}
	public void setDeletingUserGraph(LineChart deletingUserGraph) {
		this.deletingUserGraph = deletingUserGraph;
	}
	
	
}
