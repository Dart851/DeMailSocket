package ru.t_systems.demail.socket.dto.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MessageDTO implements Serializable {

    private Integer id;
    private String body;
    private Date timeStamp = new Date();
    private List<MessageStatussDTO> status;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<MessageStatussDTO> getStatus() {
        return status;
    }

    public void setStatus(List<MessageStatussDTO> status) {
        this.status = status;
    }
}