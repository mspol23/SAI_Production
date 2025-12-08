package main.view;

import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChartBox extends VBox {

	private static final ChartBox instance = new ChartBox();
	private HBox box = new HBox(8);

	private ChartBox() {
		getChildren().add(box);
		setMinHeight(360);
		setAlignment(Pos.CENTER);
	}

	public boolean boxIsEmpty() {
		return box.getChildren().isEmpty();
	}

	public static ChartBox getInstance() {
		return instance;
	}

	public void addChart(BarChart<String, Number> newChart) {
		box.getChildren().add(newChart);
	}

	public void clearBox() {
//		System.out.println(chartBox.getHeight());
		box.getChildren().clear();
	}

	public void removeLast() {
		if(!box.getChildren().isEmpty()) {
			box.getChildren().remove(box.getChildren().size() - 1);
		}
	}
}
