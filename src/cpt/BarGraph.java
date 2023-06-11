package cpt;

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

public class BarGraph extends Application {

    public BarGraph() {
        // Define axes of graph
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Import data from CSV File using listData class
        ArrayList<data> dataPoints = listData.getDataPoints();

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setPrefWidth(1000);
        barChart.setPrefHeight(1000);

        barChart.setTitle("Number of parameters in notable artificial intelligence systems");

        // Read data from the ArrayList
        ObservableList<XYChart.Data<String, Number>> data = getDataFromArrayList();

        // Create a series for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>(data);

        // Add the series to the bar chart
        barChart.getData().add(series);

        // Create a VBox to hold the bar chart
        VBox vbox = new VBox(0); // Set spacing between bar chart

        FlowPane container = new FlowPane(30, 10); // Set horizontal and vertical spacing between checkboxes
        container.setPrefWrapLength(400); // Set preferred width of the FlowPane (adjust as needed)
        container.setPadding(new Insets(10, 30, 10, 30));

        // Create a list of checkboxes
        ArrayList<CheckBox> checkboxList = new ArrayList<>();

        // For each datapoint, a new checkbox is created for it
        for (data dataPoint : dataPoints) {
            CheckBox checkbox = new CheckBox(dataPoint.getEntity());

            // Set each checkbox as unclicked
            checkbox.setSelected(false);

            checkbox.setOnAction(event -> updateGraphVisibility(barChart, dataPoint, checkbox.isSelected()));
            checkboxList.add(checkbox);
        }

        TextField searchField = new TextField();
        searchField.setPromptText("Search"); // Set a prompt text for the search field
        searchField.textProperty()
                .addListener((observable, oldValue, newValue) -> filterCheckboxes(newValue, checkboxList));

        // Add checkboxes to the FlowPane
        container.getChildren().addAll(checkboxList);

        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setPrefViewportHeight(200);
        scrollPane.setFitToWidth(true); // Allow the ScrollPane to fit the width of the VBox

        // Add the bar chart and the checkbox container to the VBox
        vbox.getChildren().addAll(barChart, searchField, scrollPane);

        return vbox;
    }

    /**
     * The method used to create the visuals of the graph
     * 
     * @author Brian Li
     */
    private VBox createGraphView() throws IOException {

        // Define axes of graph
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Import data from CSV File using listData class
        ArrayList<data> dataPoints = listData.getDataPoints();

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setPrefWidth(1000);
        barChart.setPrefHeight(1000);

        barChart.setTitle("Number of parameters in notable artificial intelligence systems");

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();

        sorting.sortEntity(true, dataPoints);

        for (data specificData : dataPoints) {

            if (!specificData.getParameter().equals("NA")) {

                // Create a unique identifier for each data point (year + entity + parameter)
                String identifier = specificData.getYear() + " | " + specificData.getEntity() + " | "
                        + specificData.getDay();

                // Since number is too big for int, set as BigInteger
                BigInteger numBig = new BigInteger(specificData.getParameter());

                // Find parameter on logramethic scale. (10^n)
                double dblParameters = Math.log10(numBig.doubleValue());

                // Create an XYChart. Data object using the identifier and value from each
                // DataPoint
                XYChart.Data<String, Number> chartData = new XYChart.Data<>(identifier, dblParameters);
                data.add(chartData);
            }
        }

        // Create a series for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>(data);

        // Add the series to the bar chart
        barChart.getData().add(series);

        // Create a VBox to hold the bar chart
        VBox vbox = new VBox(0); // Set spacing between bar chart

        FlowPane container = new FlowPane(30, 10); // Set horizontal and vertical spacing between checkboxes
        container.setPrefWrapLength(400); // Set preferred width of the FlowPane (adjust as needed)
        container.setPadding(new Insets(10, 30, 10, 30));

        // Create a list of checkboxes
        ArrayList<CheckBox> checkboxList = new ArrayList<>();

        // For each datapoint, a new checkbox is created for it
        for (data dataPoint : dataPoints) {
            CheckBox checkbox = new CheckBox(dataPoint.getEntity());

            // Set each checkbox as unclicked
            checkbox.setSelected(false);

            checkbox.setOnAction(event -> updateGraphVisibility(barChart, dataPoint, checkbox.isSelected()));
            checkboxList.add(checkbox);
        }

        TextField searchField = new TextField();
        searchField.setPromptText("Search"); // Set a prompt text for the search field
        searchField.textProperty()
                .addListener((observable, oldValue, newValue) -> filterCheckboxes(newValue, checkboxList));

        // Add checkboxes to the FlowPane
        container.getChildren().addAll(checkboxList);

        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setPrefViewportHeight(200);
        scrollPane.setFitToWidth(true); // Allow the ScrollPane to fit the width of the VBox

        // Add the bar chart and the checkbox container to the VBox
        vbox.getChildren().addAll(barChart, searchField, scrollPane);

        return vbox;
    }

    /**
     * Void method used to filter the checkboxes to see if it contains the string
     * inputted
     * 
     * @author Brian Li
     */
    private void filterCheckboxes(String searchText, List<CheckBox> checkboxes) {
        for (CheckBox checkbox : checkboxes) {
            String checkboxText = checkbox.getText();
            checkbox.setVisible(checkboxText.toLowerCase().contains(searchText.toLowerCase()));
        }
    }

    /**
     * Void method that update graph based on whats
     * 
     * @author Brian Li
     */
    private void updateGraphVisibility(BarChart<String, Number> chart, data dataPoint, boolean isVisible) {

        // Clear the bar chart
        barChart.getData().clear();

        // Check if any checkbox is selected
        boolean anySelected = false;
        for (CheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                anySelected = true;
                break;
            }
        }

        // If no checkbox is selected, return
        if (!anySelected) {
            return;
        }

        // Add data to the bar chart for selected checkboxes
        for (CheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                String parameter = checkbox.getText();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(parameter);

                // Add data points for the selected parameter
                for (DataPoint dataPoint : dataPoints) {
                    if (dataPoint.getParameter().equals(parameter)) {
                        series.getData().add(new XYChart.Data<>(dataPoint.getYear(), dataPoint.getValue()));
                    }
                }

                // Add the series to the bar chart
                barChart.getData().add(series);
            }
        }
    }

    private ObservableList<XYChart.Data<String, Number>> getDataFromArrayList() throws IOException {

        return data;
    }
}
