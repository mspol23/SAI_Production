package main.service;

import java.util.List;

import main.dao.ProductionDAO;
import main.entities.DataToChart;

public class InitialBoxService {

	private final ProductionDAO productionDAO = new ProductionDAO();

	public List<DataToChart> getTotalDataList() {

		System.out.println("Método para buscar a produção total chamado.");

		List<DataToChart> dtc = productionDAO.findTotalProductionPerUser();

		if (!dtc.isEmpty())
			System.out.println("Lista de produção total gerada com sucesso.");

		if (dtc.isEmpty())
			System.out.println("Falha ao criar lista de produtção total.");

		return dtc;
	}

}
