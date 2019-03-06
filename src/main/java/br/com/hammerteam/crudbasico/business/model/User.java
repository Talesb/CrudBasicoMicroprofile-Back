package br.com.hammerteam.crudbasico.business.model;

import java.io.Serializable;
import java.util.List;

import br.com.hammerteam.crudbasico.business.dto.UserDTO;

public class User implements Serializable {

	private long id;
	private String name;
	private String email;
	private String password;
	private List<String> groups;
	private String nationality;

	public User(String name, String email, String password, List<String> groups, String nationality) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.groups = groups;
		this.nationality = nationality;
	}

	public User(String name, String email, String password, List<String> groups) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.groups = groups;
	}
	
 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public UserDTO getDTO() {
		UserDTO user = new UserDTO();
		user.setEmail(this.getEmail());
		user.setPassword(this.getPassword());
		return user;
	}

}
