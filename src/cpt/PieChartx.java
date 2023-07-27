package cpt;

import java.io.IOException;

import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PieChartx {

    // Create VBoxes for Pie Chart
    private VBox PieChartBox = new VBox(0);

    // Import data from CSV File using listData class
    private ArrayList<data> dataPoints = listData.getDataPoints();

    public PieChartx() throws IOException {

        // Creating the pie chart
        PieChart PieChartSeen = new PieChart();

        // Styling Pie Chart using css files
        PieChartSeen.getStylesheets().add(getClass().getResource("css/pie-chart.css").toExternalForm());

        // Adding padding and spacing to Vbox
        PieChartBox.setPadding(new Insets(10));
        PieChartBox.setSpacing(10);

        // Create a map to store the frequency of each domain
        Map<String, Integer> domainFrequency = new HashMap<>();

        // Calculate the frequency of each unique entity in the list
        for (data dataPoint : dataPoints) { // string entity
            domainFrequency.put(dataPoint.getDomain(), domainFrequency.getOrDefault(dataPoint.getDomain(), 0) + 1);
        }

        // Create a list of data representing the domain and their frequencies
        for (String entity : domainFrequency.keySet()) {
            int frequency = domainFrequency.get(entity);
            PieChart.Data dataPoint = new PieChart.Data(entity, frequency);
            PieChartSeen.getData().add(dataPoint);
        }

        // Set Pie Chart title
        PieChartSeen.setTitle("Domain Distribution");

        // Set Pie Chart size
        PieChartSeen.setPrefWidth(800);
        PieChartSeen.setPrefHeight(800);

        // Add the piechart to the vbox
        PieChartBox.getChildren().addAll(PieChartSeen);
    }

    /**
     * The method used to return the graph created in a VBox for the main file to
     * display
     * 
     * @author Brian Li
     */
    public VBox createPieChartView() throws IOException {
        return PieChartBox;
    }
}
