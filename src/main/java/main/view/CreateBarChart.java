package main.view;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import main.entities.FormatToChart;

public class CreateBarChart<T extends FormatToChart> {

	private CategoryAxis xAxis = new CategoryAxis();
	private NumberAxis yAxis = new NumberAxis();
	private BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

	public CreateBarChart(List<T> list, String xLabel, String yLabel, String title) {
		createSeries(list, xLabel, yLabel, title);
		chart.setLegendVisible(false);
		yAxis.setAutoRanging(false);

	}

	public BarChart<String, Number> getBarChart() {
		return chart;
	}

	/**
	 * <p>
	 * Criar gráfico.
	 * 
	 * @param list
	 * @param xLabel
	 * @param yLabel
	 * @param title
	 */
	public void createSeries(List<T> list, String xLabel, String yLabel, String title) {

		if (list != null && !list.isEmpty()) {

			xAxis.setLabel(xLabel);
			yAxis.setLabel(yLabel);
			chart.setTitle(title);
			XYChart.Series<String, Number> series = new XYChart.Series<>();

			Double maxY = list.stream().mapToDouble(i -> i.getAmount().doubleValue()).max().getAsDouble();
			System.out.println("Valor máximo: " + maxY);
			System.out.println(list);

			list.forEach(item -> {
				XYChart.Data<String, Number> data = new XYChart.Data<>(item.getCategory(), item.getAmount());

				setYAxisValueAboveBar(data, item, maxY);

				series.getData().add(data);
			});

			chart.getData().add(series);
		}
	}

	private void setYAxisValueAboveBar(XYChart.Data<String, Number> data, T item, Double maxValue) {

		data.nodeProperty().addListener((_, _, newData) -> {

			Text amountText = new Text(String.valueOf(item.getAmount()));

			newData.parentProperty().addListener((_, _, newValue) -> {

				if (newValue != null && newValue instanceof Group) {
					((Group) newValue).getChildren().add(amountText);
				}

			});

			newData.boundsInParentProperty().addListener((_, _, newBound) -> {
				amountText.setLayoutX(
						newBound.getMinX() + newBound.getWidth() / 2 - amountText.getBoundsInLocal().getWidth() / 2);
				amountText.setLayoutY(newBound.getMinY() - 5);

				yAxis.setUpperBound(maxValue + 10);
			});
		});
	}

}
