package main.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import main.entities.DataToChart;

public class CreatePieChart {

	private ObservableList<PieChart.Data> pieDataList = FXCollections.observableArrayList();
	private PieChart pieChart = new PieChart();
	
	private CreatePieChart(List<DataToChart> list) {
		addToList(list);
		pieChart.getData().addAll(pieDataList);
		pieChart.setLegendVisible(true);
		pieChart.setLabelsVisible(false);
		pieChart.setLegendSide(Side.BOTTOM);
	}
	
	public static CreatePieChart createInstance(List<DataToChart> list) {
		return new CreatePieChart(list);
	}
	
	public PieChart getPieChart() {
		return pieChart;
	}
	
	public ObservableList<PieChart.Data> getPieDataList(){
		return pieDataList;
	}
	
	public void addToList(String category, double num) {
		pieDataList.add(new PieChart.Data(category, num));
	}
	
	public void addToList(List<DataToChart> list) {
		for(DataToChart data : list) {
			pieDataList.add(new PieChart.Data(
					data.getCategory(), (double) data.getAmount()));
		}
	}
}
