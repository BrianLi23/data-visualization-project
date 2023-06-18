package cpt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import org.controlsfx.control.RangeSlider;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

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

    private static final double MIN_X_VALUE = 1949; // Minimum X-axis value
    private static final double MAX_X_VALUE = 2024; // Maximum X-axis value

    public ScatterPlot() throws IOException {

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

        // Create legend items
        for (int i = 0; i < 11; i++) {
            String domain = "Domain " + (i + 1); // Replace with your actual domain values

            // Create a series with a single data point to represent the legend item
            XYChart.Series<Number, Number> legendSeries = new XYChart.Series<>();
            legendSeries.getData().add(new XYChart.Data<>(0, 0)); // Add a dummy data point
            legendSeries.setName(domain);

            // Customize the legend item
            Node legendNode = legendSeries.getNode();
            if (legendNode instanceof StackPane) {
                StackPane legendItem = (StackPane) legendNode;

                // Create a colored circle to represent the legend item
                Circle symbol = new Circle(5);
                symbol.setFill(getLegendColor(i)); // Replace with your own color generation logic

                // Add the symbol to the legend item
                legendItem.getChildren().add(symbol);

                // Add the legend item to the scatter chart
                scatterChart.getData().add(legendSeries);
            }
        }

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

    private String getDataPointParameters(data point) {
        String entity = point.getEntity();
        String parameter = point.getParameter();
        int year = point.getYear();
        String day = point.getDay();
        String domain = point.getDomain();

        return "Entity: " + entity + "\nParameter: " + parameter + "\nYear: " + year + "\nDay: " + day + "\nDomain: "
                + domain;
    }

    private Color getLegendColor(int index) {
        // Replace with your own color generation logic
        Color[] colors = {
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.YELLOW,
                Color.ORANGE,
                Color.PURPLE,
                Color.CYAN,
                Color.MAGENTA,
                Color.PINK,
                Color.GRAY,
                Color.BROWN
        };

        return colors[index % colors.length];
    }

}
