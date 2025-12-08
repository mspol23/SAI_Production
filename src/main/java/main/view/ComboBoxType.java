package main.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import main.entities.DataToChart;
import main.service.ComboBoxTypeService;

public class ComboBoxType extends HBox {

	private final ComboBoxTypeService service = new ComboBoxTypeService();

	private ComboBox<String> comboBoxType = new ComboBox<>();
	private Button btn = new Button("Gerar");

	public ComboBoxType() {
		getChildren().addAll(comboBoxType, btn);
		setSpacing(8);
		setPadding(new Insets(8, 10, 8, 10));
		setItems();
		setBtn();
	}

	public void resetChilden() {
		getChildren().clear();
	}

	private void setItems() {
		comboBoxType.getItems().addAll(service.getTypeList());
	}

	private void setBtn() {

		btn.setOnAction(_ -> {
			String titlePart = comboBoxType.getValue();
			String title = "Total de " + titlePart + " Produzidos por Usuário.";
			List<DataToChart> data = service.getUsersProductionByType(comboBoxType.getValue());
			CreateBarChart<DataToChart> chart = new CreateBarChart<>(data, "Usuários", "Produção", title);
			ChartBox.getInstance().addChart(chart.getBarChart());
		});
	}
}
