package main.entities;

public class DateTimeFilter {
	
	private static String initialDateTime = null;
	private static String finalDateTime = null;
	
	public String getInitialDateTime() {
		return initialDateTime;
	}

	public void setInitialDateTime(String strDate) {
		initialDateTime = formatDateToDateBase(strDate);
	}

	public String getFinalDateTime() {
		return finalDateTime;
	}

	public void setFinalDateTime(String strDate) {
		finalDateTime = formatDateToDateBase(strDate);
	}
	
	public boolean isDateTimeValid() {
		if(initialDateTime != null && 
				!initialDateTime.isBlank() &&
				!initialDateTime.isEmpty() &&
				finalDateTime != null &&
				!finalDateTime.isBlank() &&
				!finalDateTime.isEmpty()) {
			
			return true;
			
		}
		
		return false;
	}

	private String formatDateToDateBase(String date) {
		String year = date.substring(6, 10);
		String month = date.substring(3, 5);
		String day = date.substring(0, 2);
		String fmtDateTime = year + "-" + month + "-" + day;
		
		System.out.println("Data formatada para o banco de dados: " + fmtDateTime);
		
		return fmtDateTime;
	}


}
