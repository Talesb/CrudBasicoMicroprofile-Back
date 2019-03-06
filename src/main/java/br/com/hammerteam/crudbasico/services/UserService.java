package br.com.hammerteam.crudbasico.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.hammerteam.crudbasico.business.dto.UserDTO;
import br.com.hammerteam.crudbasico.business.model.User;
import br.com.hammerteam.crudbasico.repository.UserRepository;

@RequestScoped
public class UserService {

	@Inject
	private UserRepository repository;

	public long createUser(UserDTO dto) {
		User user = User.newUser(dto);
		return repository.save(user);
	}

	public UserDTO getUserById(long id) {
		User foundUser = repository.findById(id);
		return foundUser.getUserDTO();
	}

	public List<UserDTO> findAll() {
		List<User> users = repository.findAll();
		return users.stream()
				.map(user -> user.getUserDTO())
				.collect(Collectors.toList());
	}

}
