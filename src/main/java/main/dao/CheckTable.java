package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.persistence.CreateConnection;

public class CheckTable {

	public static boolean hasTables() {

		String[] tables = { "users", "usernames", "apolodata" };

		String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name = ?";

		try (Connection conn = CreateConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			for (String tableName : tables) {

				pstmt.setString(1, tableName);

				try (ResultSet rs = pstmt.executeQuery()) {

					if (!rs.next()) {
						System.out.println("false 1");
						return false;
					}

				}

			}

			System.out.println("true");
			return true;

		} catch (SQLException e) {
			System.err.println("Falha na checagem do banco de dados na classe: " + e.getMessage());
			System.out.println("false 2");
			return false;
		}
	}
}
