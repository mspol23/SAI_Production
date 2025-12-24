package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.dao.UserDAO;
import main.entities.DataToChart;
import main.entities.DateTimeFilter;

public class ComboBoxByUserService {

	private ProductionDAO prodDao = new ProductionDAO();
	private DateTimeFilter dateTimeFilter = new DateTimeFilter();
	private UserDAO userDao = new UserDAO();

	public List<String> getNameList() {
		return userDao.findAllNames();
	}

	public List<DataToChart> getProductionPerOrigin(String name) {
		
		if(dateTimeFilter.isDateTimeValid()) {
			
			return prodDao.findIndividualOriginProduction(name, dateTimeFilter);
			
		}
		
		return prodDao.findIndividualOriginProduction(name);
	}

	public List<DataToChart> getProductionPerType(String name) {
		return prodDao.findIndividualDocTypeProduction(name);
	}

}
