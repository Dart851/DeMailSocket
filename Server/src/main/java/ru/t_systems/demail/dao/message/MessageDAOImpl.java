package ru.t_systems.demail.dao.message;

import java.util.ArrayList;
import java.util.Arrays;
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
        getCurrentSession().beginTransaction();
        Message message = (Message) getCurrentSession().get(Message.class, id);
        return message;
    }

    public void addMessage(Message message) {
        getCurrentSession().beginTransaction();
        getCurrentSession().save(message);
        getCurrentSession().getTransaction().commit();

    }

   @Deprecated
    public List<Message> getMessageByStatus(List<MessageStatuss> status) {
       getCurrentSession().beginTransaction();
        Query query = getCurrentSession().createQuery("from Message m join m.status s where s.id in (:list)");
        List<Integer> a = new ArrayList<Integer>();
        /*for (MessageStatuss s : status) {
            a.add(s.getId());
            System.out.println("----BODY = " + s.getMessage().getBody());
        }*/
        query.setParameterList("list", a);
        return query.list();
    }

    public Message getMessageByStatusId(MessageStatuss status1) {
 getCurrentSession().beginTransaction();
    Query query = getCurrentSession().createQuery("from Message m join m.messageStatus s in (:statuss)");
    query.setParameterList("statuss", Arrays.asList(status1));
   // return (Message) (query.list().get(0));
        System.out.println("SIZE LIST"+query.list().size());
    return null;
    }

    public void updateMessage(Message message) {
     getCurrentSession().beginTransaction();
     getCurrentSession().update(message);
    }
}
