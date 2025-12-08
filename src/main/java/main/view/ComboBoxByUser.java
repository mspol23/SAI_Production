package main.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import main.entities.DataToChart;
import main.service.ComboBoxByUserService;

public class ComboBoxByUser extends HBox {

	ComboBoxByUserService comboBoxByUserService = new ComboBoxByUserService();

	private ComboBox<String> selectUser = new ComboBox<>();
	private ComboBox<String> selectType = new ComboBox<>();
	private Button btn = new Button("Gerar");

	public ComboBoxByUser() {
		getChildren().addAll(selectUser, selectType, btn);
		setSpacing(8);
		setPadding(new Insets(8, 10, 8, 10));
		setBoxesItems();
		setBtn();
	}

	public void resetClass() {
		getChildren().clear();
	}

	private void setBoxesItems() {
		selectUser.getItems().addAll(comboBoxByUserService.getNameList());
		selectType.getItems().addAll("Tipo de Documento", "Origem do Dado");
	}

	private void setBtn() {
		btn.setOnAction(_ -> {
			List<DataToChart> dataToChart = null;
			String type = null;
			String amount = "Quantidade";
			String titlePart = null;
			if (selectType.getValue() != null && selectType.getValue().equals("Tipo de Documento")) {
				dataToChart = comboBoxByUserService.getProductionPerType(selectUser.getValue());
				titlePart = "Tipo de Documento.";
				type = "Documentos";
			}
			if (selectType.getValue() != null && selectType.getValue().equals("Origem do Dado")) {
				dataToChart = comboBoxByUserService.getProductionPerOrigin(selectUser.getValue());
				titlePart = "Origem do Dado.";
				type = "Origens";
			}

			String title = "Produção por " + titlePart;

			CreateBarChart<DataToChart> createChart = new CreateBarChart<>(dataToChart, type, amount, title);
			ChartBox.getInstance().addChart(createChart.getBarChart());
		});
	}
}
