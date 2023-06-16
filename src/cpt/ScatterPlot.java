package cpt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class ScatterPlot {

    // Create VBoxes for bargraph
    private VBox VBoxScatterPlot = new VBox(0);

    // Create X and Y axes
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();

    // Create a scatter chart
    private ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

    // Import data from CSV File using listData class
    private ArrayList<data> dataPoints = ListData.getDataPoints();

    public ScatterPlot() throws IOException {

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
                series.getData().add(PlotData);
            }
        }
        // Add the series to the scatter chart
        scatterChart.getData().add(series);

        // Add the scatter plot to the vbox
        VBoxScatterPlot.getChildren().addAll(scatterChart);
    }

}
