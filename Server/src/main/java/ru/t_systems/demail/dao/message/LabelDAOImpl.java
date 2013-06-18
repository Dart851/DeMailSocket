package ru.t_systems.demail.dao.message;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.t_systems.demail.model.message.Label;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.sever.ServerInit;

public class LabelDAOImpl implements LabelDAO {

    private Session getCurrentSession() {
        return ServerInit.getSession().getCurrentSession();
    }

    public Message getLabel(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addLabel(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteLabel(Label label) {
        getCurrentSession().beginTransaction();
        getCurrentSession().delete(label);
        getCurrentSession().getTransaction().commit();
    }

    public void updateLabel(Label label) {
     getCurrentSession().beginTransaction();
        getCurrentSession().update(label);
        getCurrentSession().getTransaction().commit();
    }
}
