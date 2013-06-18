package ru.t_systems.demail.socket.dto.message;

import java.io.Serializable;
import java.util.Set;

import ru.t_systems.demail.socket.dto.AccountDTO;

public class LabelDTO implements Serializable {

	
	private Integer id;

	private String account;
	
	private String name;
	
	
	private Set<MessageStatussDTO> status;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<MessageStatussDTO> getStatus() {
		return status;
	}


	public void setStatus(Set<MessageStatussDTO> status) {
		this.status = status;
	}

	

	
}