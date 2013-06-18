package ru.t_systems.demail.sever;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.dao.account.AccountDAOImpl;
import ru.t_systems.demail.dao.message.LabelDAO;
import ru.t_systems.demail.dao.message.LabelDAOImpl;
import ru.t_systems.demail.dao.message.MessageDAO;
import ru.t_systems.demail.dao.message.MessageDAOImpl;
import ru.t_systems.demail.dao.message.MessageStatusDAO;
import ru.t_systems.demail.dao.message.MessageStatusDAOImpl;
import ru.t_systems.demail.dao.user.RoleDAO;
import ru.t_systems.demail.dao.user.RoleDAOImpl;

import ru.t_systems.demail.dao.user.UserDAO;
import ru.t_systems.demail.dao.user.UserDAOImpl;
import ru.t_systems.demail.model.message.Label;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.socket.dto.AccountDTO;
import ru.t_systems.demail.socket.dto.UserDTO;
import ru.t_systems.demail.socket.dto.message.LabelDTO;
import ru.t_systems.demail.socket.dto.message.MessageDTO;
import ru.t_systems.demail.socket.dto.message.MessageStatussDTO;
import ru.t_systems.demail.soket.command.Command;
import static ru.t_systems.demail.soket.command.CommandType.DELETE_LABEL;
import static ru.t_systems.demail.soket.command.CommandType.GET_MESSAGE;
import static ru.t_systems.demail.soket.command.CommandType.USER_REGISTRATION;
import ru.t_systems.demail.soket.command.Result;

public class CommandExecuter {

    private Command command;
    private UserDAO userDAO;
    private AccountDAO accountDAO;
    private LabelDAO labelDAO;
    private MessageDAO messageDAO;
    private MessageStatusDAO messageStatusDAO;

    public CommandExecuter(Command command) {
        this.command = command;
        userDAO = new UserDAOImpl();
        accountDAO = new AccountDAOImpl();
        labelDAO = new LabelDAOImpl();
        messageStatusDAO = new MessageStatusDAOImpl();
    messageDAO = new MessageDAOImpl();
            
    }

    public Result execute() {
        Result result = new Result();
        switch (command.getCommandName()) {
            case USER_LOGIN:
                User user1 = userDAO.getUser(((UserDTO) command.getCommandSource()).getLogin());
                if (user1 != null && Arrays.equals(user1.getPassword().toCharArray(), ((UserDTO) command.getCommandSource()).getPassword())) {

                    result.setResult(user1.toUserDTO());
                    System.out.println("size accounts" + user1.getAccounts().size());
                } else {
                    result.setHasError(true);
                    result.setError("Invalid Login or Password");
                }
                break;

            case USER_REGISTRATION:
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
                break;
            case DELETE_LABEL:
                LabelDTO labelDTO = (LabelDTO) command.getCommandSource();
                Account account;
                account = accountDAO.getAccountByname(labelDTO.getAccount());
                Set<Label> labels = new HashSet<Label>();
                labels = account.getLabel();
                for (Iterator<Label> it = labels.iterator(); it.hasNext();) {
                    Label label = it.next();
                    if (label.getName().equals(labelDTO.getName())) {
                        labelDAO.deleteLabel(label);
                    }
                }
                //account.setLabel(labels);
                //accountDAO.updateAccount(account);

                break;

            case RENAME_LABEL:

                LabelDTO labelDTOren = (LabelDTO) ((Object[]) command.getCommandSource())[0];
                Account accountren;
                accountren = accountDAO.getAccountByname(labelDTOren.getAccount());
                Set<Label> labelsren = new HashSet<Label>();
                labels = accountren.getLabel();
                for (Iterator<Label> it = labels.iterator(); it.hasNext();) {
                    Label label = it.next();
                    if (label.getName().equals(labelDTOren.getName())) {
                        label.setName((String) ((Object[]) command.getCommandSource())[1]);
                        labelDAO.updateLabel(label);
                    }
                }

                break;

            case GET_MESSAGE:
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

                break;

            case SEND_MESSAGE:
                MessageDTO messageDTO = (MessageDTO) command.getCommandSource();
                List<MessageStatuss> messageStatusses = new ArrayList<MessageStatuss>();
                for (Iterator<MessageStatussDTO> it = messageDTO.getStatus().iterator(); it.hasNext();) {
                    MessageStatussDTO messageStatuss1 = it.next();
                    MessageStatuss st = new MessageStatuss();
                    st.setAcountsSender(accountDAO.getAccountByname(messageStatuss1.getAcountsSender().getAccountName()));
                    st.setAcounts(accountDAO.getAccountByname(messageStatuss1.getAccount().getAccountName()));
                    messageStatusses.add(st);
                }

       
                Message message = new Message();
                message.setBody(messageDTO.getBody());
                message.setTimeStamp(new Date());
                message.setStatus(messageStatusses);
                messageDAO.addMessage(message);
                break;
                
                case GET_SEND_MESSAGE:
                Set<AccountDTO> accountDTOgetsend = (Set<AccountDTO>) command.getCommandSource();
                Set<MessageStatuss> messageStatusssend = new HashSet<MessageStatuss>();
                List<Account> accountIdsend = new ArrayList<Account>();
                for (Iterator<AccountDTO> it = accountDTOgetsend.iterator(); it.hasNext();) {
                    accountIdsend.add(accountDAO.getAccount(it.next().getId()));

                }
                messageStatusssend.addAll(messageStatusDAO.getMessageStatussSendByAccount(accountIdsend));
                System.out.println("Coutn message " + messageStatusssend.size());
                List<MessageStatussDTO> messageStatussDTOsSend = new ArrayList<MessageStatussDTO>();
                for (Iterator<MessageStatuss> it = messageStatusssend.iterator(); it.hasNext();) {
                    MessageStatuss ms = it.next();
                    messageStatussDTOsSend.add(ms.toMessageStatusDTO());
                }
                result.setResult(messageStatussDTOsSend);

                break;
                
                
            default:
                result.setHasError(true);
                result.setError("unknow command");
                break;
        }
        return result;
    }
}
