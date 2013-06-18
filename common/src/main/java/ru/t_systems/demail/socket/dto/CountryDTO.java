package ru.t_systems.demail.socket.dto;

import java.io.Serializable;
import java.util.Set;

public class CountryDTO implements Serializable {

	private Integer id;

	private String country;

	private Set<UserDTO> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}

}
