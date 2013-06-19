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
public class GetSendMessageCommand implements ServerCommand {

    private Result result;
    private AccountDAO accountDAO;
    private MessageStatusDAO messageStatusDAO;

    public GetSendMessageCommand() {
        result = new Result();
        accountDAO = new AccountDAOImpl();
        messageStatusDAO = new MessageStatusDAOImpl();
    }

    public Result execute(Command command) {

        Set<AccountDTO> accountDTOgetsend = (Set<AccountDTO>) command.getCommandSource();
        Set<MessageStatuss> messageStatusssend = new HashSet<MessageStatuss>();
        List<Account> accountIdsend = new ArrayList<Account>();
        for (Iterator<AccountDTO> it = accountDTOgetsend.iterator(); it.hasNext();) {
            accountIdsend.add(accountDAO.getAccount(it.next().getId()));

        }
        messageStatusssend.addAll(messageStatusDAO.getMessageStatussSendByAccount(accountIdsend));
        List<MessageStatussDTO> messageStatussDTOsSend = new ArrayList<MessageStatussDTO>();
        for (Iterator<MessageStatuss> it = messageStatusssend.iterator(); it.hasNext();) {
            MessageStatuss ms = it.next();
            messageStatussDTOsSend.add(ms.toMessageStatusDTO());
        }
        result.setResult(messageStatussDTOsSend);

        return result;
    }
}
