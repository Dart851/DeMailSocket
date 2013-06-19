/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.dao.account.AccountDAOImpl;
import ru.t_systems.demail.dao.message.MessageDAO;
import ru.t_systems.demail.dao.message.MessageDAOImpl;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.socket.dto.message.MessageDTO;
import ru.t_systems.demail.socket.dto.message.MessageStatussDTO;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class SendMessageCommand implements ServerCommand {

    private Result result;
    private AccountDAO accountDAO;
    private MessageDAO messageDAO;

    public SendMessageCommand() {
        result = new Result();
        accountDAO = new AccountDAOImpl();
        messageDAO = new MessageDAOImpl();

    }

    public Result execute(Command command) {
        MessageDTO messageDTO = (MessageDTO) command.getCommandSource();
        List<MessageStatuss> messageStatusses = new ArrayList<MessageStatuss>();
        for (Iterator<MessageStatussDTO> it = messageDTO.getStatus().iterator(); it.hasNext();) {
            MessageStatussDTO messageStatuss1 = it.next();
            MessageStatuss st = new MessageStatuss();
            st.setAcountsSender(accountDAO.getAccountByname(messageStatuss1.getAcountsSender().getAccountName()));
            if (accountDAO.getAccountByname(messageStatuss1.getAccount().getAccountName()) == null) {
                result.setHasError(true);
                result.setError("Account not found");

            }
            st.setAcounts(accountDAO.getAccountByname(messageStatuss1.getAccount().getAccountName()));
            messageStatusses.add(st);
        }

        Message message = new Message();
        message.setBody(messageDTO.getBody());
        message.setTimeStamp(new Date());
        message.setStatus(messageStatusses);
        message.setTitle(messageDTO.getTitle());
        messageDAO.addMessage(message);
        return result;
    }
}
