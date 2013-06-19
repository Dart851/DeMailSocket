/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.Arrays;
import ru.t_systems.demail.dao.user.UserDAO;
import ru.t_systems.demail.dao.user.UserDAOImpl;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.socket.dto.UserDTO;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class LoginCommand implements ServerCommand {

    private Result result;
    private UserDAO userDAO;

    public LoginCommand() {
        result = new Result();
        userDAO = new UserDAOImpl();
    }

    public Result execute(Command command) {
        User user1 = userDAO.getUser(((UserDTO) command.getCommandSource()).getLogin());
        if (user1 != null && Arrays.equals(user1.getPassword().toCharArray(), ((UserDTO) command.getCommandSource()).getPassword())) {

            result.setResult(user1.toUserDTO());
            System.out.println("size accounts" + user1.getAccounts().size());
        } else {
            result.setHasError(true);
            result.setError("Invalid Login or Password");
        }
        return result;
    }
}
