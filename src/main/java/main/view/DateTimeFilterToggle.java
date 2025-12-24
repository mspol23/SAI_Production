package main.view;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.service.DateTimeFilterService;

public class DateTimeFilterToggle {
	
	private DateTimeFilterService dateTimeFilterService = new DateTimeFilterService();
	
	private HBox box = new HBox(10);
	private HBox box2 = new HBox(5);
	private Label label = new Label("Adicionar filtro: ");
	private CheckBox dateFilter = new CheckBox("Por Data");
	private VBox dateBoxes = new VBox(5);
	private ComboBox<String> initDate = new ComboBox<>();
	private ComboBox<String> finalDate = new ComboBox<>();
	
	DateTimeFilterToggle() {
		box.getChildren().addAll(label, box2);
		box.setMinHeight(56);
		box.setAlignment(Pos.CENTER_LEFT);
		box2.getChildren().addAll(dateFilter);
		box2.setAlignment(Pos.CENTER_LEFT);
		dateBoxes.getChildren().addAll(initDate,finalDate);
		label.setFont(new Font(14));
		label.setStyle("-fx-font-weight: bold;");
		
		initDate.getItems().addAll(
				dateTimeFilterService.getDateTimeList());
		finalDate.getItems().addAll(
				dateTimeFilterService.getDateTimeList());
		dateFilter.setOnAction(_ -> {
			if(dateFilter.isSelected()) {
				box.getChildren().add(dateBoxes);
				dateTimeFilterService.setFilterOn();
				System.out.println("Filtro ativado: " + dateFilter.isSelected());
			} else {
				box.getChildren().remove(dateBoxes);
				dateTimeFilterService.setFilterOff();
				System.out.println("Filtro ativado: " + dateFilter.isSelected());
			}
		});
		
		initDate.setOnAction(_ -> {
			dateTimeFilterService.setInitialDateTime(initDate.getValue());
		});
		
		finalDate.setOnAction(_ -> {
			dateTimeFilterService.setFinalDateTime(finalDate.getValue());
		});
		
	} // close Constructor.
	
	public HBox getToggleVBox() {
		return box;
	}
	
	protected ComboBox<String> getInitialDate(){
		return initDate;
	}
	
	protected ComboBox<String> getFinalDate(){
		return finalDate;
	}
}
