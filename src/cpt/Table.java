package cpt;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class Table {

    // Creating instance variables
    private TableView<data> tableView = new TableView<>();
    private ChoiceBox<String> sortOptions = new ChoiceBox<>();
    ArrayList<data> chartData = ListData.getDataPoints();

    // Boolean Variables to keep track if it's clicked again for reverse
    boolean boolEntityReverse = false;
    boolean boolYearReverse = false;
    boolean boolParameterReverse = false;
    boolean boolDomainReverse = false;
    boolean boolDayReverse = false;

    // Hold the table and drop menu inside a hbox
    HBox TableHBox = new HBox(10); // Set spacing in hbox

    // Create a VBox to everything in place
    VBox WholeVBox = new VBox(15); // Set spacing in vbox
    VBox ControlVBox = new VBox(15);

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

        // Set spacing between the elements in the WholeVBox
        WholeVBox.setSpacing(20);

        // Set padding around the elements in the HBox
        WholeVBox.setPadding(new Insets(20));
        TableHBox.setPadding(new Insets(20));

        // Create a label for the table title
        Label titleLabel = new Label("Number of parameters in notable artificial intelligence systems");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Add sort options to the choice box
        sortOptions.getItems().addAll("Sort by Entity", "Sort by Year", "Sort by Parameter", "Sort by Domain",
                "Sort by Day", "Sort Ascending/Descending");

        sortOptions.setPrefWidth(300);

        // Create a filtered list to hold the filtered data based on search criteria
        FilteredList<data> filteredData = new FilteredList<>(tableData);

        // Add a search field
        TextField searchField = new TextField();
        searchField.setPromptText("Seach Artificial Intelligence Systems:"); // Set a prompt text for the search field

        // Updating the filtered list based on the search criteria
        searchField.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate(data -> {
                        String searchText = newValue.toLowerCase();
                        if (data.getEntity().toLowerCase().contains(searchText)) {
                            return true;
                        }
                        return false;
                    });

                    tableView.setItems(filteredData);
                });

        // Add listener to sort the table when an option is selected
        sortOptions.setOnAction(event -> {

            // Check which option is selected and sort accordingly
            String selectedOption = sortOptions.getValue();
            if (selectedOption.equals("Sort by Entity")) {
                if (!boolEntityReverse) {
                    Sorting.sortEntity(false, chartData);
                }

                else {
                    Sorting.sortEntity(true, chartData);
                }

                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Sort by Year")) {

                if (!boolYearReverse) {
                    Sorting.sortYear(false, chartData);
                }

                else {
                    Sorting.sortYear(true, chartData);
                }

                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Sort Ascending/Descending")) {
                if (boolYearReverse) {
                    boolDayReverse = false;
                    boolDomainReverse = false;
                    boolEntityReverse = false;
                    boolParameterReverse = false;
                    boolYearReverse = false;
                }

                else {
                    boolDayReverse = true;
                    boolDomainReverse = true;
                    boolEntityReverse = true;
                    boolParameterReverse = true;
                    boolYearReverse = true;
                }
            }

            else if (selectedOption.equals("Sort by Parameter")) {

                if (!boolEntityReverse) {
                    Sorting.sortEntity(false, chartData);
                }

                else {
                    Sorting.sortEntity(true, chartData);
                }

                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Sort by Domain")) {

                if (!boolDomainReverse) {
                    Sorting.sortDomain(false, chartData);
                }

                else {
                    Sorting.sortDomain(true, chartData);
                }

                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }

            else if (selectedOption.equals("Sort by Day")) {

                if (!boolDayReverse) {
                    Sorting.sortDay(false, chartData);
                }

                else {
                    Sorting.sortDay(true, chartData);
                }

                ObservableList<data> newTableData = FXCollections.observableArrayList(chartData);
                tableView.setItems(newTableData);
                tableView.refresh();
            }
        });

        ControlVBox.getChildren().addAll(searchField, sortOptions);
        TableHBox.getChildren().addAll(tableView, ControlVBox);
        WholeVBox.getChildren().addAll(titleLabel, TableHBox);

    }

    /**
     * The method used to return the graph created in a VBox for the main file to
     * display
     * 
     * @author Brian Li
     */
    public VBox createTableView() throws IOException {
        return WholeVBox;
    }
}
