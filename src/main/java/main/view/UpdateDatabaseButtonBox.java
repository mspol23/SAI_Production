package main.view;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import main.persistence.CreateTableStructure;
import main.persistence.InsertProductionData;
import main.persistence.InsertUserAndUsername;
import main.service.ExtractDataFromFile;

public class UpdateDatabaseButtonBox extends HBox {

	private static final Button btn = new Button("ATUALIZAR BANCO DE DADOS");
	private static UpdateDatabaseButtonBox instance = new UpdateDatabaseButtonBox();

	private UpdateDatabaseButtonBox() {
		getChildren().add(btn);
		setSpacing(8);
		getChildren().forEach(btn -> {
			if (btn instanceof Button) {
				((Button) btn).setFont(new Font("Sans Serif", 16));
				((Button) btn).setPadding(new Insets(7, 10, 7, 10));

			}
		});
		btn.setOnAction(_ -> {
			addBtnListener();
		});
	}

	public static UpdateDatabaseButtonBox getInstance() {
		return instance;
	}

	public Button getButton() {
		return btn;
	}

	private void addBtnListener() {
		if (ExtractDataFromFile.getApoloDataPath() != null && ExtractDataFromFile.getUsersPath() != null
				&& ExtractDataFromFile.getApoloDataPath().matches(".*\\.csv$")
				&& ExtractDataFromFile.getUsersPath().matches(".*\\.csv$")) {

			btn.getScene().getRoot().setDisable(true);

			CreateTableStructure.create();
			InsertUserAndUsername.insertData();
			InsertProductionData.insertData();

			btn.getScene().getRoot().setDisable(false);
		} else {
			new Alert(AlertType.ERROR, "Caminho inválido!", ButtonType.CLOSE).showAndWait();
		}
	}
}
