package br.com.hammerteam.crudbasico.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;

import br.com.hammerteam.crudbasico.business.model.User;

@RequestScoped
public class UserRepository {

	public User findById(long id) {
		Optional<User> foundUser = UserFakeDataBase.getRegisteredUsers().stream().filter(user -> id == user.getId())
				.findFirst();
		if (foundUser.isPresent()) {
			return foundUser.get();
		}
		return null;
	}

	public User findByEmail(String email) {
		Optional<User> foundUser = UserFakeDataBase.getRegisteredUsers().stream()
				.filter(user -> email.equals(user.getEmail())).findFirst();
		if (foundUser.isPresent()) {
			return foundUser.get();
		}
		return null;
	}

	public List<User> findAll() {
		return UserFakeDataBase.getRegisteredUsers();
	}

	public long save(User user) {
		long lastId = UserFakeDataBase.getRegisteredUsers().size();
		user.setId(lastId + 1);
		UserFakeDataBase.getRegisteredUsers().add(user);
		return user.getId();
	}

}
