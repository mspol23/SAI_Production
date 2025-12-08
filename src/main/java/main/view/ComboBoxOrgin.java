package main.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import main.entities.DataToChart;
import main.service.ComboBoxOriginService;

public class ComboBoxOrgin extends HBox {

	private final ComboBoxOriginService service = new ComboBoxOriginService();

	private ComboBox<String> comboBoxOrigin = new ComboBox<>();
	private Button btn = new Button("Gerar");

	public ComboBoxOrgin() {
		getChildren().addAll(comboBoxOrigin, btn);
		setSpacing(8);
		setPadding(new Insets(8, 10, 8, 10));
		setNameItems();
		setBtn();
	}

	public void resetClass() {
		getChildren().clear();
	}

	private void setNameItems() {
		comboBoxOrigin.getItems().addAll(service.getOriginList());
	}

	private void setBtn() {

		btn.setOnAction(_ -> {
			String titlePart = comboBoxOrigin.getValue();
			String title = "Total de " + titlePart + " Produzidos por Usuário.";
			List<DataToChart> data = service.getTotalPerUserByOrigin(comboBoxOrigin.getValue());
			CreateBarChart<DataToChart> chart = new CreateBarChart<>(data, "Usuários", "Produção", title);
			ChartBox.getInstance().addChart(chart.getBarChart());
		});
	}
}
