package ru.t_systems.demail.socket.dto.message;

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
    private Boolean isSpam;
    private Date date;

    public Boolean isSpam() {
        return isSpam;
    }

    public void setIsSpam(Boolean isSpam) {
        this.isSpam = isSpam;
    }

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
        if (this.getDate().equals(other.getDate())) {
            return 0;
        } else if (this.getDate().compareTo(other.getDate()) == 1) {
            return -1;
        } else {
            return 1;
        }



    }
}
