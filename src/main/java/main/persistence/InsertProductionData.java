package main.persistence;

import java.util.List;

import main.dao.ProductionDAO;
import main.entities.Production;
import main.service.ExtractDataFromFile;

public class InsertProductionData {

	private static ProductionDAO productionDao = new ProductionDAO();

	public static void insertData() {

		List<List<String>> tableDataList = ExtractDataFromFile.getTableData();

		tableDataList.forEach(lineItems -> {
			
			Production prod = new Production();
			prod.setType(lineItems.get(0));
			prod.setUsername(lineItems.get(1));
			prod.setNum(lineItems.get(2));
			prod.setDateTime(lineItems.get(3));
			prod.setSubject(lineItems.get(4));
			prod.setAssigned(lineItems.get(5));
			prod.setStatus(lineItems.get(6));
			prod.setOrigin(lineItems.get(7));
			
			System.out.println("Registro: " + prod.getDateTime());
			
			Production.addDataToFullList(prod);

			productionDao.insertData(prod);

		});

		System.out.println("Dados inseridos com sucesso.");
		System.out.println("Criação da tabela de produção encerrada.");
	}
}
