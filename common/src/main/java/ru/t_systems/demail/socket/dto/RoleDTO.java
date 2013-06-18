package ru.t_systems.demail.socket.dto;

import java.io.Serializable;
import java.util.Set;

public class RoleDTO implements Serializable{

	private Integer id;

	private String role;

	private Set<UserDTO> userRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UserDTO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserDTO> userRoles) {
		this.userRoles = userRoles;
	}

}
