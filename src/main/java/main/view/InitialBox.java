package main.view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.dao.CheckTable;
import main.entities.DataToChart;
import main.service.CreatePieChartService;
import main.service.InitialBoxService;

public class InitialBox extends VBox {

	private InitialBoxService initialBoxService = new InitialBoxService();
	private CreatePieChartService createPieChartService = new CreatePieChartService();
	private DateTimeFilterToggle dateTimeFilter = new DateTimeFilterToggle();


	private HBox buttonBox = new HBox(8);
	private Label label = new Label("Escolha a configuração dos dados para gerar o gráfico:");
	private Button btnPerUser = new Button("Por Usuário");
	private Button btnPerOrigin = new Button("Por Origem do Dado");
	private Button btnPerType = new Button("Por Tipo de Produção");
	private Button btnTotal = new Button("Total Geral");
	private Button btnTotalDocType = new Button("Total por Tipo");
	private Button btnTotalOrigin = new Button("Total por Origem");
	private ComboBoxContainer containerComboBox = ComboBoxContainer.getInstance();
	private ChartBox chartBox = ChartBox.getInstance();

	public InitialBox() {
		label.setStyle("-fx-font-weight: bold;");
		getChildren().addAll(
				label,
				dateTimeFilter.getToggleVBox(),
				buttonBox, 
				containerComboBox);
		setSpacing(8);
		setPadding(new Insets(8, 10, 8, 10));
		buttonBox.getChildren().addAll(
				btnPerUser, 
				btnPerOrigin, 
				btnPerType, 
				btnTotal,
				btnTotalDocType, 
				btnTotalOrigin);
		configButton();
		configLabel();
		if (CheckTable.hasTables()) {
			btnTotal.fire();
		} else {
			Alert alertBox = new Alert(AlertType.ERROR,
					"Tabelas não encontradas. " + "Clique no botão ATUALIZAR BANCO DE DADOS (tela principal).",
					ButtonType.CLOSE);
			alertBox.showAndWait();

			if (CheckTable.hasTables()) {
				btnTotal.fire();
			}
		}
	}

	public Button getBtnTotal() {
		return btnTotal;
	}

	private void configButton() {
		buttonBox.getChildren().stream().filter(child -> child instanceof Button).map(c -> (Button) c).forEach(c -> {
			c.setPadding(new Insets(5, 8, 5, 8));
			c.setFont(new Font("Sans Serif", 12));
		});

		btnPerUser.setOnAction(_ -> {
			containerComboBox.getChildren().clear();
			containerComboBox.getChildren().add(new ComboBoxByUser());
		});

		btnPerType.setOnAction(_ -> {
			containerComboBox.getChildren().clear();
			containerComboBox.getChildren().add(new ComboBoxType());
		});

		btnPerOrigin.setOnAction(_ -> {
			containerComboBox.getChildren().clear();
			containerComboBox.getChildren().add(new ComboBoxOrgin());
		});

		btnTotal.setOnAction(_ -> {

			// CRIAR BLOQUEIO PARA BOTÕES PARA GERAR OS GRÁFICOS.

			containerComboBox.getChildren().clear();
			String title = "Produção Total";
			System.out.println(initialBoxService.getTotalDataList());
			CreateBarChart<DataToChart> chart = new CreateBarChart<>(initialBoxService.getTotalDataList(), "Usuários",
					"Quantidade de Documentos", title);
			chartBox.addChart(chart.getBarChart());
		});
		
		btnTotalDocType.setOnAction(_ -> {
			System.out.println("Button 'btnTotalDocType' clicked.");
			containerComboBox.getChildren().clear();
			List<DataToChart> list = createPieChartService.getTotalFromEachType();
			CreatePieChart createPieChart = CreatePieChart.createInstance(list);
			PieChart pieChart = createPieChart.getPieChart();
			pieChart.setTitle("Produção por Tipo de Documento");
			chartBox.addChart(pieChart);
			
		});
		
		btnTotalOrigin.setOnAction(_ -> {
			System.out.println("Button 'btnTotalOrigin' clicked.");
			containerComboBox.getChildren().clear();
			List<DataToChart> list = createPieChartService.getTotalFromEachOrigin();
			CreatePieChart createPieChart = CreatePieChart.createInstance(list);
			PieChart pieChart = createPieChart.getPieChart();
			pieChart.setTitle("Produção por Origem");
			chartBox.addChart(pieChart);
		});
	}

	private void configLabel() {
		label.setFont(new Font("Sans Serif", 14));
	}
}
