package ru.t_systems.demail.model.message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.t_systems.demail.dao.message.MessageDAO;
import ru.t_systems.demail.socket.dto.message.MessageDTO;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private Integer id;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    private Date timeStamp = new Date();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private List<MessageStatuss> messageStatus;
    
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<MessageStatuss> getStatus() {
        return messageStatus;
    }

    public void setStatus(List<MessageStatuss> status) {
        this.messageStatus = status;
    }

    public MessageDTO toMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setBody(body);
        messageDTO.setId(id);
        messageDTO.setTimeStamp(timeStamp);
        messageDTO.setTitle(title);
        return messageDTO;
    }
}