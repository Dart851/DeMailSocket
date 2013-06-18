package ru.t_systems.demail.socket.dto;

import java.io.Serializable;
import java.util.Set;

import ru.t_systems.demail.socket.dto.message.LabelDTO;
import ru.t_systems.demail.socket.dto.message.MessageStatussDTO;

public class AccountDTO implements Serializable {

	private Integer id;

	private String accountName;

	private UserDTO user;

	private Set<MessageStatussDTO> status;

	private Set<MessageStatussDTO> statusSender;

	private Set<LabelDTO> label;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Set<MessageStatussDTO> getStatus() {
		return status;
	}

	public void setStatus(Set<MessageStatussDTO> status) {
		this.status = status;
	}

	public Set<MessageStatussDTO> getStatusSender() {
		return statusSender;
	}

	public void setStatusSender(Set<MessageStatussDTO> statusSender) {
		this.statusSender = statusSender;
	}

	public Set<LabelDTO> getLabel() {
		return label;
	}

	public void setLabel(Set<LabelDTO> label) {
		this.label = label;
	}

}
