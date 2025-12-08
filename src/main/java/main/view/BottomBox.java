package main.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class BottomBox extends HBox {

	private Button clearCharts = new Button("Limpar Gráficos");
	private Button removeLast = new Button("Remover Último Gráfico");

	public BottomBox() {
		setSpacing(8);
		getChildren().addAll(clearCharts, removeLast);
		config();
		clearCharts.setOnAction(_ -> clearAllCharts());
		removeLast.setOnAction(_ -> removeLastChart());
	}

	private void config() {
		getChildren().forEach(btn -> {
			if (btn instanceof Button) {
				((Button) btn).setPadding(new Insets(5, 8, 5, 8));
				((Button) btn).setFont(new Font("Sans Serif", 12));
			}
		});
	}

	public static void clearAllCharts() {
		ChartBox.getInstance().clearBox();
	}

	public static void removeLastChart() {
		ChartBox.getInstance().removeLast();
	}
}
