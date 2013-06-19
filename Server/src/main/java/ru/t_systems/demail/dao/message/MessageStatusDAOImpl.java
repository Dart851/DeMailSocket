package ru.t_systems.demail.dao.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.t_systems.demail.dao.account.AccountDAOImpl;

import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.sever.ServerInit;

public class MessageStatusDAOImpl implements MessageStatusDAO {

    private Session getCurrentSession() {
        return ServerInit.getSession().getCurrentSession();
    }

    public MessageStatuss getMessageStatus(int id) {
        getCurrentSession().beginTransaction();
        MessageStatuss messageStatus = (MessageStatuss) getCurrentSession()
                .get(MessageStatuss.class, id);
        return messageStatus;
    }

    public void addStatusUser(MessageStatuss messageStatus) {
        getCurrentSession().save(messageStatus);

    }

    public void addStatusUser(Set<MessageStatuss> messageStatus) {
        for (MessageStatuss status : messageStatus) {
            getCurrentSession().save(status);
        }

    }

    public List<MessageStatuss> getMessageStatussByAccount(List<Account> accounts) {
        getCurrentSession().beginTransaction();
        //Criteria criteria = getCurrentSession().createCriteria(MessageStatuss.class);
//Criteria criteria1 = criteria.createCriteria("account");
//criteria1.add(Restrictions.in("account_id", accounts));
////criteria1.add(Restrictions.eq("id", 2));
////criteria.add(Restrictions.eq("account.id", accountId));
////criteria.add(Restrictions.eq("account.id", 2));
////criteria.list();


        List<MessageStatuss> userList = new ArrayList<MessageStatuss>();
        Query query = getCurrentSession().createQuery(
                "from MessageStatuss b where b.account in (:accounts) and b.label is null");


//		List<Integer> list = Arrays.asList(accountId);
        //query.setParameter("label", null);
        query.setParameterList("accounts", accounts);
//	//	query.setParameter("account_id", accountId);
//	//	query.setParameter("account_id", 2);
        userList = query.list();
        System.out.println("Size message " + userList.size());
        //System.out.println("Staus id "+userList.get(0).getId());
        getCurrentSession().getTransaction().commit();
        return userList;
    }

//	public List<Message> getMessageUser(int userId) {
//		Criteria criteria = getCurrentSession().createCriteria(Message.class);
//		// criteria.add(Restrictions.eq("isDelete",false));
//		Criteria criteria1= criteria.createAlias("status_message", "status_message");
//		
//		criteria1.createAlias("status_message.user", "user").add(Restrictions.eq("userid",userId));
//		/*criteria.add(Restrictions.eq("id", manId));
//		 criteria.setFetchMode("birds", FetchMode.JOIN);
//		 criteria.setFetchMode("dogs", FetchMode.JOIN);
//		 return criteria.uniqueResult();*/
//		return criteria1.list();
//	}
    public List<MessageStatuss> getMessageStatussSendByAccount(List<Account> accounts) {
        getCurrentSession().beginTransaction();
        List<MessageStatuss> userList = new ArrayList<MessageStatuss>();
        
        Query query = getCurrentSession().createQuery(
                "from MessageStatuss b where b.acountsSender in (:accounts)");


//		List<Integer> list = Arrays.asList(accountId);
        //query.setParameter("label", null);
        query.setParameterList("accounts", accounts);
//	//	query.setParameter("account_id", accountId);
//	//	query.setParameter("account_id", 2);
        userList = query.list();
        System.out.println("Size message " + userList.size());
        //System.out.println("Staus id "+userList.get(0).getId());
        getCurrentSession().getTransaction().commit();
        return userList;
    }

    public void update(MessageStatuss messageStatuss) {
  getCurrentSession().beginTransaction();
  getCurrentSession().update(messageStatuss);
  getCurrentSession().getTransaction().commit();
    }
}
