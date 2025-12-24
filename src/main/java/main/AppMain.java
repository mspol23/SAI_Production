package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.view.BottomBox;
import main.view.ChartBox;
import main.view.FileChoosePane;
import main.view.InitialBox;
import main.view.UpdateDatabaseButtonBox;

public class AppMain extends Application {

	@Override
	public void start(Stage stage) {

		VBox mainbox = new VBox(8);
		mainbox.setPadding(new Insets(10));

		Scene mainScene = new Scene(mainbox, 700, 750);

		FileChoosePane fileChoosePane = FileChoosePane.getInstance();
		ChartBox chartBox = ChartBox.getInstance();
		InitialBox initialBox = new InitialBox();
		BottomBox bottomBox = new BottomBox();
		UpdateDatabaseButtonBox updateDatabaseButtonBox = UpdateDatabaseButtonBox.getInstance();

		mainbox.getChildren().addAll(
				fileChoosePane, 
				updateDatabaseButtonBox, 
				initialBox, 
				chartBox, 
				bottomBox);

		stage.setScene(mainScene);
		stage.setMinHeight(630);
		stage.setMinWidth(550);

		stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
