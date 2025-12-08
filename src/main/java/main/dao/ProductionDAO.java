package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.entities.DataToChart;
import main.entities.Production;
import main.persistence.CreateConnection;

public class ProductionDAO {

	public void insertData(Production prod) {

		String sql = "INSERT INTO apolodata VALUES(?,?,?,?,?,?,?,?,?)";

		try (Connection conn = CreateConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			System.out.println("Criando registro...");

			pstmt.setString(1, prod.getId().toString());
			pstmt.setString(2, prod.getType());
			pstmt.setString(3, prod.getUsername());
			pstmt.setString(4, prod.getNum());
			pstmt.setString(5, prod.getDateTimeAsString());
			pstmt.setString(6, prod.getSubject());
			pstmt.setString(7, prod.getAssigned());
			pstmt.setString(8, prod.getStatus());
			pstmt.setString(9, prod.getOrigin());

			pstmt.executeUpdate();

			System.out.println("Registro inserido com sucesso.");

		} catch (SQLException e) {
			System.err.println("Falha ao inserir o dado de produção na tabela: " + e.getMessage());
		}

		System.out.println("Conexão e Statement encerrados.");
	}

	public List<DataToChart> findIndividualOriginProduction(String user) {

		List<DataToChart> list = new ArrayList<>();

		if (user != null && !user.isBlank() && !user.isEmpty()) {

			System.out.println("Nome de usuário recebido para buscar origem do dado " + user.toUpperCase());

			String sql = "SELECT users.user as user, o.origin as origin, count(apolodata.username) as amount FROM users "
					+ "INNER JOIN usernames ON users.id = usernames.user_id " + "AND users.user = ? "
					+ "LEFT JOIN (SELECT DISTINCT origin FROM apolodata) AS o "
					+ "LEFT JOIN apolodata ON usernames.username = apolodata.username "
					+ "AND o.origin = apolodata.origin " + "GROUP BY users.user, o.origin " + "ORDER BY amount DESC";

			try (Connection conn = CreateConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, user);

				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						DataToChart dtc = new DataToChart();
						dtc.setCategory(rs.getString("origin"));
						dtc.setAmount(rs.getInt("amount"));
						list.add(dtc);
					}
					System.out.println("Lista de origens/usuário finalizada com sucesso.");
				}
			} catch (SQLException e) {
				System.err.println("Falha ao buscar produção por origem do dado do usuário " + user.toUpperCase() + ": "
						+ e.getMessage());
			}

			list.forEach(System.out::println);
		}

		System.out.println(list.toString());
		return list;
	}

	public List<DataToChart> findIndividualDocTypeProduction(String user) {
		List<DataToChart> resultList = new ArrayList<>();
		if (user != null && !user.isBlank() && !user.isEmpty()) {
			System.out.println("Nome de usuário recebido para buscar tipos de documentos: " + user.toUpperCase());

			String sql = "SELECT users.user as user, a.type as type, count(apolodata.username) as amount "
					+ "FROM users " + "INNER JOIN usernames ON users.id = usernames.user_id " + "AND users.user = ? "
					+ "LEFT JOIN (SELECT DISTINCT type FROM apolodata) AS a "
					+ "LEFT JOIN apolodata ON usernames.username = apolodata.username " + "AND a.type = apolodata.type "
					+ "GROUP BY users.user, a.type " + "ORDER BY amount DESC";

			try (Connection conn = CreateConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, user);

				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						DataToChart dtc = new DataToChart();
						dtc.setCategory(rs.getString("type"));
						dtc.setAmount(rs.getInt("amount"));
						resultList.add(dtc);
					}
					System.out.println("Lista de tipos/usuário finalizada com sucesso.");
				}

			} catch (SQLException e) {
				System.err.println("Falha ao buscar produção por tipo de documento do usuário " + user.toUpperCase()
						+ ": " + e.getMessage());
			}
		}
		return resultList;

	}

	public List<DataToChart> findTotalProductionPerUser() {

		String sql = "SELECT users.user as user, count(apolodata.username) as amount FROM users "
				+ "INNER JOIN usernames ON users.id = usernames.user_id "
				+ "LEFT JOIN apolodata ON usernames.username = apolodata.username " + "GROUP BY users.user "
				+ "ORDER BY amount DESC";

		List<DataToChart> resultList = new ArrayList<>();

		try (Connection conn = CreateConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {

				DataToChart dtc = new DataToChart();
				dtc.setCategory(rs.getString("user"));
				dtc.setAmount(rs.getInt("amount"));

				resultList.add(dtc);
			}

		} catch (SQLException e) {

			System.err.println("Falha ao buscar a produção total de cada usuário: " + e.getMessage());
		}

		return resultList;

	}

	public List<DataToChart> findAllUserProductionByOrigin(String origin) {
		String sql = "SELECT users.user, count(apolodata.origin) as amount "
				+ "FROM (SELECT DISTINCT origin FROM apolodata WHERE origin = ?) AS a " + "LEFT JOIN users "
				+ "INNER JOIN usernames ON users.id = usernames.user_id "
				+ "LEFT JOIN apolodata ON a.origin = apolodata.origin " + "AND usernames.username = apolodata.username "
				+ "GROUP BY a.origin, users.user " + "ORDER BY amount DESC";

		List<DataToChart> result = new ArrayList<DataToChart>();

		try (Connection conn = CreateConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, origin);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					DataToChart data = new DataToChart();
					data.setCategory(rs.getString("user"));
					data.setAmount(rs.getInt("amount"));
					result.add(data);
				}
			}
		} catch (SQLException e) {
			System.err.println("Falha ao buscar produção de cada usuário por origem: " + e.getMessage());
		}

		return result;
	}

	public List<DataToChart> findAllUserProductionByDocType(String docType) {

		String sql = "SELECT users.user, a.type, count(apolodata.type) as amount "
				+ "FROM (SELECT DISTINCT type FROM apolodata WHERE type = ?) AS a " + "LEFT JOIN users "
				+ "INNER JOIN usernames ON users.id = usernames.user_id "
				+ "LEFT JOIN apolodata ON a.type = apolodata.type " + "AND usernames.username = apolodata.username "
				+ "GROUP BY a.type, users.user " + "ORDER BY amount DESC";

		List<DataToChart> result = new ArrayList<DataToChart>();

		try (Connection conn = CreateConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, docType);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					DataToChart data = new DataToChart();
					data.setCategory(rs.getString("user"));
					data.setAmount(rs.getInt("amount"));
					result.add(data);
				}
			}
		} catch (SQLException e) {
			System.err.println("Falha ao buscar produção de cada usuário por tipo de documento: " + e.getMessage());
		}

		return result;

	}
}
