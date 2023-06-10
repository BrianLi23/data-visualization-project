/* ....Show License.... */
package charts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import javafx.scene.chart.StackedAreaChart;

/**
 * A chart that displays rectangular bars with heights indicating data values
 * for categories. Used for displaying information when at least one axis has
 * discontinuous or discrete data.
 */
public class BarChartApp extends Application {

  private StackedAreaChart chart;
  private NumberAxis xAxis;
  private NumberAxis yAxis;

  public Parent createContent() {
    xAxis = new NumberAxis("X Values", 1.0d, 9.0d, 2.0d);
    yAxis = new NumberAxis("Y Values", 0.0d, 30.0d, 2.0d);

    ObservableList<StackedAreaChart.Series> areaChartData = FXCollections.observableArrayList(
        new StackedAreaChart.Series("Series 1",
            FXCollections.observableArrayList(
                new StackedAreaChart.Data(0, 4),
                new StackedAreaChart.Data(2, 5),
                new StackedAreaChart.Data(4, 4),
                new StackedAreaChart.Data(6, 2),
                new StackedAreaChart.Data(8, 6),
                new StackedAreaChart.Data(10, 8))),
        new StackedAreaChart.Series("Series 2",
            FXCollections.observableArrayList(
                new StackedAreaChart.Data(0, 8),
                new StackedAreaChart.Data(2, 2),
                new StackedAreaChart.Data(4, 9),
                new StackedAreaChart.Data(6, 7),
                new StackedAreaChart.Data(8, 5),
                new StackedAreaChart.Data(10, 7))),
        new StackedAreaChart.Series("Series 3",
            FXCollections.observableArrayList(
                new StackedAreaChart.Data(0, 2),
                new StackedAreaChart.Data(2, 5),
                new StackedAreaChart.Data(4, 8),
                new StackedAreaChart.Data(6, 6),
                new StackedAreaChart.Data(8, 9),
                new StackedAreaChart.Data(10, 7))));
    chart = new StackedAreaChart(xAxis, yAxis, areaChartData);
    return chart;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setScene(new Scene(createContent()));
    primaryStage.show();
  }

  /**
   * Java main for when running without JavaFX launcher
   */
  public static void main(String[] args) {
    launch(args);
  }
}