package ru.t_systems.demail.sever;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.CommandType;

public class Server {

    ServerSocket serverSocket;
    boolean ServerOn = true;

    public Server() {

        try {
            serverSocket = new ServerSocket(11111);
        } catch (IOException ioe) {
            System.out
                    .println("Could not create server socket on port 11111. Quitting.");
            System.exit(-1);
        }

        System.out.println(ServerInit.getSession().getCurrentSession());
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("It is now : " + formatter.format(now.getTime()));
       
        while (ServerOn) {
            try {
                // Accept incoming connections.
                Socket clientSocket = serverSocket.accept();

                ClientThread cliThread = new ClientThread(
                        clientSocket);
                cliThread.start();

            } catch (IOException ioe) {
                System.out
                        .println("Exception encountered on accept. Ignoring. Stack Trace :");
                ioe.printStackTrace();
            }

        }

        try {
            serverSocket.close();
            System.out.println("Server Stopped");
        } catch (Exception ioe) {
            System.out.println("Problem stopping server socket");
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        new Server();

    }

    class ClientThread extends Thread {

        Socket clientSocket;
        boolean clientRun = true;

        public ClientThread() {
            super();
        }

        ClientThread(Socket s) {
            clientSocket = s;

        }

        public void run() {

           
            ObjectInputStream in = null;
            
            ObjectOutputStream out = null;

            System.out.println("Accepted Client Address - "
                    + clientSocket.getInetAddress().getHostName());

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

                    inputObject = in.readObject();
                    if (inputObject instanceof Command) {
                        clientCommand = (Command) inputObject;
                        System.out.println("Cleint command YES");
                    } else {
                        clientCommand = new Command(CommandType.QUIT, null);
                    }


                    // System.out.println("Client Says :" + clientCommand);

                    if (!ServerOn) {
                        // Special command. Quit this thread
                        System.out.print("Server has already stopped");
                        //out.writeObject();
                        out.flush();
                        clientRun = false;

                    }

                    if (clientCommand.getCommandName().equals(CommandType.QUIT)) {

                        clientRun = false;
                        System.out.print("Stopping client thread for client : ");

                    } else {
                        System.out.println("Cleint command RUN ex");
                        out.writeObject(new CommandExecuter(clientCommand).execute());
                        System.out.println("Cleint command FINISH ex");
                        out.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Clean up
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                    System.out.println("...Stopped");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}