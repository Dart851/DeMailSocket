/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.CommandType;

/**
 *
 * @author Dart
 */
class ClientThread extends Thread {
    Socket clientSocket;
    boolean clientRun = true;
    private final Server clientsThread;

    public ClientThread(final Server clientsThread) {
        super();
        this.clientsThread = clientsThread;
    }

    ClientThread(Socket s, final Server clientsThread) {
        this.clientsThread = clientsThread;
        clientSocket = s;
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        //            System.out.println("Accepted Client Address - "
        //                    + clientSocket.getInetAddress().getHostName());
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            while (clientRun) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Object inputObject = null;
                Command clientCommand;
                try {
                    inputObject = in.readObject();
                } catch (Exception e) {
                    // clientCommand = new Command(CommandType.QUIT, null);
                }
                if (inputObject instanceof Command) {
                    clientCommand = (Command) inputObject;
                } else {
                    clientCommand = new Command(CommandType.QUIT, null);
                }
                if (!clientsThread.ServerOn) {
                    System.out.print("Server stopped");
                    clientRun = false;
                }
                if (clientCommand.getCommandName().equals(CommandType.QUIT)) {
                    clientRun = false;
                    // System.out.print("Stopping client thread for client : ");
                } else {
                    //  System.out.println("Cleint command RUN ex");
                    out.writeObject(new CommandExecuter(clientCommand).execute());
                    //   System.out.println("Cleint command FINISH ex");
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close client thread
            try {
                in.close();
                out.close();
                clientSocket.close();
                //System.out.println("Server isStopped");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
}
