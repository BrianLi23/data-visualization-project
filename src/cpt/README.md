package for your cpt. do not put your project README details here, put it in the main project README
https://ourworldindata.org/grapher/artificial-intelligence-parameter-count

- What i need to impliment

COlour coded based on its domain
For table, show base on domain or year
Search button
Have extra infomration, median, mean, etc.
Have an extra interface

/\*\*
_ Void method that update graph based on whats selected by the checkbox
_
_ @author Brian Li
_/
private void updateGraphVisibility(BarChart<String, Number> chart, boolean isVisible) {

        // Clear the bar chart
        barChart.getData().clear();

        // Check if any checkbox is selected
        boolean anySelected = false;
        for (CheckBox checkbox : checkboxList) {
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
        for (CheckBox checkbox : checkboxList) {
            if (checkbox.isSelected()) {
                String parameter = checkbox.getText();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(parameter);

                // Add data points for the selected parameter
                for (data dataPoint : dataPoints) {
                    if (dataPoint.getParameter().equals(parameter)) {
                        series.getData()
                                .add(new XYChart.Data<String, Number>(dataPoint.getEntity(), dataPoint.getYear()));
                    }
                }

                // Add the series to the bar chart
                barChart.getData().add(series);
            }
        }
    }
