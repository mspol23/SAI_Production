package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.entities.User;
import main.persistence.CreateConnection;

public class UserDAO {

	private final String NEW_USER_SQL = "INSERT INTO users VALUES (?,?)";
	private final String FIND_USER_BY_NAME = "SELECT * FROM users WHERE user = ?;";

	public void createUser(User newUser) {

		try (Connection conn = CreateConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(NEW_USER_SQL)) {

			pstmt.setString(1, newUser.getId().toString());
			pstmt.setString(2, newUser.getUser());

			pstmt.execute();

		} catch (SQLException e) {
			System.err.println("Erro ao inserir novo usuário: " + e.getMessage());
		}
	}

	public User findUserByName(String userName) {

		User user = new User();

		try (Connection conn = CreateConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_USER_BY_NAME)) {

			pstmt.setString(1, userName);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					user.setId(UUID.fromString(rs.getString("id")));
					System.out.println("Coluna 'id' encontrada e definida.");
					user.setUser(rs.getString("user"));
					System.out.println("Coluna 'user' encontrada e definida.");
				}
			}
		} catch (SQLException e) {
			System.err.println(getClass().getPackageName() + "Erro ao criar lista de usuários: " + e.getMessage());
		}
		System.out.println("Resultado: " + user);

		return user;
	}

	public User findUserById(String id) {

		String sql = "SELECT * FROM users WHERE users.id = ?";

		User user = new User();

		try (Connection conn = CreateConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					user.setId(UUID.fromString(rs.getString("id")));
					user.setUser(rs.getString("user"));
				}
			}
		} catch (SQLException e) {
			System.err.println("Busca de usuário por ID falhou. " + e.getMessage());
		}

		return user;
	}

	public List<String> findAllNames() {
		String sql = "SELECT user FROM users";
		List<String> userNames = new ArrayList<>();

		try (Connection conn = CreateConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				userNames.add(rs.getString("user"));
			}

		} catch (SQLException e) {
			System.err.println("Exception creating names list: " + e.getMessage());
		}

		return userNames;
	}

}
