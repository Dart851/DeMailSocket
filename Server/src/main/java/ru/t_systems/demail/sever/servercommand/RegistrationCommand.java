/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.Arrays;
import java.util.HashSet;
import ru.t_systems.demail.dao.user.UserDAO;
import ru.t_systems.demail.dao.user.UserDAOImpl;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.socket.dto.UserDTO;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class RegistrationCommand implements ServerCommand {

    private Result result;
    private UserDAO userDAO;

    public RegistrationCommand() {
        result = new Result();
        userDAO = new UserDAOImpl();
    }

    public Result execute(Command command) {

        if (userDAO.getUser(((UserDTO) command.getCommandSource()).getLogin()) != null) {
            result.setHasError(true);
            result.setError("User alredy registred");
        } else {
            User user = new User((UserDTO) command.getCommandSource());

            Account acount = new Account();
            acount.setAccountName(((UserDTO) command.getCommandSource()).getLogin());

            user.setAccounts(new HashSet<Account>(Arrays.asList(acount)));

            userDAO.addUser(user);

        }
        return result;
    }
}
