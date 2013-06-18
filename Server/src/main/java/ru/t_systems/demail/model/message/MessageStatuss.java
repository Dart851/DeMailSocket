package ru.t_systems.demail.model.message;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.socket.dto.message.MessageStatussDTO;

@Entity
@Table(name = "status")
public class MessageStatuss {
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne//(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;
    
    @ManyToOne
    //@JoinColumn(name = "account_sender_id")
    @JoinTable(name="send_status")
    private Account acountsSender;
    
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Message message;
    @ManyToOne//(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Label label;
    @Column(name = "send_date")
    private Date sendDate = new Date();
    private Boolean isRead;
    private Boolean isDeleted;
    
    public void setDate(Date date) {
        this.sendDate = date;
    }
    
    public Date getDate() {
        return sendDate;
    }
    
    public Label getLabel() {
        return label;
    }
    
    public void setLabel(Label label) {
        this.label = label;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Message getMessage() {
        return message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }
    
    public Account getAcountsSender() {
        return acountsSender;
    }
    
    public void setAcountsSender(Account acountsSender) {
        this.acountsSender = acountsSender;
    }
    
    public Account getAcounts() {
        return account;
    }
    
    public void setAcounts(Account acounts) {
        this.account = acounts;
    }
    
    public MessageStatuss() {
        this.isRead = false;
        this.isDeleted = false;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Boolean getIsRead() {
        return isRead;
    }
    
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
    
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public MessageStatussDTO toMessageStatusDTO() {
        MessageStatussDTO status = new MessageStatussDTO();
        status.setIsDeleted(isDeleted);
        status.setIsRead(isRead);
        status.setDate(sendDate);
        status.setAcountsSender(acountsSender.toAccountDTO());
        status.setAccount(account.toAccountDTO());
        status.setId(id);
        status.setMessage(message.toMessageDTO());
        return status;
    }
}
