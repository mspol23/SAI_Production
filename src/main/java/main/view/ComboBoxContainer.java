package main.view;

import javafx.scene.layout.HBox;

public class ComboBoxContainer extends HBox {

	private static final ComboBoxContainer instance = new ComboBoxContainer();

	private ComboBoxContainer() {
	}

	public static ComboBoxContainer getInstance() {
		return instance;
	}

	public static void add(HBox box) {
		getInstance().getChildren().add(box);
	}

	public static void remove() {
		getInstance().getChildren().clear();
	}
}
