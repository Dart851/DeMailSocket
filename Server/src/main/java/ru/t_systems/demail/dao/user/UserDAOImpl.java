package ru.t_systems.demail.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.sever.ServerInit;

public class UserDAOImpl implements UserDAO {

    private Session openSession() {
        return ServerInit.getSession().getCurrentSession();
    }

    public User getUser(String login) {
        ServerInit.getSession().getCurrentSession().beginTransaction();
        List<User> userList = new ArrayList<User>();
        Query query = openSession().createQuery(
                "from User u where u.login = :login");
        query.setParameter("login", login);
        userList = query.list();
        ServerInit.getSession().getCurrentSession().getTransaction().commit();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public void addUser(User user) {
        ServerInit.getSession().getCurrentSession().beginTransaction();
        openSession().save(user);
        ServerInit.getSession().getCurrentSession().getTransaction().commit();

    }

    public void update(User user) {
        openSession().update(user);

    }

    public void deleteUser(User user) {
        openSession().delete(user);

    }
}
