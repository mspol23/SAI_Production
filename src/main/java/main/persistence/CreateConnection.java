package main.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {

	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				String file = new File("data.db").toString();
				conn = DriverManager.getConnection("jdbc:sqlite:" + file);
				System.out.println("Nova conexão criada com sucesso.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao criar conexão: " + e.getMessage());
		}
		return conn;
	}
}
