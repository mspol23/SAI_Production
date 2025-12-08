package main.view;

import java.io.File;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import main.service.ExtractDataFromFile;

public class FileChoosePane extends VBox {

	private static final FileChoosePane instance = new FileChoosePane();
	private Label label = new Label("Selecione os arquivos:");
	private TextField apoloDataPath = new TextField();
	private Button btn1 = new Button("Selecionar");
	private TextField usersDataPath = new TextField();
	private Button btn2 = new Button("Selecionar");
	private HBox apoloDataBox = new HBox(8);
	private HBox usersDataBox = new HBox(8);

	private FileChoosePane() {
		setSpacing(8);
		getChildren().addAll(label, apoloDataBox, usersDataBox);
		apoloDataBox.getChildren().addAll(apoloDataPath, btn1);
		usersDataBox.getChildren().addAll(usersDataPath, btn2);
		apoloDataPath.setText("Selecione a tabela com dados do Apolo.");
		usersDataPath.setText("Selecione a tabela com dados dos usuários.");
		defaultTextFieldConfig();
		btn1.setOnAction(_ -> {
			apoloDataPath.setText(getPath().replace("\\", "/"));
			ExtractDataFromFile.setApoloDataPath(apoloDataPath.getText());
		});
		btn2.setOnAction(_ -> {
			usersDataPath.setText(getPath().replace("\\", "/"));
			ExtractDataFromFile.setUsernamePath(usersDataPath.getText());
		});
	}

	public static FileChoosePane getInstance() {
		return instance;
	}

	private void defaultTextFieldConfig() {
		List<TextField> tfList = List.of(apoloDataPath, usersDataPath);
		tfList.forEach(tf -> {
			tf.setEditable(false);
			tf.setMinWidth(400);
		});
	}

	private String getPath() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Selecione o caminho:");
		fc.getExtensionFilters().add(new ExtensionFilter("Text File", "*.csv"));
		File selectedFile = fc.showOpenDialog(this.getScene().getWindow());
		if (selectedFile != null) {
			System.out.println("Path: " + selectedFile.getAbsolutePath().replace("\\", "/"));
			return selectedFile.getAbsolutePath();
		} else {
			return "Selecione um arquivo";
		}
	}
}
