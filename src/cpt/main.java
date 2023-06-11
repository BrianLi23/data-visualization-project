package cpt;

import java.io.*;
import java.util.*;
import java.lang.Math;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.math.BigInteger;

public class main extends Application {

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
        // Tab tableTab = new Tab("Table");
        // tableTab.setContent(createTableView());

        Tab graphTab = new Tab("Graph");
        BarGraph barGraph = new BarGraph();
        graphTab.setContent(barGraph.createGraphView());

        // Adding tabs to the TabPane
        tabPane.getTabs().addAll(graphTab);

        // Create a VBox to hold the TabPane
        VBox vbox = new VBox(tabPane);

        // Create a scene and set it to the stage
        Scene scene = new Scene(vbox, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
