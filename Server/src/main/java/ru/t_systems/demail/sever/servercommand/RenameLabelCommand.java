/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.HashSet;
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
public class RenameLabelCommand implements ServerCommand {

    private Result result;
    private AccountDAO accountDAO;
    private LabelDAO labelDAO;

    public RenameLabelCommand() {
        result = new Result();
        accountDAO = new AccountDAOImpl();
        labelDAO = new LabelDAOImpl();
    }

    public Result execute(Command command) {

        LabelDTO labelDTOren = (LabelDTO) ((Object[]) command.getCommandSource())[0];
        Account accountren;
        accountren = accountDAO.getAccountByname(labelDTOren.getAccount());
        Set<Label> labelsren = new HashSet<Label>();
        labelsren = accountren.getLabel();
        for (Iterator<Label> it = labelsren.iterator(); it.hasNext();) {
            Label label = it.next();
            if (label.getName().equals(labelDTOren.getName())) {
                label.setName((String) ((Object[]) command.getCommandSource())[1]);
                labelDAO.updateLabel(label);
            }
        }

        return result;
    }
}
