package ru.t_systems.demail.dao.message;

import java.util.List;
import java.util.Set;

import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;


public interface MessageStatusDAO {
	
	public MessageStatuss getMessageStatus(int id);
	public void addStatusUser(MessageStatuss messageStatus);
	public void addStatusUser(Set<MessageStatuss> messageStatus);
	public List<MessageStatuss> getMessageStatussByAccount(List<Account> accounts);
        public List<MessageStatuss> getMessageStatussSendByAccount(List<Account> accounts);
}
