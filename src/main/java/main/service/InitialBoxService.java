package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.entities.DataToChart;
import main.entities.DateTimeFilter;

public class InitialBoxService {

	private final ProductionDAO productionDAO = new ProductionDAO();
	private final DateTimeFilter dateTimeFilter = new DateTimeFilter();

	public List<DataToChart> getTotalDataList() {
		
		System.out.println("Filtro de data completo: " + dateTimeFilter.isDateTimeValid());

		if(dateTimeFilter.isDateTimeValid()) {
			return productionDAO.findTotalProductionPerUser(dateTimeFilter);
		}
		
		return productionDAO.findTotalProductionPerUser();
	}
}
