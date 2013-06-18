package ru.t_systems.demail.dao.message;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.sever.ServerInit;

public class MessageDAOImpl implements MessageDAO {

    private Session getCurrentSession() {
        return ServerInit.getSession().getCurrentSession();
    }

    public Message getMessage(int id) {
        Message message = (Message) getCurrentSession().get(Message.class, id);
        return message;
    }

    public void addMessage(Message message) {
        getCurrentSession().beginTransaction();
        getCurrentSession().save(message);
        getCurrentSession().getTransaction().commit();

    }

    public List<Message> getMessageByStatus(List<MessageStatuss> status) {
        Query query = getCurrentSession().createQuery("from Message m join m.status s where s.id in (:list)");
        List<Integer> a = new ArrayList<Integer>();
        for (MessageStatuss s : status) {
            a.add(s.getId());
            System.out.println("----BODY = " + s.getMessage().getBody());
        }
        query.setParameterList("list", a);
        return query.list();
    }
}
