package main.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractDataFromFile {

	private static String apolo_data_path = null;
	private static String users_path = null;
//	private static final String FILE_PATH = Paths.get("Resources", "table.csv").toAbsolutePath().toString();
//	private static final String USERNAMES_FILE_PATH = Paths.get("Resources", "efetivo.csv").toAbsolutePath().toString();
	private static List<List<String>> fileData = new ArrayList<>();
	private static List<List<String>> staffData = new ArrayList<>();

	public static List<List<String>> getTableData() {
		if (getApoloDataPath() != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(getApoloDataPath()));) {

				br.lines().forEach(line -> {

					int s = (int) line.charAt(0);
					System.out.print(s + " - ");

					String cleanLine = line;
					if (line.charAt(0) == '\ufeff') {
						cleanLine = line.replace("\ufeff", "");
					}

					String[] splitLine = cleanLine.split(";");
					List<String> lineAsList = new ArrayList<>(Arrays.asList(splitLine));

					for (int i = 0; i < 8; i++) {
						try {
							lineAsList.add(splitLine[i]);
						} catch (IndexOutOfBoundsException e) {
							System.out
									.println("Index inexistente. String vazia " + "será adicionadad ao fim da lista.");
							lineAsList.add("");
						}
					}

					fileData.add(lineAsList);

				});

				Long registryCount = fileData.stream().count();
				System.out.println("Lista criada com sucesso.");
				System.out.println("Número de registros contidos na tabela: " + registryCount);

			} catch (FileNotFoundException e) {
				System.err.println("Arquivo não encontrado." + e.getMessage());
				fileData = null;
			} catch (IOException e1) {
				System.err.println("Erro na operação com o arquivo. " + e1.getMessage());
				fileData = null;
			} catch (NullPointerException e2) {
				System.err.println("Array não encontrado." + e2.getMessage());
				fileData = null;
			}
		}
		return fileData;
	}

	public static List<List<String>> getStaffData() {

		if (getUsersPath() != null) {
			System.out.println("Iniciando a leitura do arquivo com os nomes dos usuários.");
			try (BufferedReader br = new BufferedReader(new FileReader(getUsersPath()))) {

				br.lines().forEach(line -> {

					if (line.charAt(0) != '#') {
						List<String> lineAsList = new ArrayList<>(Arrays.asList(line.split(";")));
						staffData.add(lineAsList);
					}
				});

				System.out.println("Lista com dados dos usuários e postos de trabalho criada.");

			} catch (FileNotFoundException e) {
				System.err.println("Arquivo não encontrado: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Exceção na entrada/saída do arquivo: " + e.getMessage());
			}
		}
		return staffData;
	}

	public static String getApoloDataPath() {
		return apolo_data_path;
	}

	public static String getUsersPath() {
		return users_path;
	}

	public static void setApoloDataPath(String dataPath) {
		apolo_data_path = dataPath;
	}

	public static void setUsernamePath(String usernamePath) {
		users_path = usernamePath;
	}

}
