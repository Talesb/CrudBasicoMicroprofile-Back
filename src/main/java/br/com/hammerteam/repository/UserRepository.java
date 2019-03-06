package br.com.hammerteam.repository;

import java.util.List;
import java.util.Optional;

import br.com.hammerteam.crudbasico.business.model.User;

public class UserRepository {

	public User findById(long id) {
		Optional<User> foundUser = UserFakeDataBase.getRegisteredUsers().stream().filter(user -> id == user.getId())
				.findFirst();
		if (foundUser.isPresent()) {
			return foundUser.get();
		}
		return null;
	}

	public List<User> findAll() {
		return UserFakeDataBase.getRegisteredUsers();
	}

	public boolean save(User user) {
		long lastId = UserFakeDataBase.getRegisteredUsers().size();
		user.setId(lastId + 1);
		UserFakeDataBase.getRegisteredUsers().add(user);
		return true;
	}

}
