package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.entities.DataToChart;

public class CreatePieChartService {

	private ProductionDAO productionDAO = new ProductionDAO();

	public List<DataToChart> getTotalFromEachType() {

		List<DataToChart> dataList = productionDAO.findTotalOfDocumentTypes();

		double totalOfDocs = dataList.stream().filter(d -> d.getAmount() instanceof Number)
				.mapToDouble(d -> (double) d.getAmount()).sum();

		dataList.forEach(i -> {
			if (i.getAmount() instanceof Number) {
				String percent = getPercentageAsText((double) i.getAmount(), totalOfDocs);
				String newCategoryName = i.getCategory() + " (" + percent + "%)";
				i.setCategory(newCategoryName);
			}
		});

		System.out.println("Total de documentos por tipo para o gráfico de pizza (gráfico pizza).");
		System.out.println(dataList.toString());
		return dataList;
	}

	public List<DataToChart> getTotalFromEachOrigin() {

		List<DataToChart> dataList = productionDAO.findTotalOfDataOrigin();

		double totalOfDocs = dataList.stream().filter(d -> d.getAmount() instanceof Number)
				.mapToDouble(d -> (double) d.getAmount()).sum();

		dataList.forEach(i -> {
			if (i.getAmount() instanceof Number) {
				String percent = getPercentageAsText((double) i.getAmount(), totalOfDocs);
				String newCategoryName = i.getCategory() + " (" + percent + "%)";
				i.setCategory(newCategoryName);
			}
		});

		System.out.println("Total de documentos por origem -> gráfico de pizza (gráfico pizza).");
		System.out.println(dataList.toString());
		return dataList;
	}

	private String getPercentageAsText(double value, double total) {
		return String.format("%.2f", (value / total * 100));
	}

}
