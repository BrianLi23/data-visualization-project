package cpt;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.math.BigInteger;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Table {

    // Creating instance variable
    private TableView<data> tableView = new TableView<>();
    private ChoiceBox<String> sortOptions = new ChoiceBox<>();
    ArrayList<data> chartData = listData.getDataPoints();

    // Hold the table and drop menu inside a hbox
    // Set up the layout
    HBox HBoxTable = new HBox(10);

    // Create a VBox to hold the table view and sorting controls
    VBox VBox = new VBox(15); // Set spacing between table and controls

    public Table() throws IOException {

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

        sorting.sortYear(false, chartData);

        // Create data for the table
        ObservableList<data> tableData = FXCollections.observableArrayList(chartData);

        // Set the data to the table
        tableView.setItems(tableData);

        // Set width of table columns
        yearColumn.setPrefWidth(100);
        entityColumn.setPrefWidth(200);
        parameterColumn.setPrefWidth(200);
        domainColumn.setPrefWidth(200);
        dayColumn.setPrefWidth(150);
        tableView.setPrefHeight(800);

        // Set spacing between the elements in the VBox
        VBox.setSpacing(20);

        // Set padding around the elements in the HBox
        VBox.setPadding(new Insets(20));
        HBoxTable.setPadding(new Insets(20));

        // Create a label for the table title
        Label titleLabel = new Label("Number of parameters in notable artificial intelligence systems");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Add sort options to the choice box
        sortOptions.getItems().addAll("Sort by Entity", "Sort by Year", "Sort by Parameter", "Sort by Domain",
                "Sort by Day");

        sortOptions.setPrefWidth(300);

        // Add listener to sort the table when an option is selected
        sortOptions.setOnAction(event -> {
            String selectedOption = sortOptions.getValue();
            if (selectedOption.equals("Entity")) {
                sorting.sortEntity(false, chartData);
                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Year")) {
                sorting.sortYear(false, chartData);
                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Parameter")) {
                sorting.sortParameter(false, chartData);
                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Domain")) {
                sorting.sortDomain(false, chartData);
                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Day")) {
                sorting.sortDay(false, chartData);
                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }
        });

        HBoxTable.getChildren().addAll(tableView, sortOptions);
        HBoxTable.setPadding(new Insets(20));
        VBox.getChildren().addAll(titleLabel, HBoxTable);
    }

    /**
     * The method used to return the graph created in a VBox for the main file to
     * display
     * 
     * @author Brian Li
     */
    public VBox createTableView() throws IOException {
        return VBox;
    }
}
