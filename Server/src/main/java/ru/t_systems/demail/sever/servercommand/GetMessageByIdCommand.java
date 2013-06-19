/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import java.util.Arrays;
import ru.t_systems.demail.dao.message.MessageStatusDAO;
import ru.t_systems.demail.dao.message.MessageStatusDAOImpl;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.socket.dto.message.MessageDTO;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class GetMessageByIdCommand implements ServerCommand {

    private Result result;
    private MessageStatusDAO messageStatusDAO;

    public GetMessageByIdCommand() {
        result = new Result();
        messageStatusDAO = new MessageStatusDAOImpl();
    }

    public Result execute(Command command) {
        MessageStatuss messageStatuss1 = messageStatusDAO.getMessageStatus((Integer) command.getCommandSource());

        MessageDTO messageDTO1 = messageStatuss1.getMessage().toMessageDTO();

        messageDTO1.setStatus(Arrays.asList(messageStatuss1.toMessageStatusDTO()));
        result.setResult(messageDTO1);
        messageStatuss1.setIsRead(true);

        messageStatusDAO.update(messageStatuss1);

        return result;
    }
}
