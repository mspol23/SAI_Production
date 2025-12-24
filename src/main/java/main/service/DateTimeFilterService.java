package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.entities.DateTimeFilter;

public class DateTimeFilterService {
	
	private ProductionDAO productionDAO = new ProductionDAO();
	private DateTimeFilter dateTimeFilter = new DateTimeFilter();
	
	private boolean toggleFilter = false;
	
	public DateTimeFilterService() {
	}
	
	public void setFilterOn() {
		toggleFilter = true;}
	
	public void setFilterOff() {
		toggleFilter = false;
	}
	
	public boolean isFilterOn() {
		return toggleFilter;
	}
	
	public List<String> getDateTimeList(){
		return productionDAO.findAllDistinctDate();
	}
	
	public void setInitialDateTime(String newDate) {
		dateTimeFilter.setInitialDateTime(newDate);
	}
	
	public void setFinalDateTime(String newDate) {
		dateTimeFilter.setFinalDateTime(newDate);
	}
}
