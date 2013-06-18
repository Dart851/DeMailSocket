package ru.t_systems.demail.dao.message;

import java.util.List;
import ru.t_systems.demail.model.message.Label;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;



public interface LabelDAO {
	
	public Message getLabel(int id);
	public void addLabel(Message message);
	public void deleteLabel(Label label);		
        public void updateLabel(Label label);
	
}
