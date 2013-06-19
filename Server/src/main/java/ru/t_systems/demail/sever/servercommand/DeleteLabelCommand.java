/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.Iterator;
import java.util.Set;
import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.dao.account.AccountDAOImpl;
import ru.t_systems.demail.dao.message.LabelDAO;
import ru.t_systems.demail.dao.message.LabelDAOImpl;
import ru.t_systems.demail.model.message.Label;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.socket.dto.message.LabelDTO;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class DeleteLabelCommand implements ServerCommand {

    private Result result;
    private AccountDAO accountDAO;
    private LabelDAO labelDAO;

    public DeleteLabelCommand() {
        result = new Result();
        accountDAO = new AccountDAOImpl();
        labelDAO = new LabelDAOImpl();
    }

    public Result execute(Command command) {

        LabelDTO labelDTO = (LabelDTO) command.getCommandSource();
        Account account;
        account = accountDAO.getAccountByname(labelDTO.getAccount());
        Set<Label> labels = account.getLabel();
        //labels = account.getLabel();
        for (Iterator<Label> it = labels.iterator(); it.hasNext();) {
            Label label = it.next();
            if (label.getName().equals(labelDTO.getName())) {
                labelDAO.deleteLabel(label);
            }
        }

        return result;
    }
}
