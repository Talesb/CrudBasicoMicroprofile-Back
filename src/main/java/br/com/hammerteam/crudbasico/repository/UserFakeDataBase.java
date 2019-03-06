package br.com.hammerteam.crudbasico.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.hammerteam.crudbasico.business.model.User;

public class UserFakeDataBase {

	private static List<User> registeredUsers;

	public static List<User> getRegisteredUsers() {

		if (registeredUsers == null) {
			registeredUsers = new ArrayList<User>();
			User user1 = new User("Jhon Doe", "jhonedoe@email.com", "pass", Arrays.asList("user", "protected"),"BRA");
			User user2 = new User("Carl Jhonson", "carl@email.com", "pass2", Arrays.asList("user", "protected"),"EUA");
			User user3 = new User("Tommy Vercetti", "tommy@email.com", "pass3", Arrays.asList("user", "protected"),"JPN");
			registeredUsers.add(user1);
			registeredUsers.add(user2);
			registeredUsers.add(user3);
		}

		return registeredUsers;
	}

}
