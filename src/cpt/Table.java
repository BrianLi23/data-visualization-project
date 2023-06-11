package cpt;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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

public class Table {
    private VBox createTableView() throws IOException {

        TableView<data> tableView = new TableView<>();

        // Create columns for the table
        TableColumn<data, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getYear()).asObject());

        TableColumn<data, String> entityColumn = new TableColumn<>("Entity");
        entityColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEntity()));

        TableColumn<data, String> parameterColumn = new TableColumn<>("Parameter");
        parameterColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getParameter()));

        TableColumn<data, String> domainColumn = new TableColumn<>("Domain");
        domainColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDomain()));

        TableColumn<data, String> dayColumn = new TableColumn<>("Day");
        dayColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDay()));

        // Add columns to the table
        tableView.getColumns().addAll(yearColumn, entityColumn, parameterColumn, domainColumn, dayColumn);

        ArrayList<data> chartData = listData.getDataPoints();
        sorting.sortYear(false, chartData);

        // Create data for the table
        ObservableList<data> tableData = FXCollections.observableArrayList(chartData);

        // Set the data to the table
        tableView.setItems(tableData);

        // Set width of table columns
        yearColumn.setPrefWidth(100);
        entityColumn.setPrefWidth(200);
        parameterColumn.setPrefWidth(200);
        domainColumn.setPrefWidth(150);
        dayColumn.setPrefWidth(150);

        Button sortButton = new Button("Sort Data by Year");
        sortButton.setOnAction(event -> {
            System.out.println("pressed");
            sorting.sortEntity(false, chartData);
            tableView.setItems(tableData);
            tableView.refresh();
        });

        // Create a VBox to hold the table view and sorting controls
        VBox vbox = new VBox(10); // Set spacing between table and controls
        vbox.getChildren().addAll(tableView, sortButton);

        return vbox;
    }
}
