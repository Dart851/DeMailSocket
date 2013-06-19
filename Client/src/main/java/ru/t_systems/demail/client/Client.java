package ru.t_systems.demail.client;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.t_systems.demail.socket.dto.UserDTO;
import ru.t_systems.demail.socket.dto.message.MessageStatussDTO;

// A client for our Multithreaded SocketServer. 
public class Client {

    private static UserDTO user = null;
    private static List<MessageStatussDTO> messageStatussDTO = null;
    private static List<MessageStatussDTO> sendMessageStatusDTO = null;

    public static void setSendMessageStatusDTO(List<MessageStatussDTO> sendMessageStatusDTO) {
        Client.sendMessageStatusDTO = sendMessageStatusDTO;
    }

    public static List<MessageStatussDTO> getSendMessageStatusDTO() {
        return sendMessageStatusDTO;
    }
    private Socket s;
    //private BufferedReader in;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private static final Client INSTANCE = new Client();

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    private Client() {
        try {
            s = new Socket("localhost", 11111);
        } catch (UnknownHostException uhe) {
            // Server Host unreachable
            System.out.println("Unknown Host : localhost");
            s = null;
        } catch (IOException ioe) {
            // Cannot connect to port on given server host
            System.out
                    .println("Cant connect to server at 11111. Make sure it is running.");
            s = null;
        }

        if (s != null) {
            try {
                // Create the streams to send and receive information
                out = new ObjectOutputStream(s.getOutputStream());
                //in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                in = new ObjectInputStream(s.getInputStream());

            } catch (IOException e) {
                System.out.println(e);
                s = null;
            }
        }
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public static Client getInstanse() {
        if (INSTANCE.s != null) {
            return INSTANCE;

        } else {
            return null;
        }
    }

    public static UserDTO getUser() {
        return user;
    }

    public static void setUser(UserDTO user) {
        Client.user = user;
    }

    public static void setMessageStatus(List<MessageStatussDTO> messageStatussDTOs) {
        Client.messageStatussDTO = messageStatussDTOs;
    }

    public static List<MessageStatussDTO> getMessageStatus() {
        return Client.messageStatussDTO;
    }

    public void close() {
        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
