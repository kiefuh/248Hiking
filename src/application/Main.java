package application;
	




import javafx.application.Application;
import javafx.stage.Stage;
import model.GraphStore;
import store.TreeMapStore;
import store.TreeSetStore;
import store.UserStore;
import util.TimeTest;
import util.TimeTestUser;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			UserStore userStore= new UserStore();
			TimeTestUser ttU = new TimeTestUser(userStore);
			ttU.getTime();
			TreeMapStore tMS= new TreeMapStore();
			TreeSetStore tSS= new TreeSetStore();
			TimeTest test= new TimeTest();
			test.testSearchTime(tSS, tMS);
			LineChart searchGraph= test.generateSearchGraphMaps();
			LineChart searchGraphStream= test.searchStreamGraph();
			TimeTest test2= new TimeTest();
			TreeMapStore tMS2= new TreeMapStore();
			TreeSetStore tSS2= new TreeSetStore();
			test2.testDeleteTime(tSS2, tMS2);
			LineChart deleteGraph= test2.generateDeleteGraphMaps();
			LineChart deleteStreamGraph = test2.deleteStreamGraph();
			LineChart insertionGraph=test.generateInsertionGraph();
			LineChart insertionGraphUser=ttU.generateInsertionGraph();
			LineChart searchingUserGraph=ttU.generateSearchGraph();
			LineChart deletingUserGraph=ttU.generateRemovalGraph();
			VBox boxSearch= new VBox();
			boxSearch.getChildren().addAll(searchGraph,searchGraphStream);
			Tab tab1= new Tab("Search Times Stores");
			tab1.setContent(boxSearch);
			VBox boxDelete= new VBox();
			boxDelete.getChildren().addAll(deleteGraph,deleteStreamGraph);
			Tab tab2= new Tab("Deletion Times Stores");
			tab2.setContent(boxDelete);
			VBox boxInsertion= new VBox();
			boxInsertion.getChildren().addAll(insertionGraph);
			Tab tab3= new Tab("Insertion Times Store");
			tab3.setContent(boxInsertion);
			VBox boxUserTimes= new VBox();
			boxUserTimes.getChildren().addAll(insertionGraphUser,searchingUserGraph,deletingUserGraph);
			Tab tab4= new Tab("User Operations Times");
			tab4.setContent(boxUserTimes);
//			GraphStore graphStore= new GraphStore(searchGraph,searchGraphStream,deleteGraph,deleteStreamGraph,insertionGraph,
//					insertionGraphUser,searchingUserGraph,deletingUserGraph);
			//SaveLoad.save(graphStore, "graphStore.dat");
			TabPane tabPane= new TabPane();
			tabPane.getTabs().addAll(tab1,tab2,tab3,tab4);
			Scene scene = new Scene(tabPane,1200,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
