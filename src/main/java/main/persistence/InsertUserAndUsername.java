package main.persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.dao.UserDAO;
import main.dao.UsernameDAO;
import main.entities.User;
import main.entities.Username;
import main.service.ExtractDataFromFile;

public class InsertUserAndUsername {

	private static UserDAO userDao = new UserDAO();
	private static UsernameDAO usernameDao = new UsernameDAO();

	private static Set<String> namesSet = new HashSet<>();
	private static List<List<String>> efetivoList = ExtractDataFromFile.getStaffData();

	public static void insertData() {

		for (List<String> list : efetivoList) {
			namesSet.add(list.get(0));
		}

		System.out.println("Set de nomes criado: " + namesSet.toString());

		for (String name : namesSet) {
			User newUser = new User();
			newUser.setUser(name);
			userDao.createUser(newUser);
		}

		for (List<String> list : efetivoList) {
			System.out.println("Nome: " + list.get(0));
			System.out.println("Nome de usuário: " + list.get(1));
			Username username = new Username();
			username.setUsername(list.get(1));

			User user = userDao.findUserByName(list.get(0));
			System.out.println("Usuário encontrado: " + user.toString());
			username.setUser_id(user.getId().toString());
			usernameDao.createUsername(username);
		}

	}

}
