package ru.t_systems.demail.socket.dto.message;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;
import java.io.Serializable;
import java.util.Date;

import ru.t_systems.demail.socket.dto.AccountDTO;

public class MessageStatussDTO implements Serializable, Comparable<MessageStatussDTO> {

    private Integer id;
    private AccountDTO account;
    private AccountDTO acountsSender;
    private MessageDTO message;
    private LabelDTO label;
    private Boolean isRead;
    private Boolean isDeleted;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public AccountDTO getAcountsSender() {
        return acountsSender;
    }

    public void setAcountsSender(AccountDTO acountsSender) {
        this.acountsSender = acountsSender;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }

    public LabelDTO getLabel() {
        return label;
    }

    public void setLabel(LabelDTO label) {
        this.label = label;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int compareTo(MessageStatussDTO other) {
        return this.getDate().compareTo(other.getDate());


    }
}
