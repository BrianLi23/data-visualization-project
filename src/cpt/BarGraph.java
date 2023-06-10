package cpt;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BarGraph extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFX Showcase Application");

        // Create a TabPane to hold the tabs
        TabPane tabPane = new TabPane();

        // Create a tab for the graph
        Tab graphTab = new Tab("Graph");
        graphTab.setContent(createGraphView());

        // Add the tabs to the TabPane
        tabPane.getTabs().addAll(graphTab);

        // Create a VBox to hold the TabPane
        VBox vbox = new VBox(tabPane);

        // Create a scene and set it to the stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BarChart<String, Number> createGraphView() throws IOException {

        // Define axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Number of parameters in notable artificial intelligence systems");

        // Read data from the ArrayList
        ObservableList<XYChart.Data<String, Number>> data = getDataFromArrayList();

        // Create a series for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>(data);

        // Add the series to the bar chart
        barChart.getData().add(series);

        return barChart;
    }

    private ObservableList<XYChart.Data<String, Number>> getDataFromArrayList() throws IOException {

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();

        // Retrieve the ArrayList object from the DataPointProvider class
        ArrayList<data> dataPoints = listData.getDataPoints();
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

        return data;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
