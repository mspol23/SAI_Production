package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.dao.UserDAO;
import main.entities.DataToChart;

public class ComboBoxByUserService {

	ProductionDAO prodDao = new ProductionDAO();
	UserDAO userDao = new UserDAO();

	public List<String> getNameList() {
		return userDao.findAllNames();
	}

	public List<DataToChart> getProductionPerOrigin(String name) {
		return prodDao.findIndividualOriginProduction(name);
	}

	public List<DataToChart> getProductionPerType(String name) {
		return prodDao.findIndividualDocTypeProduction(name);
	}

}
