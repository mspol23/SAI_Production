package main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.persistence.CreateConnection;

public class ComboBoxListsDAO {

	public List<String> findDistinctOrigins() {

		String sql = "SELECT DISTINCT apolodata.origin FROM apolodata ORDER BY apolodata.origin";

		List<String> response = new ArrayList<>();

		try (Connection conn = CreateConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				response.add(rs.getString("origin"));
			}

		} catch (SQLException e) {
			System.err.println("Falha ao buscar listagem de origens: " + e.getMessage());
		}
		return response;
	}

	public List<String> findDistinctTypes() {

		String sql = "SELECT DISTINCT apolodata.type FROM apolodata ORDER BY apolodata.type";

		List<String> response = new ArrayList<>();

		try (Connection conn = CreateConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				response.add(rs.getString("type"));
			}

		} catch (SQLException e) {
			System.err.println("Falha ao buscar listagem de tipos de documentos: " + e.getMessage());
		}
		return response;
	}
}
