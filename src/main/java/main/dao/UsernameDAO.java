package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.entities.Username;
import main.persistence.CreateConnection;

public class UsernameDAO {

	public void createUsername(Username username) {

		final String createSql = "INSERT INTO usernames VALUES (?,?,?);";

		try (Connection conn = CreateConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(createSql)) {

			pstmt.setString(1, username.getId().toString());
			pstmt.setString(2, username.getUsername());
			pstmt.setString(3, username.getUser_id());

			pstmt.execute();

		} catch (SQLException e) {
			System.err.println("Erro ao criar Username: " + e.getMessage());
		}
	}

//	public String findByUsername(String username) {
//
//		String sql = "SELECT id FROM usernames WHERE username = ?";
//		String result = null;
//
//		try (Connection conn = CreateConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//			pstmt.setString(1, username);
//
//			try (ResultSet rs = pstmt.executeQuery()) {
//
//				while (rs.next()) {
//					result = rs.getString("id");
//				}
//
//			}
//
//		} catch (SQLException e) {
//			System.err.println("Falha na busca por 'username': " + e.getMessage());
//		}
//
//		return result;
//	}
}
