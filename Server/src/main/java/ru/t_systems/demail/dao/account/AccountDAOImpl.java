package ru.t_systems.demail.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.sever.ServerInit;

public class AccountDAOImpl implements AccountDAO {

    private Session getCurrentSession() {
        return ServerInit.getSession().getCurrentSession();
    }

    public Account getAccount(int id) {
       getCurrentSession().beginTransaction();
        Account role = (Account) getCurrentSession().get(Account.class, id);
       // getCurrentSession().getTransaction().commit();
        return role;
    }

    public Account getAccountByname(String account) {
        getCurrentSession().beginTransaction();
        List<Account> accountList = new ArrayList<Account>();
        Query query = getCurrentSession().createQuery(
                "from Account a where a.accountName = :account");
        query.setParameter("account", account);
        accountList = query.list();
         getCurrentSession().getTransaction().commit();
        if (accountList.size() > 0) {
            return accountList.get(0);
        } else {
            return null;
        }
    }

    public void updateAccount(Account account) {
        getCurrentSession().beginTransaction();
        getCurrentSession().update(account);
        getCurrentSession().getTransaction().commit();
    }
}
