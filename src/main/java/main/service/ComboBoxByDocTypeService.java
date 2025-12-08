package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.entities.DataToChart;

public class ComboBoxByDocTypeService {

	private ProductionDAO productionDAO = new ProductionDAO();

	public List<DataToChart> handleCreateChartButton(String user) {
		return productionDAO.findIndividualDocTypeProduction(user);
	}

}
