/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.dao.account.AccountDAOImpl;
import ru.t_systems.demail.dao.message.MessageStatusDAO;
import ru.t_systems.demail.dao.message.MessageStatusDAOImpl;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.socket.dto.AccountDTO;
import ru.t_systems.demail.socket.dto.message.MessageStatussDTO;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class GetMessagesCommand implements ServerCommand {

    private Result result;
    private AccountDAO accountDAO;
    private MessageStatusDAO messageStatusDAO;

    public GetMessagesCommand() {
        result = new Result();
        accountDAO = new AccountDAOImpl();
        messageStatusDAO = new MessageStatusDAOImpl();
    }

    public Result execute(Command command) {
        Set<AccountDTO> accountDTOget = (Set<AccountDTO>) command.getCommandSource();
        Set<MessageStatuss> messageStatuss = new HashSet<MessageStatuss>();
        List<Account> accountId = new ArrayList<Account>();
        for (Iterator<AccountDTO> it = accountDTOget.iterator(); it.hasNext();) {
            accountId.add(accountDAO.getAccount(it.next().getId()));

        }
        messageStatuss.addAll(messageStatusDAO.getMessageStatussByAccount(accountId));
        System.out.println("Coutn message " + messageStatuss.size());
        List<MessageStatussDTO> messageStatussDTOs = new ArrayList<MessageStatussDTO>();
        for (Iterator<MessageStatuss> it = messageStatuss.iterator(); it.hasNext();) {
            MessageStatuss ms = it.next();
            messageStatussDTOs.add(ms.toMessageStatusDTO());
        }
        result.setResult(messageStatussDTOs);

        return result;
    }
}
