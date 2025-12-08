package main.service;

import java.util.List;

import main.dao.ComboBoxListsDAO;
import main.dao.ProductionDAO;
import main.entities.DataToChart;

public class ComboBoxTypeService {

	private final ComboBoxListsDAO comboBoxListsDAO = new ComboBoxListsDAO();
	private final ProductionDAO productionDAO = new ProductionDAO();

	public List<String> getTypeList() {

		return comboBoxListsDAO.findDistinctTypes();

	}

	public List<DataToChart> getUsersProductionByType(String type) {

		return productionDAO.findAllUserProductionByDocType(type);
	}

}
