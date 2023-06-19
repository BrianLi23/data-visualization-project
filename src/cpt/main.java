package cpt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;

public class Main extends Application {

    /**
     * The main class of program. where the graphs, tabs, tables and scenes are
     * initialized onto the screen
     * 
     * @author Brian Li
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFX Showcase Application");

        // Creating a tabpane to hold the tabs
        TabPane tabPane = new TabPane();

        // Creating tab for table and graphs
        Tab tableTab = new Tab("Table");
        Table Tabler = new Table();
        tableTab.setContent(Tabler.createTableView());

        Tab graphTab = new Tab("Bar Graph");
        BarGraph barGraph = new BarGraph();
        graphTab.setContent(barGraph.createGraphView());

        Tab graphTab2 = new Tab("Scatter Plot");
        ScatterPlot scatterChart = new ScatterPlot();
        graphTab2.setContent(scatterChart.createScatterPlotView());

        // Adding tabs to the TabPane
        tabPane.getTabs().addAll(graphTab, graphTab2, tableTab);

        // Create a VBox to hold the TabPane
        VBox vbox = new VBox(tabPane);

        // Create a scene and set it to the stage
        Scene scene = new Scene(vbox, 1200, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
