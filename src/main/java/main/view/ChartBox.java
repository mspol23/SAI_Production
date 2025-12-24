package main.view;

import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ChartBox extends StackPane {

	private static final ChartBox instance = new ChartBox();
	private HBox chartContainer = new HBox(5);

	private ChartBox() {
		setAlignment(Pos.TOP_LEFT);
		getChildren().add(chartContainer);
		chartContainer.setMinHeight(400);
	}

	public static ChartBox getInstance() {
		return instance;
	}

	public boolean isChartContainerEmpty() {
		return chartContainer.getChildren().isEmpty();
	}

	public void addChart(BarChart<String, Number> newChart) {
		chartContainer.getChildren().add(newChart);
	}

	public void addChart(PieChart pieChart) {
		chartContainer.getChildren().add(pieChart);
	}

	public void clearBox() {
//		System.out.println(chartBox.getHeight());
		chartContainer.getChildren().clear();
	}

	public void removeLast() {
		if (!chartContainer.getChildren().isEmpty()) {
			chartContainer.getChildren()
				.remove(chartContainer.getChildren().size() - 1);
		}
	}
}
