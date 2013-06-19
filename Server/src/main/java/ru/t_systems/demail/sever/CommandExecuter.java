package ru.t_systems.demail.sever;

import ru.t_systems.demail.sever.servercommand.DeleteLabelCommand;
import ru.t_systems.demail.sever.servercommand.GetMessageByIdCommand;
import ru.t_systems.demail.sever.servercommand.GetMessagesCommand;
import ru.t_systems.demail.sever.servercommand.GetSendMessageCommand;
import ru.t_systems.demail.sever.servercommand.LoginCommand;
import ru.t_systems.demail.sever.servercommand.RegistrationCommand;
import ru.t_systems.demail.sever.servercommand.RenameLabelCommand;
import ru.t_systems.demail.sever.servercommand.SendMessageCommand;
import ru.t_systems.demail.sever.servercommand.ServerCommand;
import ru.t_systems.demail.sever.servercommand.UnknownCommand;
import ru.t_systems.demail.soket.command.Command;
import static ru.t_systems.demail.soket.command.CommandType.*;

import ru.t_systems.demail.soket.command.Result;

public class CommandExecuter {

    private Result result;
    private ServerCommand serverCommand;
    private Command command;

    public CommandExecuter(Command command) {
        this.command = command;

    }

    public Result execute() {

        switch (command.getCommandName()) {
            case USER_LOGIN:
                serverCommand = new LoginCommand();
                break;

            case USER_REGISTRATION:
                serverCommand = new RegistrationCommand();
                break;

            case DELETE_LABEL:
                serverCommand = new DeleteLabelCommand();
                break;

            case RENAME_LABEL:
                serverCommand = new RenameLabelCommand();
                break;

            case GET_MESSAGES:
                serverCommand = new GetMessagesCommand();
                break;

            case SEND_MESSAGE:
                serverCommand = new SendMessageCommand();
                break;

            case GET_SEND_MESSAGE:
                serverCommand = new GetSendMessageCommand();
                break;

            case GEt_MESSAGE_BY_ID:
                serverCommand = new GetMessageByIdCommand();
                break;

            default:
                serverCommand = new UnknownCommand();
                break;
        }
        result = serverCommand.execute(command);
        return result;
    }
}
