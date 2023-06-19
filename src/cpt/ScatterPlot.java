package cpt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import org.controlsfx.control.RangeSlider;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class ScatterPlot {

    // Create VBoxes for scatterplot
    private VBox VBoxScatterPlot = new VBox(0);

    // Create X and Y axes
    private NumberAxis xAxis = new NumberAxis(1950, 2023, 25);
    private NumberAxis yAxis = new NumberAxis();

    // Create a scatter chart
    private ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

    // Import data from CSV File using listData class
    private ArrayList<data> dataPoints = ListData.getDataPoints();

    // Initialzing minimum and maximum x axis values
    private static final double MIN_X_VALUE = 1949;
    private static final double MAX_X_VALUE = 2024;

    // (ATTEMPTED) Due to time could not add legend labeling each datapoint
    List<String> legendCategories = Arrays.asList("Drawing", "Driving", "Games", "Language", "Multimodal", "Other",
            "Recommendation", "Search", "Speech", "Video", "Vision");
    List<Color> legendColors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.ORANGE, Color.BROWN,
            Color.PURPLE, Color.YELLOW, Color.NAVY, Color.MAGENTA, Color.MAROON, Color.PINK);

    // Create a series with data point to represent the legend item
    XYChart.Series<Number, Number> legendSeries = new XYChart.Series<>();

    public ScatterPlot() throws IOException {

        // Styling scatter plot using css files
        scatterChart.getStylesheets().add(getClass().getResource("css/scatter-chart.css").toExternalForm());

        // Set labels for x and y axis
        xAxis.setLabel("Years");
        yAxis.setLabel("Parameters (Log Increments | 10^n)");

        // Set height
        scatterChart.setPrefHeight(650);
        scatterChart.setPrefWidth(1000);

        // Adding padding to Vbox
        VBoxScatterPlot.setPadding(new Insets(10));

        // Set chart title
        scatterChart.setTitle("Scatter Plot Graph");

        // Create a series for the data points
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data Points");

        // (ATTEMPTED) attempted to add legend
        for (int i = 0; i < legendCategories.size(); i++) {
            String category = legendCategories.get(i);
            legendSeries.setName(category);

        }

        // Add data points to the series
        for (data specificData : dataPoints) {

            if (!specificData.getParameter().equals("NA")) {

                // Since number is too big for int, set as BigInteger
                BigInteger numBig = new BigInteger(specificData.getParameter());

                // Find parameter on logramethic scale. (10^n)
                double dblParameters = Math.log10(numBig.doubleValue());

                // Create an XYChart. Data object using the identifier and value from each
                // DataPoint
                XYChart.Data<Number, Number> PlotData = new XYChart.Data<>(specificData.getYear(), dblParameters);

                // Set the data point object as the extra value for the data point
                PlotData.setExtraValue(specificData);
                series.getData().add(PlotData);
            }
        }

        // Add the series to the scatter chart
        scatterChart.getData().add(series);

        // Setting the legend as visible and below the graph
        scatterChart.setLegendVisible(true);
        scatterChart.setLegendSide(Side.BOTTOM);
        scatterChart.setPadding(new Insets(0, 0, 20, 0));

        // Create the range slider for X-axis
        RangeSlider xSlider = new RangeSlider(MIN_X_VALUE, MAX_X_VALUE, MIN_X_VALUE, MAX_X_VALUE);
        xSlider.setBlockIncrement(1);
        xSlider.setMinorTickCount(0);
        xSlider.setMajorTickUnit(41);
        xSlider.setShowTickLabels(true);
        xSlider.setShowTickMarks(true);

        // Create a label to display point information
        Label infoLabel = new Label();
        infoLabel.setFont(Font.font(14));
        infoLabel.setStyle("-fx-background-color: white; -fx-padding: 5px; -fx-border-color: black;");
        infoLabel.setVisible(false);

        // Handle mouse hover events on data points
        for (XYChart.Data<Number, Number> dataPoint : series.getData()) {
            data point = (data) dataPoint.getExtraValue(); // Retrieve the data point object from extra value

            dataPoint.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                String parameters = getDataPointParameters(point); // Get the parameters for the data point
                infoLabel.setText(parameters); // Update the label text with parameters only
                infoLabel.setVisible(true);
            });

            dataPoint.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                infoLabel.setVisible(false);
            });
        }

        // Bind the axis lower and upper bounds to the range slider values
        xAxis.lowerBoundProperty().bind(xSlider.lowValueProperty());
        xAxis.upperBoundProperty().bind(xSlider.highValueProperty());

        // Add the scatter plot to the vbox
        VBoxScatterPlot.getChildren().addAll(scatterChart, xSlider, infoLabel);
    }

    /**
     * The method used to return the graph created in a VBox for the main file to
     * display
     * 
     * @author Brian Li
     */
    public VBox createScatterPlotView() throws IOException {
        return VBoxScatterPlot;
    }

    /**
     * The method used to return a string containing the datapoint information for
     * the label
     * 
     * @param point Datapoint to use
     * @author Brian Li
     */
    private String getDataPointParameters(data point) {
        String entity = point.getEntity();
        String parameter = point.getParameter();
        int year = point.getYear();
        String day = point.getDay();
        String domain = point.getDomain();

        return "Entity: " + entity + "\nParameter: " + parameter + "\nYear: " + year + "\nDay: " + day + "\nDomain: "
                + domain;
    }

}
