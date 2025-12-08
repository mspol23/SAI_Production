package main.service;

import java.util.List;

import main.dao.ComboBoxListsDAO;
import main.dao.ProductionDAO;
import main.entities.DataToChart;

public class ComboBoxOriginService {

	private final ComboBoxListsDAO comboBoxListsDAO = new ComboBoxListsDAO();
	private final ProductionDAO productionDAO = new ProductionDAO();

	public List<String> getOriginList() {
		return comboBoxListsDAO.findDistinctOrigins();
	}

	public List<DataToChart> getTotalPerUserByOrigin(String origin) {
		return productionDAO.findAllUserProductionByOrigin(origin);
	}

}
