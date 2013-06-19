package ru.t_systems.demail.dao.message;

import java.util.List;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;



public interface MessageDAO {
	
	public Message getMessage(int id);
	public void addMessage(Message message);
	public List<Message> getMessageByStatus(List<MessageStatuss> status);
        public Message getMessageByStatusId(MessageStatuss status);
        public void updateMessage(Message message);
	
}
