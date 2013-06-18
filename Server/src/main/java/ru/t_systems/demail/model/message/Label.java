package ru.t_systems.demail.model.message;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.socket.dto.message.LabelDTO;

@Entity
@Table(name = "label")
public class Label {

	@Id
	@GeneratedValue
	private Integer id;
//cascade=CascadeType.ALL, 
	@ManyToOne(fetch=FetchType.EAGER)
	private Account account;
	
	private String name;
	
	@OneToMany//(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="label_id")
	private Set<MessageStatuss> status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MessageStatuss> getStatus() {
		return status;
	}

	public void setStatus(Set<MessageStatuss> status) {
		this.status = status;
	}

	public LabelDTO toLabelDTO(){
            LabelDTO label = new LabelDTO();
            label.setId(id);
            label.setName(name);
            label.setAccount(account.getAccountName());
            return label;
        }
                

	
}