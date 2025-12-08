package main.persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class CreateTableStructure {

	public static void create() {
		try (Connection conn = CreateConnection.getConnection()) {
			if (conn != null) {
				try (Statement stmt = conn.createStatement()) {

					stmt.execute("DROP TABLE IF EXISTS apolodata;");
					stmt.execute("DROP TABLE IF EXISTS users;");
					stmt.execute("DROP TABLE IF EXISTS usernames;");

					System.out.println("Tabelas excluídas.");

					stmt.execute("CREATE TABLE IF NOT EXISTS users (" + "id TEXT PRIMARY KEY,"
							+ "user TEXT NOT NULL UNIQUE);");

					stmt.execute("CREATE TABLE IF NOT EXISTS usernames (" + "id TEXT PRIMARY KEY,"
							+ "username TEXT NOT NULL UNIQUE," + "user_id TEXT,"
							+ "FOREIGN KEY (user_id) REFERENCES users(user));");

					stmt.execute("CREATE TABLE IF NOT EXISTS apolodata (" + "id TEXT PRIMARY KEY,"
							+ "type TEXT NOT NULL," + "username TEXT NOT NULL," + "num TEXT," + "date_time TEXT,"
							+ "subject TEXT," + "assigned TEXT," + "status TEXT," + "origin TEXT,"
							+ "FOREIGN KEY (username) REFERENCES usernames(username));");

				}
				System.out.println("Tabela criada com sucesso.");
			}
			System.out.println("Conexão é NULA.");
		} catch (SQLException e) {
			System.err.println("Erro ao criar o statement: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// CONTINUAR A INICIALIZAÇÃO COM VERIFICAÇÃO DA EXISTÊNCIA DA TABELA.

	public static boolean checkTables() {

		boolean hasTable = true;

		try (Connection conn = CreateConnection.getConnection()) {
			DatabaseMetaData dbmeta = conn.getMetaData();
			ResultSet rs = dbmeta.getTables(null, null, "users", null);
			if (!rs.next()) {
				hasTable = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hasTable;
	}

}
